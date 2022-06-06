create table tours
(
    id          serial
        constraint tours_pk
            primary key,
    name        varchar not null,
    description varchar,
    distance    varchar,
    "from"      varchar,
    "to"        varchar
);

alter table tours
    owner to postgres;

create table tourlog
(
    id       serial
        constraint tourlog_pk
            primary key,
    date     varchar not null,
    report   varchar,
    distance varchar,
    time     varchar,
    rating   varchar,
    weather  varchar,
    speed    varchar,
    fktourid integer
);

alter table tourlog
    owner to postgres;

create unique index tourlog_id_uindex
    on tourlog (id);


