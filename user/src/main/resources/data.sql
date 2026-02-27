INSERT INTO role (name)
VALUES
    ('ADMIN'),
    ('OWNER'),
    ('CLIENT'),
    ('EMPLOYEE');

-- Insertar usuario administrador
-- Email: admin@example.com
-- Password: admin123 (BCrypt encoded)
INSERT INTO user (name, last_name, phone, email, password, role_id)
VALUES
    ('Admin', 'System', '3001234567', 'admin@example.com', '$2a$10$5baIgEkTdbT9ZuZfrZUKmuN7azTE7neWbzPNEuItpY1QRYF.54FwO', 1);
