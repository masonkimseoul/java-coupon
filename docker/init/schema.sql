CREATE DATABASE IF NOT EXISTS coupon;
USE coupon;

CREATE TABLE IF NOT EXISTS coupon(
    id                      BIGINT      NOT NULL AUTO_INCREMENT,
    `name`                    VARCHAR(255),
    category                ENUM('FASHION', 'ELECTRONICS', 'FURNITURE', 'FOODS') NOT NULL,
    discount_amount         INT,
    minimum_order_amount    INT,
    start_at                DATETIME    NOT NULL,
    end_at                  DATETIME    NOT NULL,
    PRIMARY KEY(id)
);
