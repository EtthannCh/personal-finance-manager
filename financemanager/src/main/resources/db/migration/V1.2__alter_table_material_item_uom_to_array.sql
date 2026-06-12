alter table material_item
drop CONSTRAINT if exists material_item_uom_fk;

alter table material_item
alter column uom TYPE INTEGER[] using array[uom]::INTEGER[];

alter table material_item
DROP column if exists uom_name ;

ALTER TABLE material_item
ADD CONSTRAINT material_item_name_uk UNIQUE(name);