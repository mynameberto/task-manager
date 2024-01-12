CREATE TABLE tasks(

    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(100) NOT NULL,
    status VARCHAR(15) NOT NULL,
    dtcreation DATE NOT NULL,

    PRIMARY KEY(id)
);