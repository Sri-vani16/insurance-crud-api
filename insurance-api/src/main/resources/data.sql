-- Sample data for QUOTE
INSERT INTO QUOTE (id, customer_id, product, premium, status)
VALUES 
    (1, 1001, 'Auto Insurance', 3500.00, 'CREATED'),
    (2, 1002, 'Health Insurance', 5000.00, 'APPROVED');

-- Sample data for CUSTOMER
INSERT INTO CUSTOMER (id, name, email, phone, address)
VALUES 
    (1001, 'John Doe', 'john.doe@email.com', '1234567890', '123 Main St'),
    (1002, 'Jane Smith', 'jane.smith@email.com', '0987654321', '456 Elm St');

-- Sample data for POLICY
INSERT INTO POLICY (policy_number, quote_id, customer_id, issue_date, expiry_date, status, sum_insured, premium)
VALUES 
    ('POL1001', 1, 1001, '2024-01-01', '2025-01-01', 'ACTIVE', 100000.00, 3500.00),
    ('POL1002', 2, 1002, '2024-02-01', '2025-02-01', 'ACTIVE', 200000.00, 5000.00);

-- Sample data for BILLING_INFO
INSERT INTO BILLING_INFO (id, policy_number, billing_address, payment_method)
VALUES 
    (1, 'POL1001', '123 Main St', 'Credit Card'),
    (2, 'POL1002', '456 Elm St', 'Bank Transfer');
