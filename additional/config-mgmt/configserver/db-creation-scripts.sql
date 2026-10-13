USE configDB;

CREATE TABLE PROPERTIES (
	ID INT PRIMARY KEY AUTO_INCREMENT,
	APPLICATION VARCHAR(200),
	PROFILE VARCHAR(200),
	LABEL VARCHAR(200),
	PROP_KEY VARCHAR(200),
	PROP_VALUE VARCHAR(200),
	CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

ALTER TABLE PROPERTIES RENAME TO properties;
ALTER TABLE properties RENAME COLUMN ID to id, RENAME COLUMN APPLICATION to application;
alter table properties RENAME COLUMN PROFILE TO profile, RENAME COLUMN LABEL TO label, RENAME COLUMN PROP_KEY TO prop_key,
RENAME COLUMN CREATED_AT TO created_at, RENAME COLUMN UPDATED_AT TO updated_at ;
ALTER TABLE properties  RENAME COLUMN PROP_VALUE TO prop_value;

INSERT INTO
properties (application, profile, label, prop_key, prop_value)
VALUES ('democonfig', 'prod', 'main', 'build.id', 'prod build id DB') ;

INSERT INTO properties (application, profile, label, prop_key, prop_value)
VALUES ('democonfig', 'prod', 'main', 'build.name', 'prod build name DB'),
       ('democonfig', 'prod', 'main', 'build.version', 'prod build version DB'),
       ('democonfig', 'prod', 'main', 'build.type', 'prod build type DB');

INSERT INTO properties (application, profile, label, prop_key, prop_value)
VALUES ('democonfig', 'dev', 'main', 'build.id', 'build id dev DB'),
		('democonfig', 'dev', 'main', 'build.name', 'build name dev DB'),
       ('democonfig', 'dev', 'main', 'build.version', 'build version dev DB'),
       ('democonfig', 'dev', 'main', 'build.type', 'build type dev DB');

ALTER TABLE properties MODIFY prop_value VARCHAR(5000);

UPDATE configDB.properties object
set prop_value = '{cipher}AYCCeCzP4MjYmN61Mco8QJyVGEsYylAfhjwDrGziao4iNciNnFGEuAbUDIliTsYrWrFq5iWP7agilT0WtZIPT5ZqRhzmfRNMy1EZicisTjSY06WBHSMsCc5/FCMGzW2jpwNhH5DWLuDgD5TDf0UsMWFu1Y1q1fGFf1TxrMevrmA9IannMaMFjTZLJAZSbBUbmvhEJA2Mlyoresi/dQV2T3Rr4MDhIxEqo/4PhDfreus3PNyHUW+QetGl2ZxVgPQknWFy+4nZULgrLE0Lqn6mJzRlWFEwsk9ZPqprEd3wXUxebW5KSvbBna22oQD5cY5qJEAJZG7qgF0J2sOrub8CR7snMRLO5x6cWPjQiu4ZVZersHftjxjz0aBgvVYxTtvDfeWPTGvdbOWmSYpd2V4nA8nZoSXrVJ/HohOjM3M4UDRHRIPATh49+EtYCpROfu+QE8H+By+QmyVDszF76AczmtLpe+aF09FaUVytsZKSym+7oXL9W3DmP/DYWngDqXulaLYJObtwaMSAlmO4q6dcKJ4kyzfUlW/2Ctink4RAf1av+Q=='
where profile='dev' and prop_key='build.id'
