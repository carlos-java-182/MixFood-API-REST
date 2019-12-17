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
