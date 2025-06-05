INSERT INTO categories (name)
VALUES ('Dairy'),
       ('Produce'),
       ('Bakery'),
       ('Snacks'),
       ('Beverages'),
       ('Meat'),
       ('Seafood');

INSERT INTO products (name, price, description, category_id)
VALUES ('Whole Milk - 1 Gallon', 3.49, 'Grade A pasteurized whole milk. Vitamin D added.', 1),
       ('Bananas - 1 lb', 0.59, 'Fresh yellow bananas, perfect for smoothies or snacks.', 2),
       ('Sourdough Bread Loaf', 4.29, 'Artisan sourdough bread with a crispy crust.', 3),
       ('Potato Chips - Classic', 2.99, 'Crispy salted potato chips, 10 oz bag.', 4),
       ('Orange Juice - No Pulp', 3.99, '100% pure orange juice, no added sugar.', 5),
       ('Chicken Breast - Boneless Skinless', 6.49, 'Fresh boneless chicken breasts, about 1.5 lbs.', 6),
       ('Atlantic Salmon Fillet', 9.99, 'Fresh farm-raised Atlantic salmon, skin-on.', 7),
       ('Greek Yogurt - Strawberry', 1.29, 'Strawberry flavored non-fat Greek yogurt, 5 oz.', 1),
       ('Baby Carrots - 1 lb Bag', 1.79, 'Washed and peeled baby carrots, snack-size.', 2),
       ('Chocolate Chip Cookies - 12 pack', 3.49, 'Soft baked chocolate chip cookies.', 4);
