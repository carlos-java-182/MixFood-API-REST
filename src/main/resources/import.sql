#Insert categories
INSERT INTO categories (`create_at`, `name`) VALUES ('2019-12-12', 'mexican');
INSERT INTO categories (`create_at`, `name`) VALUES ('2019-12-12', 'fitness');

#Insert ingredients
INSERT INTO ingredients (`name`, `create_at`,`thumb_route`) VALUES ('Jalapeno','2019-12-12','route');
INSERT INTO ingredients (`name`, `create_at`,`thumb_route`) VALUES ('Tomate','2019-12-12','route');
INSERT INTO ingredients (`name`, `create_at`,`thumb_route`) VALUES ('Huevo','2019-12-12','route');

#Insert users
INSERT INTO `food_db`.`users` (`age`, `create_at`, `description`, `email`, `lastname`, `name`, `password`, `porfileimage_route`, `sex`, `status`) VALUES ('25', '2019-12-12', 'about me', 'york@mixfood.com', 'Gonzalez', 'Jorge', 'password ', 'route image', 'masculine', 'active');
INSERT INTO `food_db`.`users` (`age`, `create_at`, `description`, `email`, `lastname`, `name`, `password`, `porfileimage_route`, `sex`, `status`) VALUES ('25', '2019-12-12', 'about me', 'carlos@mixfood.com', 'villasenor', 'Carlos', 'password ', 'route image', 'masculine', 'active');

#Insert recipes
INSERT INTO `food_db`.`recipes` (`averange_ranking`, `create_at`, `dificult`, `name`, `preparation_time`, `status`, `steps`, `video_route`, `user_id`) VALUES ('0', '2019-12-12', 'easy', 'alitas picantes', '5 minutos', 'public', 'paso 1: bla bla bla', 'routevideo', '1');
INSERT INTO `food_db`.`recipes` (`averange_ranking`, `create_at`, `dificult`, `name`, `preparation_time`, `status`, `steps`, `video_route`, `user_id`) VALUES ('0', '2019-12-12', 'easy', 'alitas no picantes', '5 minutos', 'public', 'paso 1: bla bla bla', 'routevideo', '1');
