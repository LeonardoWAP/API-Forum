CREATE TABLE thread(
	id SERIAL PRIMARY KEY,
	status varchar(255) NOT NULL,
	title varchar(255) NOT NULL,
	description varchar (255) NOT NULL,
	customer_id int NOT NULL ,
	created_at date NOT NULL,
	FOREIGN KEY (customer_id) REFERENCES customer(id)
);