
-- Clean up
delete from users_roles;

delete from roles;

delete from users;


-- Insert Data

insert into roles (role_id,name) values (1,'ADMIN');


-- "mypassword"
insert into users (user_id,password,username) values 
(1,'$2a$12$bVCAxg644b3byzrZFHrene6yUezzBkanNcUL9kYi0am7Qh4vzHDYO','admin');

insert into users_roles (user_id,role_id) values (1,1);

-- Employees

delete from employee;

insert into employee (id, email, first_name, last_name) values (1, 'jnw@gmail.com', 'Janifer', 'winget');

insert into employee (id, email, first_name, last_name) values (2, 'neegu@gmail.com', 'Neelam', 'Gupta');

insert into employee (id, email, first_name, last_name) values (3, 'swagahoi@gmail.com', 'Swatantra', 'Gahoi');

insert into employee (id, email, first_name, last_name) values (4, 'ts@gmail.com', 'Tanya', 'Sharma');

