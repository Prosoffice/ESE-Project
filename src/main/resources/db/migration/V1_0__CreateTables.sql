SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS student_department;
SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE student_department
(
    id integer primary key auto_increment,
    name varchar(20) not null
);

CREATE TABLE student
(
    id integer primary key auto_increment,
    email varchar(50) not null,
    name varchar(30) not null,
    password varchar(50) not null,
    department integer not null,
    foreign key (department) references student_department(id)
);


INSERT INTO student_department (name)
VALUES('Cybersecurity'),
       ('Software Engineering');

INSERT INTO student (email, name, password, department)
VALUES ('ogbonnayaprosper1@gmail.com', 'Prosper Ogbonnaya', 'password1', 2),
       ('answerpros@gmail.com', 'Answer Henry', 'password2', 1);