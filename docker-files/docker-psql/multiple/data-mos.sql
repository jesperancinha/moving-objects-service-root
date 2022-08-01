truncate mos.mos.moving_object;

insert into mos.mos.moving_object (id,  code, folder, uri,x,y)
values (gen_random_uuid(), 'GAR', 'garlic', '/aggregator/webcams/camera/GAR',1,2);
insert into mos.mos.moving_object (id,  code, folder, uri,x,y)
values (gen_random_uuid(), 'LAU', 'laurel', '/aggregator/webcams/camera/LAU',2,2);
insert into mos.mos.moving_object (id,  code, folder, uri,x,y)
values (gen_random_uuid(), 'LEM', 'lemon', '/aggregator/webcams/camera/LEM',3,4);
insert into mos.mos.moving_object (id,  code, folder, uri,x,y)
values (gen_random_uuid(), 'ONI', 'onion', '/aggregator/webcams/camera/ONI',7,2);
insert into mos.mos.moving_object (id,  code, folder, uri,x,y)
values (gen_random_uuid(), 'PUM', 'pumpkin', '/aggregator/webcams/camera/PUM',8,1);
insert into mos.mos.moving_object (id,  code, folder, uri,x,y)
values (gen_random_uuid(), 'RED', 'red-onion', '/aggregator/webcams/camera/RED',3,6);
insert into mos.mos.moving_object (id,  code, folder, uri,x,y)
values (gen_random_uuid(), 'SNA', 'snail', '/aggregator/webcams/camera/SNA',1,7);
insert into mos.mos.moving_object (id,  code, folder, uri,x,y)
values (gen_random_uuid(), 'TOM', 'tomato', '/aggregator/webcams/camera/TOM',0,1);

insert into mos.mos.info_object(id, "name", size, color, code,x,y)
values (gen_random_uuid(), 'Garlic', 2, 'white', 'GAR', 1, 2);
insert into mos.mos.info_object(id, "name", size, color, code,x,y)
values (gen_random_uuid(), 'Laurel', 4, 'green', 'LAU', 2, 2);
insert into mos.mos.info_object(id, "name", size, color, code,x,y)
values (gen_random_uuid(), 'Lemon', 2, 'lemon', 'LEM', 3, 4);
insert into mos.mos.info_object(id, "name", size, color, code,x,y)
values (gen_random_uuid(), 'Onion', 4, 'gold', 'ONI', 7, 2);
insert into mos.mos.info_object(id, "name", size, color, code,x,y)
values (gen_random_uuid(), 'Pumpkin', 10, 'orange', 'PUM', 8, 1,);
insert into mos.mos.info_object(id, "name", size, color, code,x,y)
values (gen_random_uuid(), 'Red Onion', 5, 'purple', 'RED', 3, 6);
insert into mos.mos.info_object(id, "name", size, color, code,x,y)
values (gen_random_uuid(), 'Snail', 1, 'brown', 'SNA', 1, 7);
insert into mos.mos.info_object(id, "name", size, color, code,x,y)
values (gen_random_uuid(), 'Tomato', 2, 'red','TOM', 0, 1);
