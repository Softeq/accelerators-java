/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

CREATE TABLE assessment
(
    id              uuid NOT NULL DEFAULT random_uuid(),
    user_id         uuid,
    average         decimal(5, 2),
    feedbacks_count int,
    update_date     timestamp,
    CONSTRAINT PK_assessment PRIMARY KEY (id)
);

ALTER TABLE assessment
    ADD CONSTRAINT FK_assessment_user FOREIGN KEY (user_id) REFERENCES users (id);

CREATE TABLE feedback
(
    id             uuid NOT NULL DEFAULT random_uuid(),
    user_id        uuid,
    target_user_id uuid,
    feedback_date  timestamp,
    score          decimal(5, 2),
    comment        varchar(1024),
    CONSTRAINT PK_feedback PRIMARY KEY (id)
);

ALTER TABLE feedback
    ADD CONSTRAINT FK_feedback_users FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE feedback
    ADD CONSTRAINT FK_feedback_users_target FOREIGN KEY (target_user_id) REFERENCES users (id);