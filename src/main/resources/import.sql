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


INSERT INTO `categories` VALUES (1,'2020-01-14','Appetisers',NULL,NULL,0,1),(2,'2020-01-14','breakfast',NULL,NULL,0,1),(3,'2020-01-14','Brunch',NULL,NULL,0,1),(4,'2020-01-14','Burger',NULL,NULL,0,0),(5,'2020-01-14','Dinner',NULL,NULL,0,0),(6,'2020-01-14','Lunch',NULL,NULL,0,0),(7,'2020-01-14','Noodles',NULL,NULL,0,0),(8,'2020-01-14','Pasta',NULL,NULL,0,0),(9,'2020-01-14','Pies',NULL,NULL,0,0),(10,'2020-01-14','Pizza',NULL,NULL,0,0),(11,'2020-01-14','Rice',NULL,NULL,0,0),(12,'2020-01-14','Salads',NULL,NULL,0,0),(13,'2020-01-14','Sides',NULL,NULL,0,0),(14,'2020-01-14','Snacks',NULL,NULL,0,0),(15,'2020-01-14','Soups',NULL,NULL,0,0),(16,'2020-01-14','Mexican',NULL,NULL,0,0);

15,NULL,'bacon','C:\\n',NULL),(16,NULL,'lettuce','C:\\o',NULL),(17,NULL,'mayonnaise','C:\\p',NULL),(18,NULL,'salt','C:\\q',NULL),(19,NULL,'pepper','C:\\r',NULL);INSERT INTO `ingredients` VALUES (1,NULL,'large eggs        ','C:\\',NULL),(2,NULL,'kosher salt','C:\\a',NULL),(3,NULL,'black pepper','C:\\b',NULL),(4,NULL,'unsalted butter','C:\\c',NULL),(5,NULL,'corn tortillas','C:\\d',NULL),(6,NULL,'avocado','C:\\e',NULL),(7,NULL,'salsa','C:\\f',NULL),(8,NULL,'Monterey Jack','C:\\g',NULL),(9,NULL,'cilantro','C:\\h',NULL),(10,NULL,'Cooked ham','C:\\i',NULL),(11,NULL,'Pickle spears','C:\\j',NULL),(12,NULL,'Cream cheese','C:\\k',NULL),(13,NULL,'salted butter','C:\\l',NULL),(14,NULL,'tomatoes ','C:\\m',NULL),(