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