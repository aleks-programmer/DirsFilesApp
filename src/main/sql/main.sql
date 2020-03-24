create table dir
(
	id int auto_increment
		primary key,
	path varchar(2000) null,
	created datetime not null
)
;

create table dir_in_dir
(
	dir_id int not null,
	parent_dir_id int not null,
	constraint dir_in_dir_dir_id_fk
		foreign key (dir_id) references dir (id)
			on update cascade on delete cascade,
	constraint dir_in_dir_dir_id_fk_2
		foreign key (parent_dir_id) references dir (id)
			on update cascade on delete cascade
)
;

create index dir_in_dir_dir_id_fk
	on dir_in_dir (dir_id)
;

create index dir_in_dir_dir_id_fk_2
	on dir_in_dir (parent_dir_id)
;

create table file
(
	id int auto_increment
		primary key,
	name varchar(200) not null,
	size bigint not null,
	created datetime not null
)
;

create table file_in_dir
(
	file_id int not null,
	parent_dir_id int not null,
	constraint file_in_dir_file_id_fk
		foreign key (file_id) references file (id)
			on update cascade on delete cascade,
	constraint file_in_dir_dir_id_fk_2
		foreign key (parent_dir_id) references dir (id)
			on update cascade on delete cascade
)
;

create index file_in_dir_dir_id_fk_2
	on file_in_dir (parent_dir_id)
;

create index file_in_dir_file_id_fk
	on file_in_dir (file_id)
;

