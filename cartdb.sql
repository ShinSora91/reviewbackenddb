use mysql_example;
CREATE TABLE IF NOT EXISTS cart_item (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  session_id VARCHAR(100) NOT NULL,
  product_id BIGINT NOT NULL,
  qty INT NOT NULL,
  price_snap INT NOT NULL,
  CONSTRAINT uk_session_product UNIQUE (session_id, product_id),
  CONSTRAINT fk_cartitem_product FOREIGN KEY (product_id) REFERENCES product(id)
) ENGINE=InnoDB;

CREATE INDEX idx_cart_session ON cart_item(session_id);

#나중에 로그인으로 승격하기 위해 필요한 구문
ALTER TABLE cart_item ADD COLUMN user_id BIGINT NULL;
CREATE INDEX idx_cart_user ON cart_item(user_id);