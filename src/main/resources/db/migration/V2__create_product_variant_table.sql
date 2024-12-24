CREATE TABLE IF NOT EXISTS  product_variant (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255),
    sku VARCHAR(255),
    requires_shipping BOOLEAN,
    taxable BOOLEAN,
    featured_image TEXT,
    available BOOLEAN,
    price NUMERIC(15, 2),
    grams NUMERIC(15, 2),
    compare_at_price NUMERIC(15, 2),
    position INTEGER,
    product_id BIGSERIAL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);