SET FOREIGN_KEY_CHECKS=0;
drop table if exists timetabler;
drop table if exists module;
drop table if exists lecture_room;
SET FOREIGN_KEY_CHECKS=1;

create table timetabler
(
    id integer primary key auto_increment,
    email varchar(50) not null,
    name varchar(30) not null,
    password varchar(50) not null,
    token varchar(50)
);

create table module
(
  id integer primary key auto_increment,
  name varchar(50) not null,
  department integer not null,
  foreign key (department) references student_department(id)
);

create table lecture_room
(
    id integer primary key auto_increment,
    name varchar(50) not null
);

create table class_schedule
(
    id integer primary key auto_increment,
    module integer not null,
    foreign key (module) references module(id),
    lecture_room integer not null,
    foreign key (lecture_room) references lecture_room(id),
    start_time timestamp not null,
    end_time timestamp not null,
    week integer not null
);

alter table student
add token varchar(50);

insert into timetabler(email, name, password)
VALUES ('staff1@staff.com', 'Staff 1', 'staff_1'),
       ('staff2@staff.com', 'Staff 2', 'staff_2');

insert into lecture_room(name)
VALUES ('Cadman'),
       ('Flaxman');

insert into module(name, department)
VALUES ('Pervasive Computing', 2),
       ('Enterprise Systems', 1),
       ('AWS Cloud Basics', 2),
       ('Professional Software Engineering', 2);