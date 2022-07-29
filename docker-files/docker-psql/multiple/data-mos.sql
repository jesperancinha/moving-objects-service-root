truncate mos.mos.moving_object;

insert into mos.mos.moving_object (id, "name", code, folder, url)
values (gen_random_uuid(), 'garlic', 'GAR', 'garlic', 'http://moving-objects-jwt-service:8080/objects/camera/gar');
insert into mos.mos.moving_object (id, "name", code, folder, url)
values (gen_random_uuid(), 'laurel', 'LAU', 'laurel', 'http://moving-objects-jwt-service:8080/objects/camera/lau');
insert into mos.mos.moving_object (id, "name", code, folder, url)
values (gen_random_uuid(), 'lemon', 'LEM', 'lemon', 'http://moving-objects-jwt-service:8080/objects/camera/lem');
insert into mos.mos.moving_object (id, "name", code, folder, url)
values (gen_random_uuid(), 'onion', 'ONI', 'onion', 'http://moving-objects-jwt-service:8080/objects/camera/oni');
insert into mos.mos.moving_object (id, "name", code, folder, url)
values (gen_random_uuid(), 'pumpkin', 'PUM', 'pumpkin', 'http://moving-objects-jwt-service:8080/objects/camera/pum');
insert into mos.mos.moving_object (id, "name", code, folder, url)
values (gen_random_uuid(), 'red-onion', 'RED', 'red-onion', 'http://moving-objects-jwt-service:8080/objects/camera/red');
insert into mos.mos.moving_object (id, "name", code, folder, url)
values (gen_random_uuid(), 'snail', 'SNA', 'snail', 'http://moving-objects-jwt-service:8080/objects/camera/sna');
insert into mos.mos.moving_object (id, "name", code, folder, url)
values (gen_random_uuid(), 'tomato', 'TOM', 'tomato', 'http://moving-objects-jwt-service:8080/objects/camera/tom');
