CREATE TABLE thesis.request_audit(
	audit_id bigint,
	method_name varchar(50),
	request_data text,
	response_status varchar(200),
	response_body text,
	status varchar(10),
	creation_timestamp varchar(50),
	exception varchar(100),
	exception_message text
);

CREATE SEQUENCE request_audit_seq START WITH 1 INCREMENT BY 1;