create table manager
(
    id         serial  not null
        constraint manager_pk
            primary key,
    username   varchar not null,
    password   varchar not null,
    email      varchar not null,
    created_at timestamp,
    updated_at timestamp
);

alter table manager
    owner to postgres;


------------------------------------------------------------


create table facility
(
    id           serial    not null
        constraint facility_pk
            primary key,
    name         varchar   not null,
    sport_type   varchar   not null,
    email        varchar   not null,
    phone_number varchar   not null,
    created_at   timestamp not null,
    updated_at   timestamp not null,
    address      varchar   not null,
    longitude    float8    not null,
    latitude     float8    not null,
    manager      integer
        constraint facility_manager_manager_id_fk
            references manager (id)
);

alter table facility
    owner to postgres;


------------------------------------------------------------


create table court
(
    id         serial  not null
        constraint court_pk
            primary key,
    facility   integer not null
        constraint court_facility_facility_id_fk
            references facility (id),
    features   varchar,
    created_at timestamp,
    updated_at timestamp
);

alter table court
    owner to postgres;


------------------------------------------------------------


create table device
(
    id         serial  not null
        constraint device_pk
            primary key,
    serial     varchar not null,
    model      varchar not null,
    created_at timestamp,
    updated_at timestamp,
    facility   integer
        constraint device_facility_facility_id_fk
            references facility (id)
);

alter table device
    owner to postgres;


------------------------------------------------------------


create table court_feature
(
    id         serial  not null
        constraint court_feature_pk
            primary key,
    name       varchar not null,
    type       varchar not null,
    courts     varchar,
    created_at timestamp,
    updated_at timestamp
);

alter table court_feature
    owner to postgres;


------------------------------------------------------------


create table "user"
(
    id         serial not null
        constraint user_pk
            primary key,
    username   varchar,
    password   varchar,
    email      varchar,
    created_at timestamp,
    updated_at timestamp
);

alter table "user"
    owner to postgres;


------------------------------------------------------------


create table reservation
(
    id         serial      not null
        constraint reservation_pk
            primary key,
    court      integer     not null
        constraint reservation_court_court_id_fk
            references court (id),
    manager    integer
        constraint reservation_manager_manager_id_fk
            references manager (id),
    "user"     integer
        constraint reservation_user_user_id_fk
            references "user" (id),
    start_time timestamptz not null,
    end_time   timestamptz not null,
    type       varchar     not null,
    created_at timestamp,
    updated_at timestamp
);

alter table reservation
    owner to postgres;


------------------------------------------------------------


create table facility_feature
(
    id         serial  not null
        constraint facility_feature_pk
            primary key,
    name       varchar not null,
    type       varchar not null,
    created_at timestamp,
    updated_at timestamp
);

alter table facility_feature
    owner to postgres;


-------------------------------------------------------------
