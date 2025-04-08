### 1. **Connect as "System"**
   - **Action**: Connect to the database as the `SYSTEM` user.
   - **Check the "DICT" catalog**:
     ```sql
     SELECT * FROM DICT;
     ```
   - **Count instances**:
     ```sql
     SELECT COUNT(*) FROM DICT;
     ```
   - **Structure of "DICT"**:
     ```sql
     DESCRIBE DICT;
     ```

---

### 2. **Roles and Structures of Specific Tables/Views**
   - **Action**: For each table/view, provide its purpose and structure.
     ```sql
     -- For ALL_TAB_COLUMNS
     DESCRIBE ALL_TAB_COLUMNS;

     -- For USER_USERS
     DESCRIBE USER_USERS;

     -- For ALL_CONSTRAINTS
     DESCRIBE ALL_CONSTRAINTS;

     -- For USER_TAB_PRIVS
     DESCRIBE USER_TAB_PRIVS;
     ```

---

### 3. **Find Connected Username**
   - **Query**:
     ```sql
     SELECT USER FROM DUAL;
     ```

---

### 4. **Compare ALL_TAB_COLUMNS and USER_TAB_COLUMNS**
   - **Compare structure**:
     ```sql
     DESCRIBE ALL_TAB_COLUMNS;
     DESCRIBE USER_TAB_COLUMNS;
     ```
   - **Compare content** (e.g., specific columns or filters):
     ```sql
     SELECT * FROM ALL_TAB_COLUMNS MINUS SELECT * FROM USER_TAB_COLUMNS;
     SELECT * FROM USER_TAB_COLUMNS MINUS SELECT * FROM ALL_TAB_COLUMNS;
     ```

---

### 5. **Verify TP1 Tables**
   - **Action**: Check if the tables exist.
     ```sql
     SELECT * FROM USER_TABLES WHERE TABLE_NAME IN ('TABLE_NAME_1', 'TABLE_NAME_2');
     ```
   - **List details**:
     ```sql
     SELECT * FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'TABLE_NAME';
     ```

---

### 6. **List Tables for Specific Users**
   - **For "SYSTEM"**:
     ```sql
     SELECT * FROM ALL_TABLES WHERE OWNER = 'SYSTEM';
     ```
   - **For "GererEquipe"**:
     ```sql
     SELECT * FROM ALL_TABLES WHERE OWNER = 'GEREREQUIPE';
     ```

---

### 7. **Attributes of EQUIPE and COUREUR**
   - **Query**:
     ```sql
     SELECT * FROM USER_TAB_COLUMNS WHERE TABLE_NAME IN ('EQUIPE', 'COUREUR');
     ```

---

### 8. **Verify Foreign Key Reference**
   - **Query**:
     ```sql
     SELECT * FROM USER_CONSTRAINTS WHERE CONSTRAINT_TYPE = 'R' AND TABLE_NAME IN ('COUREUR', 'EQUIPE');
     ```

---

### 9. **List Constraints**
   - **Query**:
     ```sql
     SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME IN ('TABLE_1', 'TABLE_2');
     ```

---

### 10. **Recreate PARTICIPER Table**
   - **Query for details**:
     ```sql
     SELECT DBMS_METADATA.GET_DDL('TABLE', 'PARTICIPER') FROM DUAL;
     ```

---

### 11. **Privileges for GestionCompet**
   - **Query**:
     ```sql
     SELECT * FROM USER_TAB_PRIVS WHERE GRANTEE = 'GESTIONCOMPET';
     ```

---

### 12. **Roles for GererEquipe**
   - **Query**:
     ```sql
     SELECT * FROM DBA_ROLE_PRIVS WHERE GRANTEE = 'GEREREQUIPE';
     ```

---

### 13. **Objects for GestionCompet**
   - **Query**:
     ```sql
     SELECT * FROM ALL_OBJECTS WHERE OWNER = 'GESTIONCOMPET';
     ```

---

### 14. **Find Owner of ETAPE Table**
   - **Query**:
     ```sql
     SELECT OWNER FROM ALL_TABLES WHERE TABLE_NAME = 'ETAPE';
     ```

---

### 15. **Find Size of ETAPE Table**
   - **Query**:
     ```sql
     SELECT SEGMENT_NAME, BYTES/1024 AS SIZE_KB
     FROM USER_SEGMENTS
     WHERE SEGMENT_NAME = 'ETAPE';
     ```

---

### 16. **Verify Effects of Data Definition Commands**
   - **Action**: Check changes in the data dictionary using:
     ```sql
     SELECT * FROM USER_OBJECTS WHERE OBJECT_NAME = 'OBJECT_NAME';
     ```

---
