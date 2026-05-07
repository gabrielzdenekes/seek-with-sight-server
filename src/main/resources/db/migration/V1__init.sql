CREATE TABLE flyway_test (
    id BIGSERIAL PRIMARY KEY,
    message VARCHAR(100) NOT NULL,
    created TIMESTAMP DEFAULT NOW()
);

INSERT INTO flyway_test (message) VALUES ('Flyway works correctly');