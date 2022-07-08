create table address(
    id bigserial primary key,
    zip bigint not null,
    country varchar(255) not null,
    city varchar(255),
    street varchar(255),
    building varchar(10),
    extension varchar(10),
    flat varchar(10)
);

create table customers(
    id bigserial primary key,
    username varchar(50) not null,
    email varchar(255) not null unique,
    password varchar(255) not null,
    name varchar(50),
    surname varchar(50),
    expired boolean not null default 'true',
    locked boolean not null default 'true',
    enable boolean not null default 'false',
    role varchar(50) not null
);

create table credit_cards(
    id bigserial primary key,
    customer_id bigint not null,
    card_number varchar(40) not null,
    expire_date date not null,
    cvv varchar(10) not null,
    constraint fk_customer foreign key(customer_id) references customers(id)
);

create table buckets(
    id bigserial primary key,
    customer_id bigint not null,
    constraint fk_customer_bucket foreign key(customer_id) references customers(id),
    delivery_address bigint not null,
    constraint fk_address foreign key(delivery_address) references address(id),
    paid timestamp
);

create table bucket_products(
    id bigserial primary key,
    bucket_id bigint not null,
    constraint fk_bucket_product foreign key(bucket_id) references buckets(id),
    name varchar(255) not null,
    model varchar(255),
    price decimal(10,2),
    quantity decimal(10,2)
);