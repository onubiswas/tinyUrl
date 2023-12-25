CREATE TABLE url_mapping (
    id              SERIAL PRIMARY KEY,
    original_url    VARCHAR(2000) NOT NULL UNIQUE,
    shortened_url   VARCHAR(255) NOT NULL UNIQUE,
    created_at      VARCHAR(50),
    expired_at      VARCHAR(50)
);

