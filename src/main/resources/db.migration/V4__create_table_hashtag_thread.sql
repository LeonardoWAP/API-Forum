CREATE TABLE hashtag_thread(
	id SERIAL PRIMARY KEY,
	hashtag_id int NOT NULL ,
	thread_id varchar(255) NOT NULL,
	FOREIGN KEY (thread_id) REFERENCES thread(id),
	FOREIGN KEY (hashtag_id) REFERENCES hashtag(id)
);