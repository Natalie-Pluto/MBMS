DROP schema IF EXISTS moviebooking_db CASCADE;
CREATE schema moviebooking_db;
SET search_path TO 'moviebooking_db';

DROP TABLE IF EXISTS Credit_Card CASCADE;
DROP TABLE IF EXISTS Users CASCADE;
DROP TABLE IF EXISTS Gift_Card CASCADE;
DROP TABLE IF EXISTS Cinema_Session CASCADE;
DROP TABLE IF EXISTS Movie CASCADE;
DROP TABLE IF EXISTS Cinema CASCADE;
DROP TABLE IF EXISTS Seat_Availability CASCADE;

CREATE TABLE Credit_Card (
    number CHAR(5) PRIMARY KEY,
    cardholder_name VARCHAR(100) NOT NULL,
    pin CHAR(4) NOT NULL,
    balance DECIMAL(12,5) NOT NULL -- DECIMAL(<#digits>,<#post-decimal places>)
);

CREATE TABLE Users ( -- note: table can't be named 'User' as 'User' is a reserved keyword 
    username VARCHAR(100) PRIMARY KEY,
    password_ VARCHAR(100),
    creditcard CHAR(5) REFERENCES Credit_Card(number),
    identity_ CHAR(1) NOT NULL
);

CREATE TABLE Gift_Card(
    number CHAR(18) PRIMARY KEY,
    redeemed BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE Movie(
    movie_id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    classification VARCHAR(30) NOT NULL,
    release_date DATE NOT NULL,
    synopsis TEXT,
    directors TEXT,
    genre char(30)
);

CREATE TABLE Cinema (
    cinema_name VARCHAR(100) PRIMARY KEY
);

CREATE TABLE Cinema_Session (
    session_id INT PRIMARY KEY,
    cinema VARCHAR(100) REFERENCES Cinema(cinema_name),
    screen_type VARCHAR(6) NOT NULL,
    movie INT NOT NULL REFERENCES Movie(movie_id),
    ticket_price_kids DECIMAL(10,4) NOT NULL,
    ticket_price_adults DECIMAL(10,4) NOT NULL,
    ticket_price_seniors DECIMAL(10,4) NOT NULL,
    number_of_front_seats_booked INT,
    number_of_mid_seats_booked INT,
    number_of_back_seats_booked INT,
    front_seat_capacity INT,
    mid_seat_capacity INT,
    back_seat_capacity INT,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    UNIQUE(cinema, screen_type, movie, start_time)
);

-------------------------------------------
---- Sample Data ----
-------------------------------------------

-- sample Movie data --

-- sample Credit_Card data --

-- sample Users data--

-- sample Cinema_Session data --

-- sample Gift_Card data--

-- sample Cinema data --
