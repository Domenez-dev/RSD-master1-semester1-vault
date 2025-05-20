import numpy as np


def strictly_dominant(matrix):

    # Variables for holding both player best strategies based on the adversary player strategy
    player_1_strategies = []
    player_2_strategies = []
    rows, cols, _ = matrix.shape

    # Iterate over the matrix & extract the strategies
    for i in range(rows):

        # Temp variables to compare gains
        gain_temp1 = 0
        gain_temp2 = 0

        # Temp variables to hold the strategy label, intinally it hold (strategy1, strategy1) of player 1 & player 2 respectively
        label_temp1 = matrix[0][0][2]
        label_temp2 = matrix[0][0][3]

        for j in range(cols):

            # Cast the the gains & strategy in the temp variables if a bigger gain is found
            if gain_temp1 < matrix[j][i][0]:
                gain_temp1 = matrix[j][i][0]
                label_temp1 = matrix[j][i][2]
            if gain_temp2 < matrix[i][j][1]:
                gain_temp2 = matrix[i][j][1]
                label_temp2 = matrix[i][j][3]

        # Append the best strategies for players
        player_1_strategies.append(label_temp1)
        player_2_strategies.append(label_temp2)

    print("Player 1 best strategies: ", player_1_strategies)
    print("Player 2 best strategies: ", player_2_strategies)

    # Extract strategies with their count & remove duplication
    unique_player1, count_1 = np.unique(player_1_strategies, return_counts=True)
    unique_player2, count_2 = np.unique(player_2_strategies, return_counts=True)

    # If both players have only 1 strategy as their best strategy & its the same for both then its a strictly dominant strategy
    if np.array_equal(unique_player1, unique_player2) and count_1 == count_2 == 1:
        print(
            "Strictly dominant strategy exists & its value is", player_1_strategies[0]
        )

    # If a strategy is significant for at least half of the player playing posibilities its a Softly dominant strategy
    # (can be different to 3rd of player possibilities in bigger matrices)
    # i did cols - 2 because i added 2 fields in colmuns in the matrix datatype to represent labels, so they shouldn't be counted with the posibilities size
    elif (
        unique_player1[count_1 > cols - 2 / 2].size > 0
        and unique_player2[count_2 > rows / 2].size > 0
    ):
        print(
            f"Softly dominant strategy exists: \nPlayer 1 -> {unique_player1[count_1 > cols-2 / 2]} \nPlayer 2 -> {unique_player2[count_2 > rows / 2]}"
        )

    # If all strategies are spread equally or appear frequently there's no dominant strategy
    else:
        print("No Strict/Soft dominant strategies exist for this game")


# TEST 1:
# matrix_soft_strict_dominant = np.array(
#     [
#         [(10, 10, "A1", "B1"), (4, 9, "A1", "B2"), (4, 11, "A1", "B3")],
#         [(5, 6, "A2", "B1"), (8, 3, "A2", "B2"), (3, 4, "A2", "B3")],
#         [(4, 0, "A3", "B3"), (8, 1, "A3", "B2"), (5, 3, "A3", "B3")],
#     ],
#     dtype=object,
# )

# TEST 2:
matrix_soft_strict_dominant = np.array(
    [
        [(0, 0, "0", "0"), (75, -25, "0", "100")],
        [(-25, 75, "100", "0"), (50, 50, "100", "100")],
    ],
    dtype=object,
)

strictly_dominant(matrix_soft_strict_dominant)


def balance_by_elimination(matrix):
    rows, cols, _ = matrix.shape

    # Function to check if a strategy is strictly dominated
    def is_strictly_dominated(player, strategy_index):
        if player == 1:
            # Player 1's payoffs for the given strategy
            payoffs = [matrix[strategy_index][j][0] for j in range(cols)]
            # Player 1's payoffs for all other strategies
            other_payoffs = [
                [matrix[i][j][0] for j in range(cols)]
                for i in range(rows)
                if i != strategy_index
            ]
        else:
            # Player 2's payoffs for the given strategy
            payoffs = [matrix[i][strategy_index][1] for i in range(rows)]
            # Player 2's payoffs for all other strategies
            other_payoffs = [
                [matrix[i][j][1] for i in range(rows)]
                for j in range(cols)
                if j != strategy_index
            ]

        # Check if the current strategy is strictly dominated by any other strategy
        for other_payoff in other_payoffs:
            if all(payoffs[k] < other_payoff[k] for k in range(len(payoffs))):
                return True
        return False

    # Iteratively eliminate strictly dominated strategies
    while True:
        eliminated = False

        # Check for player 1
        for i in range(rows):
            if is_strictly_dominated(1, i):
                matrix = np.delete(matrix, i, axis=0)
                rows -= 1
                eliminated = True
                break

        # Check for player 2
        for j in range(cols):
            if is_strictly_dominated(2, j):
                matrix = np.delete(matrix, j, axis=1)
                cols -= 1
                eliminated = True
                break

        # If no strategies were eliminated in this iteration, break the loop
        if not eliminated:
            break

    # The remaining strategies are the Nash equilibrium
    print("Nash Equilibrium Strategies:")
    for i in range(rows):
        for j in range(cols):
            print(
                f"Player 1: {matrix[i][j][2]}, Player 2: {matrix[i][j][3]} with payoffs ({matrix[i][j][0]}, {matrix[i][j][1]})"
            )


# Example matrix for testing
matrix_elimination = np.array(
    [
        [(0, 0, "0", "0"), (75, -25, "0", "100")],
        [(-25, 75, "100", "0"), (50, 50, "100", "100")],
    ],
    dtype=object,
)

balance_by_elimination(matrix_elimination)
