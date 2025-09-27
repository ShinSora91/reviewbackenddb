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

select * from cart_item;
select * from product;

insert into product values(1, 'https://lovecoco.co.kr/web/product/big/201911/cd8e3ebfd07b005cdefb8351e6b9e640.jpg', '퀼팅 패딩 베스트', '45000');
insert into product values(2,'https://lovecoco.co.kr/web/product/big/201911/2d55ba2ee6574578cc7614f631f6f6bc.jpg','체크 멜빵바지','52000');
insert into product values(3,'https://lovecoco.co.kr/web/product/big/201911/2d55ba2ee6574578cc7614f631f6f6bc.jpg','체크 더플코트','58000');
insert into product values(4,'https://lovecoco.co.kr/web/product/big/202011/ee85dfa110ea4bb5fc5e35e7c1fa5fe0.jpg','스트라이프 티셔츠','38000');
insert into product values(5,'https://lovecoco.co.kr/web/product/big/202111/69caf2014e3fcf78d286909b6fd58f94.jpg','테리 후드 올인원','61000');
insert into product values(6,'https://lovecoco.co.kr/web/product/big/20191127/fb4b5f65bfc21de1db78346891dc4d05.jpg','후드 베스트','50000');
insert into product values(7,'https://lovecoco.co.kr/web/product/big/20200105/953e3ae7837739ae2d1637b93a952039.jpg','하트 자수 맨투맨','42000');