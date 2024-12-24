CREATE TABLE IF NOT EXISTS products (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255),
    handle VARCHAR(255),
    published_at TIMESTAMP,
    vendor VARCHAR(255),
    product_type VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);