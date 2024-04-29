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
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(20),
    password VARCHAR(100),
    email VARCHAR(30),
    last_login_at DATETIME(6) DEFAULT NOW(6),
    deleted_at DATE DEFAULT NULL
);