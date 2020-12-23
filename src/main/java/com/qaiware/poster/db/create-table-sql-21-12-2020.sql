create table if not exists messages (
	id bigint PRIMARY KEY,
	payload text NOT NULL,
	type text,
	created_at text,
)
