CREATE TABLE thesis.Users(
	userId uuid,
	name varchar(100),
	username varchar(100),
	email varchar(100),
	mobile varchar(50),
    profession varchar(100),
    organization varchar(100)
);

CREATE TABLE thesis.Posts(
    post_id uuid,
    post_name varchar(100),
    content text,
    post_date varchar(50),
    user_id uuid
);