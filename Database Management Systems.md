#### 1. **Introduction to Database Management Systems (DBMS)**

A Database Management System (DBMS) is software that interacts with users, applications, and the database to capture and analyze data. The main purpose of a DBMS is to provide a way to store, retrieve, and manage data efficiently.

- **Types of DBMS**:
  - [**Relational DBMS (RDBMS)**](Relational%20DBMS.md): Uses structured tables with rows and columns (e.g., MySQL, PostgreSQL).
  - **NoSQL DBMS**: Supports unstructured data and flexibility in formats (e.g., MongoDB, Cassandra).
  - **Object-Oriented DBMS (OODBMS)**: Stores data in the form of objects (e.g., ObjectDB).
  - **Hierarchical and Network DBMS**: Less commonly used; data is structured hierarchically or in a network format (e.g., IBM Information Management System).

### [Structure and Functionality of a (DBMS)](Structure%20and%20Functionality%20of%20a%20(DBMS).md):
A Database Management System (DBMS) is a software tool that stores, manages, and retrieves data in an organized way. To understand a DBMS, we need to explore its internal structure, its schema, and the various functionalities it provides. Here’s a breakdown of these aspects to give you a comprehensive view.

---

#### 2. **Relational Databases and SQL**

**Relational databases** store data in tables with rows and columns, where tables can be linked by keys. Structured Query Language (SQL) is used to manage and query data in relational databases.

- **Example**: Creating a table and inserting data.

```sql
CREATE TABLE employees (
    id INT PRIMARY KEY,
    name VARCHAR(50),
    position VARCHAR(50),
    salary DECIMAL
);

INSERT INTO employees (id, name, position, salary) VALUES
(1, 'Alice', 'Developer', 70000),
(2, 'Bob', 'Designer', 65000),
(3, 'Charlie', 'Manager', 80000);
```

- **Querying Data**:

```sql
SELECT * FROM employees WHERE position = 'Developer';
```

This query retrieves all employees with the position 'Developer'.

---

#### 3. **NoSQL Databases**

NoSQL databases handle large volumes of unstructured data. They are schema-less and provide flexibility with data storage.

- **Types of NoSQL Databases**:
  - **Document-Oriented** (e.g., MongoDB): Stores data in JSON-like documents.
  - **Key-Value Stores** (e.g., Redis): Simple key-value pairs.
  - **Column-Family Stores** (e.g., Cassandra): Stores data in columns.
  - **Graph Databases** (e.g., Neo4j): Uses graph structures with nodes and edges.

- **Example in MongoDB** (document-based NoSQL):

```json
// Inserting a document in a MongoDB collection
{
    "_id": 1,
    "name": "Alice",
    "position": "Developer",
    "salary": 70000
}

// Query to find a developer
db.employees.find({ "position": "Developer" });
```

---

#### 4. **Normalization and Data Integrity in Relational Databases**

Normalization organizes tables to minimize redundancy.

- **First Normal Form (1NF)**: Ensures each column holds atomic values.
- **Second Normal Form (2NF)**: No partial dependency on any column other than the primary key.
- **Third Normal Form (3NF)**: All columns depend only on the primary key.

**Example**: Normalizing a table for a school database might involve splitting a table of students and their subjects into separate tables with `student_id` linking them.

---

#### 5. **Security in DBMS**

Database security involves access control, authentication, and encryption.

- **Example of Access Control**:

```sql
CREATE USER 'dev_user'@'localhost' IDENTIFIED BY 'password';
GRANT SELECT ON employees TO 'dev_user'@'localhost';
```

This restricts `dev_user` to only query (`SELECT`) the `employees` table.

---

#### 6. **Backup and Recovery**

Regular backups and a recovery plan are essential for protecting data.

- **Example Backup in MySQL**:

```bash
mysqldump -u username -p database_name > backup.sql
```

This command creates a backup of `database_name` in `backup.sql`.