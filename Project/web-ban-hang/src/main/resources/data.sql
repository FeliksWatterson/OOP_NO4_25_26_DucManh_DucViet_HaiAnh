
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE products;
SET FOREIGN_KEY_CHECKS = 1;


-- ban chay
INSERT INTO products (name, description, price, original_price, category, image_url, hover_image_url, stock, section)
VALUES
('Đèn Ngủ Led Cảm Ứng Thông Minh', 'Đèn ngủ LED cảm ứng thông minh.', 109000, 210000, 'Nhà Cửa & Đời Sống', '/images/products/1.jpg', '/images/products/1.jpg', 99, 'bestSeller'),
('Chảo Chống Dính Đá Hoa Cương 26cm', 'Chảo chống dính đá hoa cương 26cm.', 335000, 460000, 'Nhà Bếp', '/images/products/2.jpg', '/images/products/2.jpg', 88, 'bestSeller'),
('Son Lì Cao Cấp Velvet Rose', 'Son lì cao cấp Velvet Rose.', 210000, 350000, 'Sức Khỏe & Làm Đẹp', '/images/products/3.jpg', '/images/products/3.jpg', 150, 'bestSeller'),
('Dầu gội bồ kết gừng bưởi 500ml Nhà Mơ giảm rụng mọc tóc', 'Dầu gội thảo mộc giảm rụng tóc.', 75000, 170000, 'Sức Khỏe & Làm Đẹp', '/images/products/4.jpg', '/images/products/4.jpg', 120, 'bestSeller');

-- san pham moi
INSERT INTO products (name, description, price, original_price, category, image_url, hover_image_url, stock, section)
VALUES
('Đèn bàn LED thông minh', 'Đèn bàn LED cảm ứng, sạc USB.', 999000, 1500000, 'Nhà Cửa & Đời Sống', '/images/products/denban.jpg', '/images/products/denban.jpg', 40, 'new'),
('Bộ Dưỡng Da Thiên Nhiên', 'Combo dưỡng da thiên nhiên 3 bước.', 1150000, 1870000, 'Sức Khỏe & Làm Đẹp', '/images/products/duongda.jpg', '/images/products/duongda.jpg', 35, 'new'),
('Tai Nghe Không Dây Moondrop Ultra Sonic', 'Tai nghe true wireless âm thanh chi tiết.', 1490000, 2200000, 'Công Nghệ & Thiết Bị Số', '/images/products/true.jpg', '/images/products/true.jpg', 60, 'new'),
('Bình Gốm Decor Nhà Cửa', 'Bình gốm decor phong cách tối giản.', 720000, 1200000, 'Nhà Cửa & Đời Sống', '/images/products/gom.jpg', '/images/products/gom.jpg', 25, 'new');

-- mua nhieu nhat
INSERT INTO products (name, description, price, original_price, category, image_url, hover_image_url, stock, section)
VALUES
('Vòng Tay Thông Minh Theo Dõi Sức Khỏe', 'Smartband theo dõi sức khỏe, pin bền.', 1360000, 1720000, 'Công Nghệ & Thiết Bị Số', '/images/products/vong.jpg', '/images/products/vong.jpg', 50, 'mostBought'),
('Máy Khuếch Tán Tinh Dầu', 'Khuếch tán tinh dầu tạo ẩm nhẹ.', 910000, 1450000, 'Nhà Cửa & Đời Sống', '/images/products/mtd.jpg', '/images/products/mtd.jpg', 45, 'mostBought'),
('Combo Bàn Phím & Chuột Không Dây', 'Combo không dây 2.4GHz tiết kiệm.', 1210000, 1500000, 'Công Nghệ & Thiết Bị Số', '/images/products/banphim.jpg', '/images/products/banphim.jpg', 80, 'mostBought'),
('Máy Xay Sinh Tố Cầm Tay', 'Máy xay cầm tay dung tích 500ml.', 749000, 1200000, 'Gia Dụng & Nhà Bếp', '/images/products/sinhto.jpg', '/images/products/sinhto.jpg', 70, 'mostBought');

-- danh gia cao
INSERT INTO products (name, description, price, original_price, category, image_url, hover_image_url, stock, section)
VALUES
('Đồng Hồ Thời Trang SilverLine', 'Đồng hồ thời trang, chống nước 3ATM.', 2760000, 3320000, 'Thời Trang & Phụ Kiện', '/images/products/silver.jpg', '/images/products/silver.jpg', 20, 'topRated'),
('Bộ Nến Thơm Cao Cấp', 'Bộ nến thơm thư giãn hương tự nhiên.', 560000, 920000, 'Nhà Cửa & Đời Sống', '/images/products/nenthom.jpg', '/images/products/nenthom.jpg', 55, 'topRated'),
('Ba lô Du Lịch Chống Nước AirFit', 'Balo 35L, chất liệu chống thấm.', 1490000, 2200000, 'Du Lịch & Cắm Trại', '/images/products/balo.jpg', '/images/products/balo.jpg', 33, 'topRated'),
('Tinh Chất Vitamin C 50ml', 'Serum Vitamin C sáng da, đều màu.', 420000, 570000, 'Sức Khỏe & Làm Đẹp', '/images/products/vitamin.jpg', '/images/products/vitamin.jpg', 90, 'topRated');

-- flash sale
INSERT INTO products (name, description, price, original_price, category, image_url, hover_image_url, stock, section)
VALUES
('Combo dầu gội, dầu xả, sữa tắm Old Spice Swagger', 'Bộ chăm sóc cơ thể cho nam giới.', 1250000, 1600000, 'Sức Khỏe & Làm Đẹp', '/images/products/shampoo.jpg', '/images/products/shampoo.jpg', 36, 'flashSale'),
('SmartWatch Cubitt AURA Pro', 'Đồng hồ thông minh nhiều tính năng, GPS.', 2320000, 2900000, 'Công Nghệ & Thiết Bị Số', '/images/products/cubitt.jpg', '/images/products/cubitt.jpg', 36, 'flashSale');

-- goi y
INSERT INTO products (name, description, price, original_price, category, image_url, hover_image_url, stock, section)
VALUES
('Sách - Combo tự học + tập viết Tiếng Nga', 'Combo tự học + tập viết Tiếng Nga.', 75000, 89000, 'Sách Học Tập', '/images/products/nga2.jpg', '/images/products/nga1.jpg', 120, 'suggested'),
('Máy khuyếch tán tinh dầu', 'Máy khuyếch tán tinh dầu gia dụng.', 104000, 210000, 'Nhà Cửa & Đời Sống', '/images/products/td1.jpg', '/images/products/td2.jpg', 150, 'suggested'),
('Combo 3 hộp 300 miếng lau kính', 'Khăn lau kính chuyên dụng.', 45000, 92000, 'Phụ Kiện & Trang Sức', '/images/products/kinh1.jpg', '/images/products/kinh2.jpg', 200, 'suggested'),
('Ốp Điện Thoại Mềm Chống Sốc', 'Ốp mềm chống sốc, bền bỉ.', 38000, 55000, 'Điện thoại & Phụ kiện', '/images/products/op1.jpg', '/images/products/op2.jpg', 300, 'suggested'),
('Sách - Thiên văn học trực quan', 'Sách minh họa trực quan về thiên văn.', 427000, 550000, 'Sách Tiếng Việt', '/images/products/tv1.jpg', '/images/products/tv2.jpg', 50, 'suggested'),
('Kẹo Sữa Trà Xanh Matcha UHA', 'Kẹo sữa vị matcha UHA.', 34000, 45000, 'Đồ ăn vặt', '/images/products/keo1.jpg', '/images/products/keo2.jpg', 400, 'suggested'),
('Máy Nghe Nhạc FiiO SNOWSKY Echo Mini', 'Máy nghe nhạc nhỏ gọn, chất âm tốt.', 1349000, 1700000, 'Thiết Bị Điện Tử', '/images/products/nhac1.jpg', '/images/products/nhac2.jpg', 45, 'suggested'),
('IEM TangZu Wan''er SG 2 Jade Dragon', 'Tai nghe IEM tầm giá phổ thông.', 521000, 660000, 'Thiết Bị Điện Tử', '/images/products/waner1.jpg', '/images/products/waner2.jpg', 120, 'suggested'),
('Sticker tem thư series Memories', 'Sticker trang trí phong cách vintage.', 34000, 76000, 'Nhà Sách Online', '/images/products/tem1.jpg', '/images/products/tem2.jpg', 500, 'suggested'),
('Vợt Kumpoo K520 Pro chính hãng', 'Vợt cầu lông cân bằng, dễ chơi.', 458000, 650000, 'Thể Thao & Du Lịch', '/images/products/ku1.jpg', '/images/products/ku2.jpg', 60, 'suggested'),
('TPCN Revive KSM-66 Ashwagandha', 'Thực phẩm bổ sung hỗ trợ sức khỏe.', 540000, 570000, 'Sức Khỏe & Làm Đẹp', '/images/products/sam1.jpg', '/images/products/sam2.jpg', 80, 'suggested'),
('Áo Polo MYO Vải Cotton To The Moon', 'Áo polo nam vải cotton.', 350000, 390000, 'Thời Trang Nam', '/images/products/moon1.jpg', '/images/products/moon2.jpg', 90, 'suggested');
