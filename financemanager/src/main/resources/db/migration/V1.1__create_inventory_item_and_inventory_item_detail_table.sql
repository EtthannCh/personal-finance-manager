CREATE TABLE IF NOT EXISTS inventory_item(
    id serial primary key,
    material_item_id NUMERIC not null,
    material_item_name VARCHAR(250) not null,
    qty NUMERIC(8,2) not null,
    created_by_id uuid not null,
    created_by VARCHAR(250) not null,
    created_at timestamp not null,
    last_updated_by_id UUID not null,
    last_updated_by VARCHAR(250) not null,
    last_updated_at timestamp not null,
    prev_updated_by_id UUID,
    prev_updated_by VARCHAR(250),
    prev_updated_at timestamp
);