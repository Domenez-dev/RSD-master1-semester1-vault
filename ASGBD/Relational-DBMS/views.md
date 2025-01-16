### Views in Database Management Systems (DBMS)

A **view** in a DBMS is a virtual table that provides a customized perspective of data from one or more tables. Views do not store data themselves but dynamically retrieve data from the underlying tables whenever accessed. They can be thought of as saved queries or pre-defined SELECT statements. Views are useful for abstracting data, improving security, and simplifying complex queries.

Let’s dive into how views work, their creation and usage, types, advantages, and limitations.

---

### 1. **What is a View?**

A view is a named, saved query that allows users to access a specific set of columns and rows in one or more tables. Unlike regular tables, views do not contain physical data. Instead, they pull data from the tables they are based on, making them dynamic—any change in the underlying tables immediately reflects in the view.

#### Example

Consider a database with a table `employees`:

| emp_id | emp_name   | department | salary |
|--------|------------|------------|--------|
| 1      | Alice      | HR         | 60000  |
| 2      | Bob        | Finance    | 70000  |
| 3      | Carol      | HR         | 65000  |
| 4      | Dave       | IT         | 72000  |

If we create a view to display only employees from the HR department, it would look like this:

```sql
CREATE VIEW hr_employees AS
SELECT emp_id, emp_name, salary
FROM employees
WHERE department = 'HR';
```

Now, querying `hr_employees` will show only the relevant rows from the `employees` table:

| emp_id | emp_name | salary |
|--------|----------|--------|
| 1      | Alice    | 60000  |
| 3      | Carol    | 65000  |

---

### 2. **Types of Views**

There are generally two types of views:

1. **Simple Views**: These views are created based on a single table and do not contain complex functions or multiple joins. Simple views are easier to manage and may allow updates directly through the view.

2. **Complex Views**: These views are based on multiple tables, use joins, aggregate functions, or complex calculations. They are often read-only because updating data through a complex view may cause inconsistencies.

---

### 3. **Creating and Using Views**

Views are created with the `CREATE VIEW` statement. Once created, they can be queried just like regular tables.

#### Creating a View

```sql
CREATE VIEW [view_name] AS
SELECT [columns]
FROM [table_name]
WHERE [conditions];
```

Example:

```sql
CREATE VIEW high_salary_employees AS
SELECT emp_name, salary
FROM employees
WHERE salary > 65000;
```

#### Querying a View

To query data from a view, use `SELECT` like you would with any table:

```sql
SELECT * FROM high_salary_employees;
```

#### Dropping a View

Views can be removed with the `DROP VIEW` statement:

```sql
DROP VIEW high_salary_employees;
```

---

### 4. **Advantages of Views**

Views offer several benefits:

- **Data Security**: Views can limit access to sensitive data by providing only a subset of the table’s columns and rows.
  
- **Data Abstraction**: Users can interact with a simplified view rather than complex table structures, helping them focus on only the data they need.

- **Logical Data Independence**: Views can mask changes in the underlying table structure from end users. For instance, if columns are added to a base table, the view can remain unaffected.

- **Simplified Queries**: Views can encapsulate complex queries with joins or aggregations, making it easier to retrieve commonly needed data without rewriting complex SQL.

---

### 5. **Limitations of Views**

Despite their benefits, views have some limitations:

- **Performance**: Views do not store data; each time a view is queried, the underlying tables are accessed, which can impact performance, especially for complex views with joins or aggregations.

- **Restrictions on Updates**: Not all views can be updated. Complex views, especially those using joins, aggregations, or subqueries, are generally read-only. Simple views on single tables can often be updated, but changes may have restrictions.

- **Dependence on Base Tables**: If a base table of a view is dropped or its structure is modified significantly, the view may become invalid and need recreation.

---

### 6. **Updating Data through Views**

Views can be updated directly only if they meet certain conditions:

- The view is based on a single table.
- No aggregate functions (e.g., `SUM`, `AVG`) are used.
- The view does not contain `DISTINCT`, `GROUP BY`, `ORDER BY`, or complex expressions.

If these conditions are met, you can perform `INSERT`, `UPDATE`, and `DELETE` operations on the view, which will propagate to the underlying table.

#### Example of Updating a View

Given the simple view:

```sql
CREATE VIEW hr_employees AS
SELECT emp_id, emp_name, salary
FROM employees
WHERE department = 'HR';
```

You can update `hr_employees`:

```sql
UPDATE hr_employees
SET salary = 67000
WHERE emp_name = 'Alice';
```

This change updates the `salary` in the `employees` table.

---

### 7. **Examples of Using Views**

Let’s look at some practical examples to illustrate how views can simplify data retrieval.

#### Example 1: Aggregation View

If you want to display the total salary expenditure for each department, you can create a view for easy access:

```sql
CREATE VIEW dept_salary_totals AS
SELECT department, SUM(salary) AS total_salary
FROM employees
GROUP BY department;
```

Querying `dept_salary_totals` gives:

| department | total_salary |
|------------|--------------|
| HR         | 125000       |
| Finance    | 70000        |
| IT         | 72000        |

#### Example 2: Hiding Sensitive Information

Suppose the `employees` table contains a `ssn` column (Social Security Number) that should not be accessible to all users. A view can be created without this column

#### Example 3: Simplifying Complex Queries

If the `employees` table is joined frequently with a `departments` table, a view can be created for easier querying:

```sql
CREATE VIEW employee_department AS
SELECT e.emp_name, e.salary, d.dept_name
FROM employees e
JOIN departments d ON e.department = d.dept_id;
```
