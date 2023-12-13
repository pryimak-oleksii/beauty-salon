INSERT INTO role (role_name)
VALUES ('ADMINISTRATOR'),
       ('MASTER'),
       ('CLIENT');

INSERT INTO status (status_name)
VALUES ('CREATED'),
       ('PAID'),
       ('DONE'),
       ('CANCELED');

INSERT INTO public.users (role_id, user_name, user_surname, user_password, user_email, user_rating) VALUES (1, 'admin', 'admin', 'admin', 'admin', null);
INSERT INTO public.users (role_id, user_name, user_surname, user_password, user_email, user_rating) VALUES (2, 'master', 'master', 'master', 'master', null);
INSERT INTO public.users (role_id, user_name, user_surname, user_password, user_email, user_rating) VALUES (3, 'client', 'client', 'client', 'client', null);
INSERT INTO public.services (service_name, service_description, service_price, service_duration) VALUES ('Миникюр', 'Описание маникюр', 300, 60);
