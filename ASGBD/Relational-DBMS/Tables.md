### 1. **Structure of a Table**

A table consists of:
- **Columns (Fields)**: Define the attributes or properties of data. Each column has a specific data type (e.g., `INT`, `VARCHAR`, `DATE`).
- **Rows (Records)**: Represent individual entries or data points in the table.

#### Example Table: `employees`

| emp_id | emp_name | department | salary |
|--------|----------|------------|--------|
| 1      | Alice    | HR         | 60000  |
| 2      | Bob      | Finance    | 70000  |
| 3      | Carol    | HR         | 65000  |

---

### 2. **Creating Tables**

Tables are created using the `CREATE TABLE` statement, specifying column names, data types, and constraints.

#### Syntax:

```sql
CREATE TABLE table_name (
    column1 datatype constraint,
    column2 datatype constraint,
    ...
);
```

#### Example:

```sql
CREATE TABLE employees (
    emp_id INT PRIMARY KEY,
    emp_name VARCHAR(50) NOT NULL,
    department VARCHAR(30),
    salary DECIMAL(10, 2) CHECK (salary > 0)
);
```

### Modifying Tables with `ALTER`

[The `ALTER TABLE` statement](Alter%20Table.md) is used to modify the structure of an existing table without losing the data it contains. You can add, modify, or delete columns, as well as manage constraints.

---

### 3. **Data Manipulation**

- **Insert Data**: Add rows to a table.

```sql
INSERT INTO employees (emp_id, emp_name, department, salary)
VALUES (1, 'Alice', 'HR', 60000);
```

- **Update Data**: Modify existing rows.

```sql
UPDATE employees
SET salary = 65000
WHERE emp_id = 1;
```

- **Delete Data**: Remove rows from a table.

```sql
DELETE FROM employees
WHERE emp_id = 1;
```

- **Query Data**: Retrieve data using `SELECT`.

```sql
SELECT emp_name, salary
FROM employees
WHERE department = 'HR';
```

---

### 4. **Constraints**

Constraints ensure data integrity by imposing rules on the columns.

- **PRIMARY KEY**: Uniquely identifies each row.
- **FOREIGN KEY**: Ensures referential integrity by linking to another table.
- **NOT NULL**: Ensures a column cannot have `NULL` values.
- **UNIQUE**: Ensures all values in a column are distinct.
- **CHECK**: Validates data based on a condition.
- **DEFAULT**: Provides a default value if no value is supplied.

#### Example:

```sql
CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    customer_id INT NOT NULL,
    order_date DATE DEFAULT CURRENT_DATE,
    total DECIMAL(10, 2) CHECK (total > 0),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);
```

---

### 5. **Relationships Between Tables**

Tables in relational databases are often related through **keys**:

- **One-to-One**: Each row in Table A corresponds to one row in Table B.
- **One-to-Many**: Each row in Table A corresponds to multiple rows in Table B.
- **Many-to-Many**: Rows in Table A relate to multiple rows in Table B, typically implemented with a junction table.

#### Example of Relationships

- `customers` (One-to-Many with `orders`):

| customer_id | customer_name |
|-------------|---------------|
| 1           | Alice         |
| 2           | Bob           |

| order_id | customer_id | total |
|----------|-------------|-------|
| 101      | 1           | 500   |
| 102      | 2           | 300   |

---

### 6. **Indexes**

Indexes improve query performance by allowing faster lookups.

- **Creating an Index**:

```sql
CREATE INDEX idx_department ON employees(department);
```

- **Using Primary or Unique Keys** automatically creates an index.

---

### 7. **Views vs. Tables**

| **Aspect**    | **Tables**                           | **Views**                                |
|---------------|--------------------------------------|------------------------------------------|
| **Definition** | Physical storage of data            | Virtual table, query-based               |
| **Updates**    | Supports insert, update, and delete | Updates may be limited (read-only views) |
| **Storage**    | Consumes disk space                 | Dynamic, no storage                      |

---

### 8. **Temporary Tables**

Temporary tables are used for intermediate data storage in complex queries or transactions.

- **Creating a Temporary Table**:

```sql
CREATE TEMPORARY TABLE temp_table AS
SELECT * FROM employees WHERE department = 'HR';
```

The table exists only for the session duration.

---

### 9. **Partitioning Tables**

Partitioning splits a table into smaller, more manageable pieces, improving performance for large datasets.

- **Range Partitioning**: Divides rows based on a range of values.

```sql
CREATE TABLE orders (
    order_id INT,
    order_date DATE,
    total DECIMAL(10, 2)
)
PARTITION BY RANGE (order_date) (
    PARTITION p1 VALUES LESS THAN ('2024-01-01'),
    PARTITION p2 VALUES LESS THAN ('2025-01-01')
);
```

---