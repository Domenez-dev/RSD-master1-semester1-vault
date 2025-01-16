### Common Uses of `ALTER TABLE`

#### 1. **Add a New Column**

To add a new column to an existing table:

```sql
ALTER TABLE employees
ADD job_title VARCHAR(50);
```

The `employees` table now has an additional column `job_title`.

---

#### 2. **Modify an Existing Column**

To change the data type or constraints of a column:

- Change the data type:

```sql
ALTER TABLE employees
MODIFY salary DECIMAL(12, 2);
```

- Add a `NOT NULL` constraint:

```sql
ALTER TABLE employees
MODIFY job_title VARCHAR(50) NOT NULL;
```

---

#### 3. **Rename a Column**

To rename an existing column (supported in some DBMSs like MySQL):

```sql
ALTER TABLE employees
RENAME COLUMN job_title TO position;
```

---

#### 4. **Drop a Column**

To remove an unnecessary column:

```sql
ALTER TABLE employees
DROP COLUMN position;
```

---

#### 5. **Add or Drop Constraints**

- Add a new constraint (e.g., foreign key):

```sql
ALTER TABLE orders
ADD CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customers(customer_id);
```

- Drop an existing constraint:

```sql
ALTER TABLE orders
DROP CONSTRAINT fk_customer;
```

---

#### 6. **Rename a Table**

To rename the table itself:

```sql
ALTER TABLE employees
RENAME TO staff;
```

---

### Example Workflow

Let’s assume you initially create this table:

```sql
CREATE TABLE products (
    product_id INT PRIMARY KEY,
    product_name VARCHAR(100),
    price DECIMAL(10, 2)
);
```

1. Add a column for stock quantity:

```sql
ALTER TABLE products
ADD stock INT DEFAULT 0;
```

2. Modify the `price` column to include a `NOT NULL` constraint:

```sql
ALTER TABLE products
MODIFY price DECIMAL(10, 2) NOT NULL;
```

3. Rename the table:

```sql
ALTER TABLE products
RENAME TO inventory;
```

#ASGBD