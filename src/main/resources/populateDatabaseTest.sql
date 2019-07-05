insert into publisher(id, version, name, create_date, update_date) values (1, 1, 'publisher1', now(3), now(3));

insert into publisher(id, version, name, create_date, update_date) values (2, 1, 'publisher2', now(3), now(3));

insert into platform(id, version, name, create_date, update_date) values (1, 1, 'platform1', now(3), now(3));

insert into video_game(id, version, name, year, critic_score, user_score, global_sales, create_date, update_date) values (1, 1, 'game1', 2019, '9.3', '2', '3.12', now(3), now(3));

insert into video_game(id, version, name, year, critic_score, user_score, global_sales, platform_id, publisher_id, create_date, update_date) values (2, 1, 'game2', 2012, '2', '8', '6.12', 1, 1, now(3), now(3));

insert into video_game(id, version, name, year, critic_score, user_score, global_sales, platform_id, publisher_id, create_date, update_date) values (3, 1, 'game3', 1950, '5', '5', '4', 1, null, now(3), now(3));