CREATE TABLE users(
    ID serial PRIMARY KEY,
    NAME varchar(100) not null,
    EMAIL varchar(100) not null,
    PASSWORD varchar(100) not null,
    CREATED timestamp with time zone not null,
    MODIFIED timestamp with time zone not null,
    LAST_LOGIN timestamp with time zone not null,
    TOKEN varchar(100) not null
)