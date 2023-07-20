CREATE TABLE IF NOT EXISTS QUESTIONS(
    ID UUID NOT NULL UNIQUE,
    QUESTION VARCHAR(300) NOT NULL,
    ALTERNATIVES TEXT NOT NULL,
    ANSWER VARCHAR(30),
    CATEGORY_ID UUID,

    FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORIES(ID),

    PRIMARY KEY (ID)
);