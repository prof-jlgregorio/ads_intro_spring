create table if not exists clients(

    id SERIAL,
    name varchar(50) not null,
    gender char(1) not null,
    city varchar(50) not null,
    email varchar(50) not null,
    profession_id integer not null,

    constraint pk_client primary key (id),
    constraint fk_client_profession foreign key(profession_id) references professions(id)

);