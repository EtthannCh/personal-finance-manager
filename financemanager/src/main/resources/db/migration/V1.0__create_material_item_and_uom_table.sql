CREATE TABLE IF NOT EXISTS unit_of_measure(
    id serial primary key,
    name VARCHAR(250) not null UNIQUE,
    code VARCHAR(250) not null UNIQUE,
    created_by VARCHAR(250) not null,
    created_by_id UUID not null,
    created_at timestamp not null,
    last_updated_by_id UUID not null,
    last_updated_by VARCHAR(250) not null,
    last_updated_at timestamp not null,
    prev_updated_by_id UUID,
    prev_updated_by VARCHAR(250),
    prev_updated_at timestamp
);

CREATE TABLE IF NOT EXISTS material_item(
    id serial primary key,
    name VARCHAR(250) not null,
    description VARCHAR(250) not null,
    uom int not null,
    uom_name VARCHAR(250) not null,
    created_by VARCHAR(250) not null,
    created_by_id UUID not null,
    created_at timestamp not null,
    last_updated_by_id UUID not null,
    last_updated_by VARCHAR(250) not null,
    last_updated_at timestamp not null,
    prev_updated_by_id UUID,
    prev_updated_by VARCHAR(250),
    prev_updated_at timestamp,

    CONSTRAINT material_item_uom_fk FOREIGN KEY (uom) REFERENCES unit_of_measure(id)
);