CREATE TABLE thread(
	id SERIAL PRIMARY KEY,
	status varchar(255) NOT NULL,
	title varchar(255) NOT NULL,
	description varchar (255) NOT NULL
);