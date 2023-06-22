create table if not exists events
(
    id          serial primary key,
    title       varchar(255) not null,
    location    varchar(255) not null,
    description text,
    date        date         not null,
    price       numeric      not null,
    image       text
)