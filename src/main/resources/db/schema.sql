CREATE TABLE employees(
    emp_no INTEGER AUTO_INCREMENT PRIMARY KEY,
    birth_date DATE,
    first_name VARCHAR(14),
    last_name VARCHAR(16),
    gender ENUM('M', 'F'),
    hire_date DATE
);

CREATE TABLE deleted_employees(
    emp_no INTEGER PRIMARY KEY,
    birth_date DATE,
    first_name VARCHAR(14),
    last_name VARCHAR(16),
    gender ENUM('M', 'F'),
    hire_date DATE,
    deleted_date DATE
);

CREATE TABLE service_user(
    id BIGINT PRIMARY KEY,
    user_name VARCHAR(20),
    password VARCHAR(20),
    email VARCHAR(30),
    last_login_at DATETIME,
    deleted_at DATE
);