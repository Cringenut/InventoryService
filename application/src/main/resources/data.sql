INSERT INTO inventory_item (sku, available, reserved, version)
VALUES ('SKU-1', 6, 2, 0);

INSERT INTO inventory_item (sku, available, reserved, version)
VALUES ('SKU-2', 7, 0, 0);

INSERT INTO domain_event (sku, type, payload, created_at)
VALUES
    ('SKU-1', 'ItemReservedEvent', '{"sku":"SKU-1","qty":2}', CURRENT_TIMESTAMP),

    ('SKU-2', 'ItemReservedEvent', '{"sku":"SKU-2","qty":3}', CURRENT_TIMESTAMP);
