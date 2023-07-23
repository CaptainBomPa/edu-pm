INSERT INTO PROJECT (PROJECT_NAME)
VALUES ('test project 1'),
       ('test project 2'),
       ('test project 3');

INSERT INTO FEATURE (FEATURE_NAME, DESCRIPTION, PROJECT_ID)
VALUES ('Some feature 1', 'description 1', 1),
       ('Some feature 2', 'description 2', 2),
       ('Some feature 3', 'description 3', 3);

INSERT INTO TEAM (TEAM_NAME, PROJECT_ID)
VALUES ('Team 1-1', 1),
       ('Team 1-2', 1),
       ('Team 2-1', 2),
       ('Team 2-1', 2),
       ('Team 3-1', 3),
       ('Team 3-1', 3);

INSERT INTO USER_STORY (USER_STORY_NAME, DESCRIPTION, FEATURE_ID)
VALUES ('As a user I want... 1-1', 'Lorem ipsum 1-1....', 1),
       ('As a user I want... 1-2', 'Lorem ipsum 1-2....', 1),
       ('As a user I want... 2-1', 'Lorem ipsum 2-1....', 2),
       ('As a user I want... 2-2', 'Lorem ipsum 2-2....', 2),
       ('As a user I want... 3-1', 'Lorem ipsum 3-1....', 3),
       ('As a user I want... 3-2', 'Lorem ipsum 3-2....', 3);

INSERT INTO TASK (TASK_NAME, DESCRIPTION, USER_STORY_ID)
VALUES ('Task US1', 'Do it', 1),
       ('Task US2', 'Do it', 2),
       ('Task US3', 'Do it', 3),
       ('Task US4', 'Do it', 4),
       ('Task US5', 'Do it', 5),
       ('Task US6', 'Do it', 6);

INSERT INTO USERS (USERNAME, PASSWORD, ROLE, TEAM_ID)
VALUES ('admin', '$2a$10$R2rNkxslm96X9a/hOUx05..X8EmTJ89tjlyylGvBlAbN0qAQNKiAS', 'admin', 1);
