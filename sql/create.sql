DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS status;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS service;
DROP TABLE IF EXISTS "order";

CREATE TABLE role
(
    role_id   INT GENERATED ALWAYS AS IDENTITY,
    role_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (role_id)
);

CREATE TABLE status
(
    status_id   INT GENERATED ALWAYS AS IDENTITY,
    status_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (status_id)
);


CREATE TABLE users
(
    users_id      INT GENERATED ALWAYS AS IDENTITY,
    role_id       INT          NOT NULL,
    user_name     varchar(255) NOT NULL,
    user_surname  varchar(255) NOT NULL,
    user_password varchar(255) NOT NULL,
    user_email    varchar(255) NOT NULL,
    user_rating   INT,
    PRIMARY KEY (users_id),
    CONSTRAINT fk_role
        FOREIGN KEY (role_id)
            REFERENCES role (role_id)
);


CREATE TABLE services
(
    service_id          INT GENERATED ALWAYS AS IDENTITY,
    service_name        varchar(255)  NOT NULL,
    service_description varchar(1000) NOT NULL,
    service_price       DECIMAL,
    service_duration    INT           NOT NULL,
    PRIMARY KEY (service_id)

);

CREATE TABLE master_services
(
    master_service_id INT GENERATED ALWAYS AS IDENTITY,
    master_id         INT NOT NULL,
    service_id        INT NOT NULL,
    PRIMARY KEY (master_service_id),
    CONSTRAINT fk_master
        FOREIGN KEY (master_id)
            REFERENCES users (users_id),
    CONSTRAINT fk_service
        FOREIGN KEY (service_id)
            REFERENCES services (service_id)
);

CREATE TABLE orders
(
    order_id         INT GENERATED ALWAYS AS IDENTITY,
    client_id        INT  NOT NULL,
    master_id        INT  NOT NULL,
    service_id       INT  NOT NUll,
    status_id        INT  NOT NULL,
    reservation_time timestamp
        NOT NULL,
    feed_back        varchar(500),
    PRIMARY KEY (order_id),
    CONSTRAINT fk_user
        FOREIGN KEY (client_id)
            REFERENCES users (users_id),
    CONSTRAINT fk_master
        FOREIGN KEY (master_id)
            REFERENCES users (users_id),
    CONSTRAINT fk_status
        FOREIGN KEY (status_id)
            REFERENCES status (status_id),
    CONSTRAINT fk_service
        FOREIGN KEY (service_id)
            REFERENCES services (service_id)

);

CREATE TABLE master_schedule
(
    schedule_id INT GENERATED ALWAYS AS IDENTITY,
    master_id   INT NOT NULL,
    day_id      INT NOT NULL,
    PRIMARY KEY (schedule_id),
    CONSTRAINT fk_master
        FOREIGN KEY (master_id)
            REFERENCES users (users_id)
);