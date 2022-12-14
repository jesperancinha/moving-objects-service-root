create schema if not exists mos;

drop table if exists mos.moving_object;
drop table if exists mos.info_object;

create table if not exists mos.moving_object(
    id UUID,
    code VARCHAR ( 50 ) NOT NULL,
    folder VARCHAR ( 255 ) UNIQUE NOT NULL,
    uri VARCHAR(255 ) UNIQUE NOT NULL,
    x INT,
    y INT,
    PRIMARY KEY (id)
);

create table if not exists mos.info_object(
    id UUID,
    "name" VARCHAR(50) UNIQUE NOT NULL,
    "size" INT,
    color VARCHAR ( 255 ) NOT NULL,
    code VARCHAR ( 50 ) NOT NULL,
    x INT,
    y INT,
    PRIMARY KEY (id)
);
