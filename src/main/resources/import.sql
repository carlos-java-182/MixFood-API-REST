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

INSERT INTO users (`id_user`, `create_at`, `description`, `email`, `gender`, `lastname`, `name`, `password`, `porfileimage_route`, `status`, `date_birth`, `country`, `enabled`)
VALUES ('1', '2020-01-01', 'Hello! I am york and i am a user', 'york@mixfood.com', 'M', 'Gonzalez', 'Jorge', '$2a$10$HuW6eN1Xa41t7Szkb22dH.NymlPYOI7DrUB7SbQya6cvcjFKg.LZ.', 'default.png', 'active', '1994-11-09', 'Mexico', '1');

INSERT INTO users (`id_user`, `create_at`, `description`, `email`, `gender`, `lastname`, `name`, `password`, `porfileimage_route`, `status`, `date_birth`, `country`, `enabled`)
VALUES ('2', '2020-01-01', 'Hello! I am carlos and i am a user', 'carlos@mixfood.com', 'M', 'Villasenor', 'Carlos', '$2a$10$HuW6eN1Xa41t7Szkb22dH.NymlPYOI7DrUB7SbQya6cvcjFKg.LZ.', 'default.png', 'active', '1994-11-09', 'Mexico', '1');

INSERT INTO users (`id_user`, `create_at`, `description`, `email`, `gender`, `lastname`, `name`, `password`, `porfileimage_route`, `status`, `date_birth`, `country`, `enabled`)
VALUES ('3', '2020-01-01', 'Hello! I am edgar and i am a user', 'edgar@mixfood.com', 'M', 'Lara', 'Edgar', '$2a$10$HuW6eN1Xa41t7Szkb22dH.NymlPYOI7DrUB7SbQya6cvcjFKg.LZ.', 'default.png', 'active', '1994-11-09', 'Mexico', '1');



INSERT INTO users_authorities (`user_id`, `role_id`) VALUES ('1', '2');
INSERT INTO users_authorities (`user_id`, `role_id`) VALUES ('2', '2');
INSERT INTO users_authorities (`user_id`, `role_id`) VALUES ('3', '2');
