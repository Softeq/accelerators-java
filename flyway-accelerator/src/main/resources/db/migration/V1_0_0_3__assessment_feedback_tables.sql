
CREATE TABLE assessment (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    target_user_id int,
    assesment_date datetime
);

alter table assessment add constraint fk_assessment_users__target_user_id foreign key (target_user_id) references users (id);

CREATE TABLE feedback (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id int,
    assessment_id int,
    feedback_date datetime,
    score decimal(2,2),
    comment varchar (1024)
);

alter table feedback add constraint fk_feedback_users__user_id foreign key (user_id) references users (id);
alter table feedback add constraint fk_feedback_assessment__assesment_id foreign key (assessment_id) references assessment (id);