CREATE TABLE IF NOT EXISTS inventory_item(
    id serial primary key,
    material_item_id int4 not null,
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
    prev_updated_at timestamp,

    CONSTRAINT inventory_item_material_fk FOREIGN KEY (material_item_id) REFERENCES material_item(id)
);

CREATE TABLE IF NOT EXISTS inventory_item_detail(
    id serial primary key,
    inventory_item_id int4 not null,
    supplier_id int4 not null,
    supplied_date timestamp not null,
    balance_qty NUMERIC(5,2) not null,
    reserve_qty NUMERIC(5,2) not null,
    price NUMERIC(8,2) not null,
    created_by_id uuid not null,
    created_by VARCHAR(250) not null,
    created_at timestamp not null,
    last_updated_by_id UUID not null,
    last_updated_by VARCHAR(250) not null,
    last_updated_at timestamp not null,
    prev_updated_by_id UUID,
    prev_updated_by VARCHAR(250),
    prev_updated_at timestamp,
    CONSTRAINT inventory_item_detail_inventory_item_fk FOREIGN KEY (inventory_item_id) REFERENCES inventory_item(id)
);