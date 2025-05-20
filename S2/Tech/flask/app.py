from flask import Flask, render_template, request
import numpy as np

app = Flask(__name__)

def analyze_game(matrix):
    """
    Complete game analysis with:
    - Strict/Weak dominance checks
    - Iterated elimination of dominated strategies
    - Pareto optimal outcomes
    - Security levels (Maximin)
    - Pure Nash equilibrium
    """
    current_matrix = matrix.copy()
    original = matrix.copy()

    results = {
        'strict_dominant': {"P1": [], "P2": []},
        'elimination': [],
        'pareto': [],
        'security': {"P1": None, "P2": None},
        'nash': []
    }

    # ----------------------
    # 1. Strict Dominance Check
    # ----------------------

    def is_strictly_dominant(player, strategy_idx):
        """True if strategy dominates ALL others in ALL scenarios"""
        if player == 1:
            payoffs = np.array([[cell[0] for cell in row] for row in current_matrix])
            current = payoffs[strategy_idx]
            for other in range(payoffs.shape[0]):
                if other == strategy_idx:
                    continue
                if not np.all(current > payoffs[other]):
                    return False
        else:
            payoffs = np.array([[cell[1] for cell in row] for row in current_matrix]).T
            current = payoffs[strategy_idx]
            for other in range(payoffs.shape[0]):
                if other == strategy_idx:
                    continue
                if not np.all(current > payoffs[other]):
                    return False
        return True

    # Check for any strictly dominant strategies
    # Player 1 check
    for i in range(current_matrix.shape[0]):
        if is_strictly_dominant(1, i):
            strat = current_matrix[i][0][2]  # Get strategy name
            if strat not in results['strict_dominant']["P1"]:
                results['strict_dominant']["P1"].append(strat)

    # Player 2 check
    for j in range(current_matrix.shape[1]):
        if is_strictly_dominant(2, j):
            strat = current_matrix[0][j][3]  # Get strategy name
            if strat not in results['strict_dominant']["P2"]:
                results['strict_dominant']["P2"].append(strat)

    # ----------------------
    # 2. Weak Dominance Check
    # ----------------------

    def is_weakly_dominated(player, strategy_idx):
        """True if exists a strategy that weakly dominates this one"""
        if player == 1:
            payoffs = np.array([[cell[0] for cell in row] for row in current_matrix])
            current = payoffs[strategy_idx]
            for other in range(payoffs.shape[0]):
                if other == strategy_idx:
                    continue
                other_payoffs = payoffs[other]
                if np.all(other_payoffs >= current) and np.any(other_payoffs > current):
                    return True
        else:
            payoffs = np.array([[cell[1] for cell in row] for row in current_matrix]).T
            current = payoffs[strategy_idx]
            for other in range(payoffs.shape[0]):
                if other == strategy_idx:
                    continue
                other_payoffs = payoffs[other]
                if np.all(other_payoffs >= current) and np.any(other_payoffs > current):
                    return True
        return False

    # ----------------------
    # 3. Iterated Elimination
    # ----------------------

    # First eliminate strictly dominated
    eliminated = True
    while eliminated:
        eliminated = False

        # Player 1 elimination
        for i in reversed(range(current_matrix.shape[0])):
            if is_weakly_dominated(1, i):
                current_matrix = np.delete(current_matrix, i, axis=0)
                eliminated = True
                break

        # Player 2 elimination
        for j in reversed(range(current_matrix.shape[1])):
            if is_weakly_dominated(2, j):
                current_matrix = np.delete(current_matrix, j, axis=1)
                eliminated = True
                break

    # Store remaining strategies
    if current_matrix.size == 0:
        results['elimination'].append("No strategies remain after elimination")
    else:
        for row in current_matrix:
            for cell in row:
                results['elimination'].append(f"P1:{cell[2]}, P2:{cell[3]} ({cell[0]},{cell[1]})")

    # ----------------------
    # 4. Additional Analyses
    # ----------------------

    # Pareto Optimal Outcomes
    all_outcomes = [
        (cell[0], cell[1], cell[2], cell[3]) for row in original for cell in row
    ]
    for outcome in all_outcomes:
        dominated = False
        for other in all_outcomes:
            if (other[0] >= outcome[0] and other[1] >= outcome[1]) and (
                other[0] > outcome[0] or other[1] > outcome[1]
            ):
                dominated = True
                break
        if not dominated:
            results['pareto'].append(f"P1:{outcome[2]}, P2:{outcome[3]} ({outcome[0]},{outcome[1]})")

    # Security Levels (Maximin)
    p1_payoffs = np.array([[cell[0] for cell in row] for row in original])
    results['security']['P1'] = max([min(row) for row in p1_payoffs])
    p2_payoffs = np.array([[cell[1] for cell in row] for row in original]).T
    results['security']['P2'] = max([min(col) for col in p2_payoffs])

    # Pure Nash Equilibrium
    for i in range(original.shape[0]):
        for j in range(original.shape[1]):
            p1_br = all(
                original[i][j][0] >= original[k][j][0] for k in range(original.shape[0])
            )
            p2_br = all(
                original[i][j][1] >= original[i][l][1] for l in range(original.shape[1])
            )
            if p1_br and p2_br:
                results['nash'].append(
                    f"P1:{original[i][j][2]}, P2:{original[i][j][3]} ({original[i][j][0]},{original[i][j][1]})"
                )

    return results

@app.route('/', methods=['GET', 'POST'])
def index():
    if request.method == 'POST':
        try:
            # Get form data
            p1_strategies = int(request.form['p1_strategies'])
            p2_strategies = int(request.form['p2_strategies'])

            # Build matrix from form inputs
            matrix = []
            for i in range(p1_strategies):
                row = []
                for j in range(p2_strategies):
                    p1_payoff = int(request.form[f'p1_{i}_{j}'])
                    p2_payoff = int(request.form[f'p2_{i}_{j}'])
                    p1_strat = request.form[f'p1_strat_{i}']
                    p2_strat = request.form[f'p2_strat_{j}']
                    row.append((p1_payoff, p2_payoff, p1_strat, p2_strat))
                matrix.append(row)

            # Convert to numpy array and analyze
            np_matrix = np.array(matrix, dtype=object)
            results = analyze_game(np_matrix)

            return render_template('results.html', results=results, matrix=matrix)

        except Exception as e:
            error = f"An error occurred: {str(e)}"
            return render_template('index.html', error=error,
                                 p1_strategies=request.form.get('p1_strategies', 2),
                                 p2_strategies=request.form.get('p2_strategies', 2))

    # Default GET request - show empty form
    return render_template('index.html', p1_strategies=2, p2_strategies=2)

@app.route('/update_form', methods=['POST'])
def update_form():
    p1_strategies = int(request.form['p1_strategies'])
    p2_strategies = int(request.form['p2_strategies'])
    return render_template('matrix_form.html',
                         p1_strategies=int(p1_strategies),
                         p2_strategies=int(p2_strategies))

if __name__ == '__main__':
    app.run(debug=True, port=5050)
