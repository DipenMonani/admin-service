CREATE TABLE IF NOT EXISTS product_image (
    id BIGSERIAL PRIMARY KEY,
    position INTEGER,
    product_id BIGSERIAL,
    src VARCHAR(255),
    width INTEGER,
    height INTEGER,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT fk_product_images_product_id FOREIGN KEY (product_id) REFERENCES products(id)
);