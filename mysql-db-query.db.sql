CREATE TABLE `customer` (
  `customer_id` int PRIMARY KEY AUTO_INCREMENT,
  `customer_name` varchar(255),
  `order_id` int
);

CREATE TABLE `order_items` (
  `order_id` int,
  `product_id` int,
  `quantity` int DEFAULT 1
);

CREATE TABLE `order` (
  `order_id` int PRIMARY KEY,
  `customer_id` int UNIQUE NOT NULL,
  `status` varchar(255),
  `created_at` varchar(255) COMMENT 'When order created'
);

CREATE TABLE `products` (
  `product_id` int PRIMARY KEY,
  `product_name` varchar(255),
  `seller_id` int NOT NULL,
  `mrp` int,
  `quantity` int
);

CREATE TABLE `seller` (
  `seller_id` int PRIMARY KEY AUTO_INCREMENT,
  `seller_name` varchar(255)
);

ALTER TABLE `customer` ADD FOREIGN KEY (`customer_id`) REFERENCES `order` (`customer_id`);

ALTER TABLE `order_items` ADD FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`);

ALTER TABLE `order_items` ADD FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`);

ALTER TABLE `products` ADD FOREIGN KEY (`seller_id`) REFERENCES `seller` (`seller_id`);
