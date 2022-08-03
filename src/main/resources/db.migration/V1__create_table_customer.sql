CREATE TABLE customer(
	id SERIAL PRIMARY KEY,
	name varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	email varchar (255) not null unique
);