CREATE TABLE IF NOT EXISTS users
(
    id        SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL,
    password  VARCHAR(60)     NOT NULL,
    role      VARCHAR(50)  NOT NULL
);
