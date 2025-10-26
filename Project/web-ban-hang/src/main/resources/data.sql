SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE products;
SET FOREIGN_KEY_CHECKS = 1;


-- ban chay
INSERT INTO products (name, description, price, original_price, category, image_url, hover_image_url, stock, section)
VALUES
('Đèn Ngủ Led Cảm Ứng Thông Minh', 'Đèn tích hợp cảm biến ánh sáng hoạt động với 2 chế độ.
                                    \nChế độ On: Đèn luôn sáng sau khi cấp nguồn
                                    \nChế độ Auto: Đèn chỉ sáng khi cấp nguồn và cường độ ánh sáng <15 lux
                                    \nỨng dụng: Sử dụng làm đèn ngủ, đèn chiếu sáng chân tường,...', 
                                    109000, 210000, 'Nhà Cửa & Đời Sống', '/images/products/1.jpg', '/images/products/1.jpg', 99, 'bestSeller'),

('Chảo Chống Dính Đá Hoa Cương 26cm', 'Bề mặt chảo chống dính được tráng đá hoa cương siêu bền, không phản ứng với thức ăn, an toàn sức khỏe.
                                    \nĐộ dày 2,686mm được đúc bằng nhôm nguyên khối chắc chắn, chịu lực cao.
                                    \nBề mặt phủ chống dính đá hoa cương bền đẹp, không bao giờ bị bong tróc hay mất chất chống dính như các loại chảo thường, giúp bạn yên tâm sử dụng lâu dài.', 
                                    335000, 460000, 'Nhà Bếp', '/images/products/2.jpg', '/images/products/2.jpg', 88, 'bestSeller'),

('Son Lì Cao Cấp Velvet Rose', 'Lấy cảm hứng từ vẻ đẹp kiêu sa, quyến rũ của những đóa hồng quyền lực, son thỏi cao cấp Velvet Rose là sự kết hợp hoàn hảo giữa thiết kế sang trọng và chất son đỉnh cao, giúp tôn vinh nét đẹp tự nhiên và hiện đại của người phụ nữ. Đây không chỉ là một thỏi son, mà còn là một tuyên ngôn sắc đẹp dành riêng cho bạn.', 
                                    210000, 350000, 'Sức Khỏe & Làm Đẹp', '/images/products/3.jpg', '/images/products/3.jpg', 150, 'bestSeller'),

('Combo Dầu Gội Bưởi, Bồ Kết, Dầu Xả Cafuné 480ml giúp tóc chắc khỏe chống gãy rụng cho tóc', 'Bộ sản phẩm chăm sóc tóc từ The Cafuné hoàn thiện quy trình gội – xả – dưỡng hằng ngày. 
                                                                                            \nDầu gội Bưởi & Bồ Kết làm sạch bụi bẩn, dầu thừa; dầu xả Bưởi bổ sung ẩm cho tóc mềm mượt. ', 
                                                                                            179000, 188000, 'Sức Khỏe & Làm Đẹp', '/images/products/4.jpg', '/images/products/4.jpg', 120, 'bestSeller');

-- san pham moi
INSERT INTO products (name, description, price, original_price, category, image_url, hover_image_url, stock, section)
VALUES
('Đèn bàn LED thông minh', 'Đèn bàn LED thông minh tích hợp nhiều tính năng thông minh và hiện đại, sử dụng chip Led có hiệu suất ánh sáng cao, cho ánh sáng trung thực tự nhiên và hỗ trợ bảo vệ mắt một cách tối đa', 
                            999000, 1500000, 'Nhà Cửa & Đời Sống', '/images/products/denban.jpg', '/images/products/denban.jpg', 40, 'new'),

('Bộ Dưỡng Da Thiên Nhiên', 'Bộ sản phẩm, đầy đủ các công đoạn từ làm sạch đến dưỡng da, giải quyết 2 vấn đề về da mà nhiều nàng đang gặp phải, cho nàng một làn da khỏe mạnh, rạng ngời sức sống.', 
                            1150000, 1870000, 'Sức Khỏe & Làm Đẹp', '/images/products/duongda.jpg', '/images/products/duongda.jpg', 35, 'new'),

('Tai Nghe Không Dây Moondrop Ultra Sonic', 'Sau Block, Moondrop tiếp tục trình làng chiếc tai nghe True Wireless mới có tên gọi là Ultrasonic. Sản phẩm được trang bị công nghệ Bluetooth 5.3 tiên tiến, hỗ trợ các codec âm thanh chất lượng cao như LDAC và LC3. Đặc biệt, nó còn tích hợp tính năng khử tiếng ồn chủ động (ANC) và có chế độ game mode với độ trễ khoảng 55ms, đáp ứng mọi nhu cầu đa dạng của người dùng.', 
                                            1490000, 2200000, 'Công Nghệ & Thiết Bị Số', '/images/products/true.jpg', '/images/products/true.jpg', 60, 'new'),

('Bình Gốm Decor Nhà Cửa', 'Bình gốm decor là sản phẩm phù hợp với nhiều yêu cầu mục đích lựa chọn chậu kết hợp cây, hoa trong trang trí nội thất. Bề mặt nhẵn mịn của bình hoa kết hợp cây, hoa khi trang trí trong nhà, ban công, sân vườn như tạo một bản hòa tấu nhẹ nhàng của thiên nhiên.', 
                            720000, 1200000, 'Nhà Cửa & Đời Sống', '/images/products/gom.jpg', '/images/products/gom.jpg', 25, 'new');

-- mua nhieu nhat
INSERT INTO products (name, description, price, original_price, category, image_url, hover_image_url, stock, section)
VALUES
('Vòng Tay Thông Minh Theo Dõi Sức Khỏe',   'Chiếc vòng tay thông minh theo dõi sức khoẻ còn có chức năng đo nhịp tim một cách chính xác, phản ánh tuần hoàn của máu và giúp bạn nắm rõ sức khỏe. Từ đó, cải thiện sức khỏe nhờ tập luyện và ăn uống.
                                            \nChiếc vòng tay sức khoẻ thông minh sẽ nắm rõ được bạn đã đi bao nhiêu bước chân trong lúc thể thao, vận động,... Từ đó, nó sẽ cho bạn biết được lượng calo tiêu hao là bao nhiêu và giúp bạn dễ dàng biết cách bù trừ lượng calo cho bản thân.', 
                                            1360000, 1720000, 'Công Nghệ & Thiết Bị Số', '/images/products/vong.jpg', '/images/products/vong.jpg', 50, 'mostBought'),
('Máy Khuếch Tán Tinh Dầu', 'Máy khuếch tán tinh dầu không chỉ giúp lan tỏa hương thơm tinh dầu đến mọi góc trong ngôi nhà mà còn đem lại hiệu quả trị liệu tốt hơn cho sức khỏe và tinh thần của bạn. Với thiết kế thanh lịch, đa dạng về mẫu mã và dễ sử dụng, máy xông tinh dầu Kodo trở thành sự lựa chọn hoàn hảo cho những ai yêu thích không gian sống trong lành và thoải mái.', 
                            910000, 1450000, 'Nhà Cửa & Đời Sống', '/images/products/mtd.jpg', '/images/products/mtd.jpg', 45, 'mostBought'),
('Combo Bàn Phím & Chuột Không Dây',    'Bạn tìm kiếm một thiết bị tiện lợi phù hợp cho công việc học tập và văn phòng, bộ bàn phím chuột Wireless Dell KM7321W là một trong sự lựa chọn đáng để bạn cân nhắc. Với thiết kế kiểu dáng đẹp mắt, màu sắc hiện đại, phong cách phù hợp với mọi môi trường làm việc. Kết nối không dây kép cho phép bạn điều khiển nhiều hệ thống cùng lúc, cực kỳ lý tưởng và tiện lợi cho việc kiểm soát công việc khắp nơi ngay tại chỗ ngồi của bạn.', 
                                        1210000, 1500000, 'Công Nghệ & Thiết Bị Số', '/images/products/banphim.jpg', '/images/products/banphim.jpg', 80, 'mostBought'),
('Máy Xay Sinh Tố Cầm Tay', 'Máy xay cầm tay là thiết bị nhà bếp đa năng có thể được sử dụng để xay nhuyễn thực phẩm, đánh bông, trộn đều và thậm chí là làm kem. Chúng thường nhỏ gọn và dễ sử dụng, khiến chúng trở thành một sự bổ sung tuyệt vời cho bất kỳ nhà bếp nào.', 
                            749000, 1200000, 'Gia Dụng & Nhà Bếp', '/images/products/sinhto.jpg', '/images/products/sinhto.jpg', 70, 'mostBought');

-- danh gia cao
INSERT INTO products (name, description, price, original_price, category, image_url, hover_image_url, stock, section)
VALUES
('Đồng Hồ Thời Trang SilverLine',   'Được chế tác từ thép không gỉ mạ đôi với lớp hoàn thiện bằng bạc đánh bóng, những chiếc đồng hồ này được tạo ra để nâng tầm mọi phong cách, dù là ngày hay đêm.', 
                                    2760000, 3320000, 'Thời Trang & Phụ Kiện', '/images/products/silver.jpg', '/images/products/silver.jpg', 20, 'topRated'),
('Hộp Nến Thơm Quà Tặng Misscandle',    'Nến thơm Misscandle - Bộ quà tặng hoàn hảo với hương thơm dài lâu.
                                        \nMang đến không gian sống đầy hương thơm và ánh sáng ấm áp. Hộp nến thơm cao cấp Misscandle. Bộ sản phẩm cao cấp này bao gồm 2 nến với thời gian cháy kéo dài lên đến hơn 15h , tạo nên không khí thư giãn và dễ chịu cho mọi không gian.', 
                                        560000, 920000, 'Nhà Cửa & Đời Sống', '/images/products/nenthom.jpg', '/images/products/nenthom.jpg', 55, 'topRated'),
('Ba lô Du Lịch Chống Nước AirFit', 'Balo 35L, chất liệu chống thấm.
                                    \nKhả năng chống nước: Sản phẩm được khuyến cáo đi mưa nhỏ, không dầm mưa lâu , chất vải trượt nước nhưng nếu dầm mưa nước sẽ có khả năng len lỏi vào các đường chỉ may nối giữa các bộ phận của túi hoặc dây kéo', 
                                    1490000, 2200000, 'Du Lịch & Cắm Trại', '/images/products/balo.jpg', '/images/products/balo.jpg', 33, 'topRated'),
('Tinh Chất Vitamin C 50ml', 'Công nghệ giúp ổn định vitamin nguyên chất được hoàn thiện bằng phương pháp vô thủy, hiệu quả trong việc cải thiện các vết thâm và nếp nhăn.
                            \nKhả năng làm sáng da mạnh mẽ chỉ với một giọt tinh chất.', 420000, 570000, 'Sức Khỏe & Làm Đẹp', '/images/products/vitamin.jpg', '/images/products/vitamin.jpg', 90, 'topRated');

-- flash sale
INSERT INTO products (name, description, price, original_price, category, image_url, hover_image_url, stock, section)
VALUES
('Combo dầu gội, dầu xả, sữa tắm Old Spice Swagger', 'Bộ sản phẩm Old Spice Swagger mang đến giải pháp chăm sóc toàn diện cho nam giới với hương thơm mạnh mẽ, nam tính từ gỗ tuyết tùng và chanh lá cam.', 
                                                    1250000, 1600000, 'Sức Khỏe & Làm Đẹp', '/images/products/shampoo.jpg', '/images/products/shampoo.jpg', 36, 'flashSale'),
('SmartWatch Cubitt AURA Pro', 'Màn hình AMOLED 1.43 inch sắc nét, hỗ trợ 24/7 theo dõi sức khỏe. Với khả năng chống nước và thời lượng pin lên đến 10 ngày, đây là lựa chọn hoàn hảo cho lối sống năng động.', 
                                                    2320000, 2900000, 'Công Nghệ & Thiết Bị Số', '/images/products/cubitt.jpg', '/images/products/cubitt.jpg', 36, 'flashSale');

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
