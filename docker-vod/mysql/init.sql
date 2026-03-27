
CREATE TABLE `client`
(
    `id`         int          NOT NULL AUTO_INCREMENT,
    `first_name` varchar(255) NOT NULL,
    `last_name`  varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);


CREATE TABLE `car`
(
    `id`              int          NOT NULL AUTO_INCREMENT,
    `brand`           varchar(255) NOT NULL,
    `model`           varchar(255) NOT NULL,
    `production_year` int          NOT NULL,
    `daily_rate`      float        NOT NULL,
    `client_id`       int DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_car_client` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
);


CREATE TABLE `rentalCar`
(
    `id`   int          NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    `logo` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);


CREATE TABLE `carToRentalCcar`
(
    `car_id`        int NOT NULL,
    `rental_car_id` int NOT NULL,
    PRIMARY KEY (`car_id`, `rental_car_id`),
    CONSTRAINT `fk_car` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`),
    CONSTRAINT `fk_rental_car` FOREIGN KEY (`rental_car_id`) REFERENCES rental_car (`id`)
);

-- Wstawianie klientów
INSERT INTO `client` (`id`, `first_name`, `last_name`) VALUES
                                                           (1, 'Jan', 'Kowalski'),
                                                           (2, 'Anna', 'Nowak'),
                                                           (3, 'Piotr', 'Wiśniewski'),
                                                           (4, 'Katarzyna', 'Wójcik');

-- Wstawianie samochodów (z przypisaniem do klientów)
INSERT INTO `car` (`id`, `brand`, `model`, `production_year`, `daily_rate`, `client_id`) VALUES
                                                                                             (1, 'Toyota', 'Corolla', 2020, 120.0, 1),
                                                                                             (2, 'Skoda', 'Octavia', 2021, 140.0, 1),
                                                                                             (3, 'BMW', 'X5', 2022, 350.0, 2),
                                                                                             (4, 'Audi', 'A6', 2019, 280.0, 2),
                                                                                             (5, 'Ford', 'Focus', 2018, 100.0, 3),
                                                                                             (6, 'Volkswagen', 'Golf', 2021, 130.0, 3),
                                                                                             (7, 'Mercedes-Benz', 'C-Class', 2023, 300.0, 4),
                                                                                             (8, 'Volvo', 'XC60', 2022, 250.0, 4);

-- Wstawianie punktów wypożyczalni
INSERT INTO rental_car (`id`, `name`, `logo`) VALUES
                                                    (1, 'Rent-A-Car Warszawa', 'https://images.seeklogo.com/logo-png/47/1/warsaw-logo-png_seeklogo-477873.png'),
                                                    (2, 'Auto-Wynajem Kraków', 'https://www.climate-kic.org/wp-content/uploads/2018/05/Logo-Krakow_C_rgb.jpg'),
                                                    (3, 'SuperCars Wrocław', 'https://i.pinimg.com/originals/73/7f/f2/737ff20de51e5907c6924164e4c862e3.png'),
                                                    (4, 'Gdańsk Drive', 'https://download.cloudgdansk.pl/gdansk-pl/t/20170184249.png');

-- Relacja Many-to-Many (przypisanie aut do oddziałów)
INSERT INTO car_rental_car (`car_id`, `rental_car_id`) VALUES
                                                             (2, 1), (2, 3), -- Skoda (id:2) -> Wawa i Wrocław
                                                             (1, 3),         -- Toyota (id:1) -> Wrocław
                                                             (3, 3),         -- BMW (id:3) -> Wrocław
                                                             (6, 1), (6, 4), -- VW (id:6) -> Wawa i Gdańsk
                                                             (7, 4), (7, 2), -- Mercedes (id:7) -> Gdańsk i Kraków
                                                             (3, 2);         -- BMW (id:3) -> Kraków













CREATE TABLE user
(
    id       int primary key auto_increment,
    username VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE role
(
    id       int primary key auto_increment,
    username VARCHAR(255),
    role     VARCHAR(255)
);

INSERT INTO user(username, password)
VALUES ('dbuser1', 'dbuser1'),
       ('dbuser2', 'dbuser2'),
       ('dbuser3', 'dbuser3');


INSERT INTO role(username, role)
VALUES ('dbuser1', 'USER_ADMIN'),
       ('dbuser2', 'AUTHOR_ADMIN'),
       ('dbuser3', 'BOOK_ADMIN');




