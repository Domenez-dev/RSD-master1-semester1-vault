### Views in Database Management Systems (DBMS)

A **view** is a virtual table in a DBMS that provides a specific way to look at data from one or more tables. Views don’t store data; they retrieve it dynamically from underlying tables, offering flexibility, security, and simplified access to complex data.

#### Why Use Views?

- **Data Security**: Restrict access to specific data without exposing the entire table.
- **Simplified Queries**: Encapsulate complex queries and present a simplified data view.
- **Data Abstraction**: Users interact with a customized view of data without needing to know table structures.

---

### Creating and Using Views

To create a view:

```sql
CREATE VIEW view_name AS
SELECT column1, column2
FROM table_name
WHERE condition;
```

For example, to show only employees in HR:

```sql
CREATE VIEW hr_employees AS
SELECT emp_id, emp_name, salary
FROM employees
WHERE department = 'HR';
```

You can now query `hr_employees`:

```sql
SELECT * FROM hr_employees;
```

---

### Types of Views

1. **Simple Views**: Based on a single table, often updatable.
2. **Complex Views**: Based on multiple tables with joins or aggregates, generally read-only.

---

### Updating Data through Views

Updating through views is possible for simple views without aggregates, joins, or complex expressions. For example:

```sql
UPDATE hr_employees
SET salary = 67000
WHERE emp_name = 'Alice';
```

This change will update the underlying `employees` table.

---

### Advantages and Limitations

**Advantages**:
- Security and restricted data access
- Simplification of complex queries
- Logical independence from table structure changes

**Limitations**:
- Potential performance impact (views don’t store data)
- Limited update capability, especially with complex views

---

Views are a powerful tool for managing data access and simplifying query complexity, providing users with customized perspectives on underlying tables without affecting the data itself.