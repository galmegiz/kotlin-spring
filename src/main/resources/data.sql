CREATE TABLE employees(
    emp_no INTEGER PRIMARY KEY,
    birth_date DATE,
    first_name VARCHAR(14),
    last_name VARCHAR(16),
    gender ENUM('M', 'F'),
    hire_date DATE
);