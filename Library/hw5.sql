drop table if exists library;
drop table if exists lib_users;
drop table if exists lib_checkout;

create table library (
    id          integer auto_increment primary key,
    type        varchar(255),
    name        varchar(255),
    info        varchar(255),
    available   boolean default true
);

create table lib_users (
    id          integer auto_increment primary key,
    username    varchar(255) unique not null,
    password    varchar(255)
);

create table lib_checkout (
    id          integer auto_increment primary key,
    cin         integer,
    name        varchar(255),
    borrowed    date,
    due         date,
    returned    date,
    item_id     integer
);

insert into library values (1, 'Tablet', 'Samsung Galaxy Tab 10.1', 'Android 3.0', false);
insert into library values (2, 'Book', 'Cracking the Code Interview (6th Ed)', '2015', true);
insert into library values (3, 'Book', 'Cracking the Code Interview (6th Ed)', '2015', true);
insert into library values (4, 'Tablet', 'Nexus 7 (2nd Gen)', 'Android 4.4.2', true);
insert into library values (5, 'Tablet', 'Nexus 7 (2nd Gen)', 'Android 4.4.2', true);

insert into lib_users values (1, 'cysun', 'abcd');

insert into lib_checkout values (1, 5678, 'John', '2017-04-01', null, '2017-08-28', 1);
insert into lib_checkout values (2, 1234, 'Jane', '2018-03-04', '2018-04-04', '2018-03-20', 1);
insert into lib_checkout values (3, 4321, 'Tom', '2018-04-12', '2018-05-01', null, 1);
