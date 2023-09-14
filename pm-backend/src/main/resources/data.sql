INSERT INTO PROJECT (PROJECT_NAME)
VALUES ('test project 1'),
       ('test project 2'),
       ('test project 3');

INSERT INTO FEATURE (FEATURE_NAME, DESCRIPTION, PROJECT_ID)
VALUES ('Some feature 1', 'description 1', 1),
       ('Some feature 2', 'description 2', 2),
       ('Some feature 3', 'description 3', 3);

INSERT INTO TEAM (TEAM_NAME)
VALUES ('Team 1-1'),
       ('Team 1-2'),
       ('Team 2-1'),
       ('Team 2-1'),
       ('Team 3-1'),
       ('Team 3-1');

INSERT INTO ITERATION(START_DATE, END_DATE)
VALUES ('2023-09-01T12:00:01', '2023-09-14T12:00:00'),
       ('2023-09-14T12:00:01', '2023-09-28T12:00:00'),
       ('2023-09-28T12:00:01', '2023-10-12T12:00:00'),
       ('2023-10-12T12:00:01', '2023-10-26T12:00:00'),
       ('2023-10-26T12:00:01', '2023-11-10T12:00:00');

INSERT INTO USERS (USERNAME, PASSWORD, TEAM_ID, ROLES)
VALUES ('admin', '$2a$10$R2rNkxslm96X9a/hOUx05..X8EmTJ89tjlyylGvBlAbN0qAQNKiAS', 1, 'ADMINISTRATOR');

INSERT INTO USER_STORY (USER_STORY_NAME, DESCRIPTION, FEATURE_ID, ITERATION_IT_NUMBER, STORY_POINTS, ASSIGNED_USER_ID)
VALUES ('As a user I want... 1-1', 'Lorem ipsum 1-1....', 1, 1, 1, 1),
       ('As a user I want... 1-2', 'Lorem ipsum 1-2....', 1, 1, 3, 1),
       ('As a user I want... 2-1', 'Lorem ipsum 2-1....', 2, 1, 5, 1),
       ('As a user I want... 2-2', 'Lorem ipsum 2-2....', 2, 2, 3, 1),
       ('As a user I want... 3-1', 'Lorem ipsum 3-1....', 3, 2, 3, 1),
       ('As a user I want... 3-2', 'Lorem ipsum 3-2....', 3, 2, 8, 1);

INSERT INTO TASK (TASK_NAME, DESCRIPTION, USER_STORY_ID)
VALUES ('Task US1', 'Do it', 1),
       ('Task US2', 'Do it', 2),
       ('Task US3', 'Do it', 3),
       ('Task US4', 'Do it', 4),
       ('Task US5', 'Do it', 5),
       ('Task US6', 'Do it', 6);

INSERT INTO USERS_PROJECTS(USER_ID, PROJECTS_ID)
VALUES (1, 1),
       (1, 2)
