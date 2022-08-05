CREATE TABLE message(
	id SERIAL PRIMARY KEY,
	description varchar (255) NOT NULL,
	thread_id int NOT NULL,
	customer_id int NOT NULL ,
	created_at date NOT NULL,
	FOREIGN KEY (customer_id) REFERENCES customer(id)
	FOREIGN KEY (thread_id) REFERENCES thread(id)
);