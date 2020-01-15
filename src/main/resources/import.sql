#Insert categories
INSERT INTO categories (`create_at`, `name`) VALUES ('2019-12-12', 'mexican');
INSERT INTO categories (`create_at`, `name`) VALUES ('2019-12-12', 'fitness');

#Insert ingredients
INSERT INTO ingredients (`name`, `create_at`,`thumb_route`) VALUES ('Jalapeno','2019-12-12','route');
INSERT INTO ingredients (`name`, `create_at`,`thumb_route`) VALUES ('Tomate','2019-12-12','route2');
INSERT INTO ingredients (`name`, `create_at`,`thumb_route`) VALUES ('Huevo','2019-12-12','route3');
INSERT INTO ingredients (`name`, `create_at`,`thumb_route`) VALUES ('Arroz','2019-12-12','route4');

#Insert users
INSERT INTO users (`age`, `create_at`, `description`, `email`, `lastname`, `name`, `password`, `porfileimage_route`, `sex`, `status`) VALUES ('25', '2019-12-12', 'about me', 'york@mixfood.com', 'Gonzalez', 'Jorge', 'password ', 'route image', 'masculine', 'active');
INSERT INTO users (`age`, `create_at`, `description`, `email`, `lastname`, `name`, `password`, `porfileimage_route`, `sex`, `status`) VALUES ('25', '2019-12-12', 'about me', 'carlos@mixfood.com', 'villasenor', 'Carlos', 'password ', 'route image', 'masculine', 'active');

#Insert recipes
INSERT INTO recipes (`averange_ranking`, `create_at`, `dificult`, `name`, `preparation_time`, `status`, `steps`, `video_route`, `user_id`,`category_id`) VALUES ('0', '2019-12-12', 'easy', 'alitas picantes', '5 ', 'public', 'paso 1: bla bla bla', 'routevideo', 1,1);
INSERT INTO recipes (`averange_ranking`, `create_at`, `dificult`, `name`, `preparation_time`, `status`, `steps`, `video_route`, `user_id`,`category_id`) VALUES ('0', '2019-12-12', 'easy', 'alitas no picantes', '5 ', 'public', 'paso 1: bla bla bla', 'routevideo', 1,1);

#Insert tags
INSERT INTO tags (name,thumb_route,create_at) VALUES('mota','ruta','2012-12-12');

#Insert images
INSERT INTO images (`create_at`, `route_image`, `recipe_id`) VALUES ( '2019-12-12', 'routesss', 1);

#Insert Favorites
INSERT INTO favorites (`id_favorite`, `create_at`, `recipe_id`, `user_id`) VALUES ('0', '2019-12-12', 1, 1);

#Insert Followers
INSERT INTO followers (`id_follower`, `create_at`, `follower_id`, `user_id`) VALUES ('0', '2019-12-12', '2', '1');

INSERT INTO `users` VALUES (1,'25','2019-12-12','Lorem ipsum, dolor sit amet consectetur adipisicing elit. Illo, asperiores illum, consequuntur vitae unde dolore quia ullam quaerat magnam, voluptatum temporibus possimus animi soluta sequi dolores minima laudantium culpa quam!','emdewdwedal@email.com','MALE','Perez','Jorgesx','123456','UserImage_974f56ec-1ee1-4106-bf8c-87c74c42db8f.jpg','active',NULL,NULL,1,'1994-12-07','Mexico',1),(2,'25','2019-12-12','about me','carlosadmin@mixfood.com','MALE','villasenor','Carlos','$10$n6RX0mvfo5ZLhP4e.cGPzeMQ5bd5u5fTWTyEuFkTqwZikblCH5CWG','UserImage_893da079-6b40-48aa-a97d-e414dca4d84c.jpg','active',NULL,NULL,1,'1994-12-07','Mexico',1),(4,'25','2019-12-12','about me','edgaradmin@mixfood.com','MALE','Diablo','Edgar','$10$n6RX0mvfo5ZLhP4e.cGPzeMQ5bd5u5fTWTyEuFkTqwZikblCH5CWG','UserImage_893da079-6b40-48aa-a97d-e414dca4d84c.jpg','active',NULL,NULL,1,'1994-12-07','Mexico',1),(5,'1','2019-12-12','eee','carlosuser@mixfood.com','MALE','Pepe','Perez','$10$n6RX0mvfo5ZLhP4e.cGPzeMQ5bd5u5fTWTyEuFkTqwZikblCH5CWG','ff','active',NULL,NULL,2,'1994-12-07','Mexico',1),(6,'1','2019-12-12','eee','edgaruser@mixfood.com','MALE','Juan','Gonzalez','$10$n6RX0mvfo5ZLhP4e.cGPzeMQ5bd5u5fTWTyEuFkTqwZikblCH5CWG','fff','active',NULL,NULL,2,'1994-12-07','Mexico',1);
INSERT INTO `users_authorities` VALUES (2,1),(4,1),(5,2),(6,2);

