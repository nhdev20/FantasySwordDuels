DROP TABLE IF EXISTS run_selection, run_skill, run, selection, skill;

CREATE TABLE run (
run_id serial PRIMARY KEY,
date_time timestamp NOT NULL,
highest_level_completed int NOT NULL
);

CREATE TABLE skill (
skill_name varchar(50) PRIMARY KEY,
description varchar(500) NOT NULL
);

CREATE TABLE run_skill (
run_id int,
skill_name varchar(50),
skill_value int NOT NULL,
CONSTRAINT pk_run_skill_run_id_skill_name PRIMARY KEY (run_id, skill_name),
CONSTRAINT fk_run_skill_run_id FOREIGN KEY (run_id) REFERENCES run(run_id),
CONSTRAINT fk_run_skill_skill_name FOREIGN KEY (skill_name) REFERENCES skill(skill_name)
);

CREATE TABLE selection (
selection_name varchar(50) PRIMARY KEY,
description varchar(500) NOT NULL
);

CREATE TABLE run_selection (
run_id int,
selection_name varchar(50),
selection_count int NOT NULL,
CONSTRAINT pk_run_selection_run_id_selection_name PRIMARY KEY (run_id, selection_name),
CONSTRAINT fk_run_selection_run_id FOREIGN KEY (run_id) REFERENCES run(run_id),
CONSTRAINT fk_run_selection_selection_name FOREIGN KEY (selection_name) REFERENCES selection(selection_name)
);

INSERT INTO skill (skill_name, description) VALUES ('Health', 'Health determines how much damage you can take and continue forward.');
INSERT INTO skill (skill_name, description) VALUES ('Speed', 'Speed determines how quickly you can move out of the way or perform a quick attack.');
INSERT INTO skill (skill_name, description) VALUES ('Strength', 'Strength determines your damage and armor protection.');

INSERT INTO selection (selection_name, description) VALUES ('Quick Attack', 'A quick, low damage attack.');
INSERT INTO selection (selection_name, description) VALUES ('Heavy Attack', 'A slow, high damage attack.');
INSERT INTO selection (selection_name, description) VALUES ('Block', 'A defensive move that prevents damage based on incoming attack type.');
INSERT INTO selection (selection_name, description) VALUES ('Dodge', 'A defensive move attempting to avoid an attack based on Speed.');
INSERT INTO selection (selection_name, description) VALUES ('Heal', 'Restores Health to full value.');
INSERT INTO selection (selection_name, description) VALUES ('Fiery Strike', 'Automatically inflicts 10 damage on the enemy.');
