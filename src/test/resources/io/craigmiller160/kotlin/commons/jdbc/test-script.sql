CREATE TABLE people (
  person_id BIGINT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255),
  middle_name VARCHAR(255),
  last_name VARCHAR(255),
  PRIMARY KEY (person_id)
);

INSERT INTO people(first_name, middle_name, last_name) VALUES ('Bob','Eugene','Saget');
INSERT INTO people(first_name, middle_name, last_name) VALUES ('John','Henry','Doe');