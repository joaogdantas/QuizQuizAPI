CREATE TABLE IF NOT EXISTS CATEGORIES(
    ID UUID NOT NULL UNIQUE,
    NAME VARCHAR(45) NOT NULL,
    STARS FLOAT,

    PRIMARY KEY (ID)
);