truncate mos.mos.moving_object;

insert into mos.mos.moving_object (id, "name", code, folder, url,x,y)
values (gen_random_uuid(), 'garlic', 'GAR', 'garlic', 'http://moving-objects-jwt-service:8080/objects/camera/gar',1,2);
insert into mos.mos.moving_object (id, "name", code, folder, url,x,y)
values (gen_random_uuid(), 'laurel', 'LAU', 'laurel', 'http://moving-objects-jwt-service:8080/objects/camera/lau',2,2);
insert into mos.mos.moving_object (id, "name", code, folder, url,x,y)
values (gen_random_uuid(), 'lemon', 'LEM', 'lemon', 'http://moving-objects-jwt-service:8080/objects/camera/lem',3,4);
insert into mos.mos.moving_object (id, "name", code, folder, url,x,y)
values (gen_random_uuid(), 'onion', 'ONI', 'onion', 'http://moving-objects-jwt-service:8080/objects/camera/oni',7,2);
insert into mos.mos.moving_object (id, "name", code, folder, url,x,y)
values (gen_random_uuid(), 'pumpkin', 'PUM', 'pumpkin', 'http://moving-objects-jwt-service:8080/objects/camera/pum',8,1);
insert into mos.mos.moving_object (id, "name", code, folder, url,x,y)
values (gen_random_uuid(), 'red-onion', 'RED', 'red-onion', 'http://moving-objects-jwt-service:8080/objects/camera/red',3,6);
insert into mos.mos.moving_object (id, "name", code, folder, url,x,y)
values (gen_random_uuid(), 'snail', 'SNA', 'snail', 'http://moving-objects-jwt-service:8080/objects/camera/sna',1,7);
insert into mos.mos.moving_object (id, "name", code, folder, url,x,y)
values (gen_random_uuid(), 'tomato', 'TOM', 'tomato', 'http://moving-objects-jwt-service:8080/objects/camera/tom',0,1);
