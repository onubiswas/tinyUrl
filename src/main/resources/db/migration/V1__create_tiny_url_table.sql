CREATE TABLE url_mapping (
    id              SERIAL PRIMARY KEY,
    original_url    VARCHAR(2000) UNIQUE,
    shortened_url   VARCHAR(255) UNIQUE,
    created_at      VARCHAR(50),
    expired_at      VARCHAR(50)
);
