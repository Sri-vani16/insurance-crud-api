CREATE TABLE IF NOT EXISTS QUOTE (
    id BIGINT PRIMARY KEY,
    customer_id BIGINT,
    product VARCHAR(255),
    premium DECIMAL(10, 2),
    status VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS POLICY (
    policy_number VARCHAR(50) PRIMARY KEY,
    quote_id BIGINT,
    customer_id BIGINT,
    issue_date DATE,
    expiry_date DATE,
    status VARCHAR(50),
    sum_insured DECIMAL(15, 2),
    premium DECIMAL(10, 2),
    FOREIGN KEY (quote_id) REFERENCES QUOTE(id)
);

CREATE TABLE IF NOT EXISTS CUSTOMER (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(50),
    address VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS BILLING_INFO (
    id BIGINT PRIMARY KEY,
    policy_number VARCHAR(50),
    billing_address VARCHAR(255),
    payment_method VARCHAR(100),
    FOREIGN KEY (policy_number) REFERENCES POLICY(policy_number)
);
