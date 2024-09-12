-- Criação da tabela Attraction
CREATE TABLE attraction (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description VARCHAR(100000) NOT NULL,
    map_link VARCHAR(200) NOT NULL,
    city VARCHAR(200) NOT NULL,
    state VARCHAR(200) NOT NULL,
    image_link VARCHAR(500) NOT NULL,
    fonte VARCHAR(300) NOT NULL
);

CREATE SEQUENCE attraction_sequence START 1;

-- Criação da tabela TouristSegmentation
CREATE TABLE tourist_segmentation (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(100) NOT NULL
);

CREATE SEQUENCE tourist_sequence START 11;

-- Tabela de relacionamento Many-to-Many entre Attraction e TouristSegmentation
CREATE TABLE attraction_segmentation (
    attraction_id BIGINT NOT NULL,
    segmentation_id BIGINT NOT NULL,
    PRIMARY KEY (attraction_id, segmentation_id),
    CONSTRAINT fk_attraction FOREIGN KEY (attraction_id) REFERENCES attraction(id),
    CONSTRAINT fk_segmentation FOREIGN KEY (segmentation_id) REFERENCES tourist_segmentation(id)
);

-- Criação da tabela AttractionType
CREATE TABLE attraction_type (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(100) NOT NULL
);

CREATE SEQUENCE attractiontype_sequence START 1;

-- Tabela de relacionamento One-to-One entre Attraction e AttractionType
CREATE TABLE attraction_attractiontype (
    attraction_id BIGINT PRIMARY KEY,
    attractiontype_id BIGINT NOT NULL,
    CONSTRAINT fk_attractiontype FOREIGN KEY (attraction_id) REFERENCES attraction(id),
    CONSTRAINT fk_type FOREIGN KEY (attractiontype_id) REFERENCES attraction_type(id)
);

-- Criação da tabela MoreInfoLink
CREATE TABLE more_info_link (
    id BIGSERIAL PRIMARY KEY,
    link VARCHAR(500) NOT NULL,
    description VARCHAR(500) NOT NULL
);

CREATE SEQUENCE moreinfolink_sequence START 1;
