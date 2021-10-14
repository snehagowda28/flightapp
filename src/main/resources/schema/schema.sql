create table flight (
   flight_id bigint not null,
    end_destination varchar(255),
    end_time timestamp,
    flight_company integer,
    flight_model varchar(255),
    is_blocked boolean,
    price bigint,
    start_destination varchar(255),
    start_time timestamp,
    meal_id bigint,
    primary key (flight_id)
)

create table flight_ticket (
    pnr integer not null,
    created_at timestamp,
    updated_at timestamp,
    primary key (pnr)
)