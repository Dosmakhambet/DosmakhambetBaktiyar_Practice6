CREATE TABLE skill
(
    id     BIGINT NOT NULL,
    name   VARCHAR(255),
    status INTEGER,
    CONSTRAINT pk_skill PRIMARY KEY (id)
);

CREATE TABLE specialty
(
    id     BIGINT NOT NULL,
    name   VARCHAR(255),
    status INTEGER,
    CONSTRAINT pk_specialty PRIMARY KEY (id)
);

CREATE TABLE developer
(
    id           BIGINT NOT NULL,
    firstName    VARCHAR(255),
    lastName     VARCHAR(255),
    status       INTEGER,
    specialty_id BIGINT,
    CONSTRAINT pk_developer PRIMARY KEY (id)
);

ALTER TABLE developer
    ADD CONSTRAINT FK_DEVELOPER_ON_SPECIALTY FOREIGN KEY (specialty_id) REFERENCES specialty (id);

CREATE TABLE developer_skill
(
    developers_id BIGINT NOT NULL,
    skills_id     BIGINT NOT NULL
);

ALTER TABLE developer_skill
    ADD CONSTRAINT fk_devski_on_developer FOREIGN KEY (developers_id) REFERENCES developer (id);

ALTER TABLE developer_skill
    ADD CONSTRAINT fk_devski_on_skill FOREIGN KEY (skills_id) REFERENCES skill (id);
