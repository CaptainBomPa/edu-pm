INSERT INTO PROJECT (PROJECT_NAME)
VALUES ('Project Alpha'),
       ('Project Beta'),
       ('Project Charlie');

INSERT INTO FEATURE (FEATURE_NAME, DESCRIPTION, PROJECT_ID)
VALUES ('New search algorithm to beat Google', 'We need to aim high, and beat the best companies on the market', 1),
       ('New design on most visited pages', 'New UI/UX for most visited pages. Need to refresh and make this pages younger', 2),
       ('Improvements to performance on server side', 'Backend improvements to speed up the response from server', 3);

INSERT INTO TEAM (TEAM_NAME)
VALUES ('Team Bulldogs'),
       ('Team Lions'),
       ('Team Eagles'),
       ('Team Cats'),
       ('Team Snakes'),
       ('Team Spiders');

INSERT INTO ITERATION(START_DATE, END_DATE)
VALUES ('2023-09-01T12:00:01', '2023-09-14T12:00:00'),
       ('2023-09-14T12:00:01', '2023-09-28T12:00:00'),
       ('2023-09-28T12:00:01', '2023-10-12T12:00:00'),
       ('2023-10-12T12:00:01', '2023-10-26T12:00:00'),
       ('2023-10-26T12:00:01', '2023-11-10T12:00:00');

INSERT INTO USERS (USERNAME, PASSWORD, TEAM_ID, ROLES, FIRST_NAME, LAST_NAME)
VALUES ('admin', '$2a$10$R2rNkxslm96X9a/hOUx05..X8EmTJ89tjlyylGvBlAbN0qAQNKiAS', 1, 'ADMINISTRATOR', 'Jan', 'Kowalski'),
       ('user', '$2a$10$.bdr4pJ94f3sAUTBzy2G9eADtbMPyohOzo397uXUhKJ6EQZ93fqAK', 4, 'USER_READ', 'Adam', 'Nowak');

-- INSERT INTO USER_STORY (USER_STORY_NAME, DESCRIPTION, FEATURE_ID, ITERATION_IT_NUMBER, STORY_POINTS, ASSIGNED_USER_ID,
--                         TEAM_ID)
-- VALUES ('Rework of algorithms', 'Lorem ipsum 1-1....', 1, 4, 1, 1, 1),
--        ('As a user I want... 1-2', 'Lorem ipsum 1-2....', 1, 4, 3, 1, 1),
--        ('As a user I want... 2-1', 'Lorem ipsum 2-1....', 2, 4, 5, 1, 1),
--        ('As a user I want... 2-2', 'Lorem ipsum 2-2....', 2, 4, 3, 1, 1),
--        ('As a user I want... 3-1', 'Lorem ipsum 3-1....', 3, 4, 3, 1, 1),
--        ('As a user I want... 3-2', 'Lorem ipsum 3-2....', 3, 4, 8, 1, 1),
--        ('As a user I want... 3-2-1', 'Lorem ipsum 3-2....', 3, 4, 8, 1, 1),
--        ('As a user I want... 3-2-2', 'Lorem ipsum 3-2....', 3, 4, 8, 1, 1),
--        ('As a user I want... 3-2-3', 'Lorem ipsum 3-2....', 3, 4, 8, 1, 1),
--        ('As a user I want... 3-2-4', 'Lorem ipsum 3-2....', 3, 4, 8, 1, 1),
--        ('As a user I want... 3-2-5', 'Lorem ipsum 3-2....', 3, 4, 8, 1, 1),
--        ('As a user I want... 3-2-6', 'Lorem ipsum 3-2....', 3, 4, 8, 1, 1),
--        ('As a user I want... 3-2-7', 'Lorem ipsum 3-2....', 3, 4, 8, 1, 1),
--        ('As a user I want... 3-2-8', 'Lorem ipsum 3-2....', 3, 4, 8, 1, 1);

INSERT INTO USER_STORY (USER_STORY_NAME, DESCRIPTION, FEATURE_ID, ITERATION_IT_NUMBER, STORY_POINTS, ASSIGNED_USER_ID, TEAM_ID)
VALUES ('Improved search result filtering',
        'Enhancing the filtering options for search results to provide a better user experience.', 1, 4, 1, 1, 1),
       ('Advanced search result sorting',
        'Implementing advanced sorting algorithms for search results to boost performance.', 1, 4, 2, 1, 1),
       ('User-friendly homepage redesign',
        'Redesigning the homepage with a user-centric approach to improve engagement.', 2, 4, 3, 2, 1),
       ('Profile page makeover', 'Giving the profile page a fresh new look to increase user satisfaction.', 2, 4, 5, 2,
        1),
       ('Optimized server-side caching', 'Implementing server-side caching techniques to speed up data retrieval.', 3,
        4, 8, 1, 1),
       ('Enhanced database indexing', 'Improving database indexing for faster query processing and scalability.', 3, 4,
        13, 2, 1),
       ('Streamlined registration process',
        'Simplifying the user registration process for a smoother onboarding experience.', 1, 4, 1, 1, 1),
       ('Personalized user recommendations',
        'Implementing recommendation algorithms for personalized user content suggestions.', 1, 4, 2, 2, 1),
       ('Efficient data synchronization',
        'Enhancing data synchronization processes to ensure data consistency across devices.', 2, 4, 3, 2, 1),
       ('Mobile-friendly interface', 'Adapting the interface for mobile devices to cater to a broader user base.', 2, 4,
        5, 1, 1),
       ('Improved notification system', 'Enhancing the notification system to keep users informed and engaged.', 3, 4,
        8, 2, 1),
       ('Security enhancements', 'Implementing advanced security measures to protect user data and privacy.', 3, 4, 13,
        2, 1),
       ('User feedback integration', 'Integrating a user feedback system to gather valuable insights and suggestions.',
        1, 4, 1, 1, 1),
       ('Content sharing options', 'Adding options for users to easily share content with their social networks.', 1, 4,
        2, 2, 1),
       ('Social media login', 'Allowing users to log in using their social media accounts for added convenience.', 2, 4,
        3, 1, 1),
       ('Gamification elements', 'Introducing gamification elements to increase user engagement and interaction.', 2, 4,
        5, 2, 1),
       ('Enhanced image uploading', 'Improving image uploading functionality for a smoother user experience.', 3, 4, 8,
        1, 1),
       ('Integrated user chat', 'Implementing a real-time chat feature for users to connect and communicate.', 3, 4, 13,
        2, 1),
       ('Content recommendation engine',
        'Developing a recommendation engine to suggest content based on user preferences.', 1, 4, 1, 1, 1),
       ('Accessibility improvements', 'Enhancing the platforms accessibility features to cater to all users.', 1, 4, 2,
        1, 1),
       ('Localized content support', 'Adding support for multiple languages and localized content.', 2, 4, 3, 1, 1),
       ('Interactive user tutorials', 'Creating interactive tutorials to help users make the most of the platform.', 2,
        4, 5, 1, 1),
       ('Enhanced video streaming', 'Improving video streaming quality and performance for better user experience.', 3,
        4, 8, 1, 1),
       ('Advanced content analytics', 'Implementing analytics to track user engagement and content performance.', 3, 4,
        13, 2, 1),
       ('User profile customization',
        'Allowing users to customize their profiles with personal information and themes.', 1, 4, 1, 1, 1);

INSERT INTO TASK (TASK_NAME, DESCRIPTION, USER_STORY_ID)
VALUES
    ('Implement Feature 1', 'Implement new feature as specified', 1),
    ('QA Testing', 'Perform quality assurance testing for Feature 1', 1),
    ('Documentation Update', 'Update documentation for Feature 1', 1);

INSERT INTO TASK (TASK_NAME, DESCRIPTION, USER_STORY_ID)
VALUES
    ('Implement Feature 2', 'Implement new feature as specified', 2),
    ('QA Tests', 'Conduct quality assurance tests for Feature 2', 2),
    ('Code Review', 'Perform a code review for Feature 2', 2);

INSERT INTO TASK (TASK_NAME, DESCRIPTION, USER_STORY_ID)
VALUES
    ('Design Improvements', 'Make design improvements as described', 3),
    ('Testing Updates', 'Update and conduct testing for Feature 3', 3),
    ('User Feedback Review', 'Review and address user feedback for Feature 3', 3);

INSERT INTO TASK (TASK_NAME, DESCRIPTION, USER_STORY_ID)
VALUES
    ('Performance Enhancements', 'Implement performance enhancements as outlined', 4),
    ('Security Testing', 'Perform security testing for Feature 4', 4),
    ('User Documentation', 'Create user documentation for Feature 4', 4);

INSERT INTO TASK (TASK_NAME, DESCRIPTION, USER_STORY_ID)
VALUES
    ('Optimize Server-Side', 'Optimize server-side for improved performance', 5),
    ('Load Testing', 'Conduct load testing on the updated server-side', 5),
    ('Error Handling', 'Enhance error handling for Feature 5', 5);

INSERT INTO TASK (TASK_NAME, DESCRIPTION, USER_STORY_ID)
VALUES
    ('User Feedback Integration', 'Integrate user feedback for Feature 6', 6),
    ('UI/UX Tweaks', 'Make UI/UX tweaks as per feedback', 6),
    ('Cross-Browser Testing', 'Conduct cross-browser testing for Feature 6', 6);

INSERT INTO TASK (TASK_NAME, DESCRIPTION, USER_STORY_ID)
VALUES
    ('Implement Feature 1', 'Implement new feature as specified', 7),
    ('QA Testing', 'Perform quality assurance testing for Feature 1', 7),
    ('Documentation Update', 'Update documentation for Feature 1', 7);

INSERT INTO TASK (TASK_NAME, DESCRIPTION, USER_STORY_ID)
VALUES
    ('Implement Feature 2', 'Implement new feature as specified', 8),
    ('QA Tests', 'Conduct quality assurance tests for Feature 2', 8),
    ('Code Review', 'Perform a code review for Feature 2', 8);

INSERT INTO TASK (TASK_NAME, DESCRIPTION, USER_STORY_ID)
VALUES
    ('Design Improvements', 'Make design improvements as described', 9),
    ('Testing Updates', 'Update and conduct testing for Feature 3', 9),
    ('User Feedback Review', 'Review and address user feedback for Feature 3', 9);

INSERT INTO TASK (TASK_NAME, DESCRIPTION, USER_STORY_ID)
VALUES
    ('Performance Enhancements', 'Implement performance enhancements as outlined', 10),
    ('Security Testing', 'Perform security testing for Feature 4', 10),
    ('User Documentation', 'Create user documentation for Feature 4', 10);

INSERT INTO TASK (TASK_NAME, DESCRIPTION, USER_STORY_ID)
VALUES
    ('Optimize Server-Side', 'Optimize server-side for improved performance', 11),
    ('Load Testing', 'Conduct load testing on the updated server-side', 11),
    ('Error Handling', 'Enhance error handling for Feature 5', 11);

INSERT INTO TASK (TASK_NAME, DESCRIPTION, USER_STORY_ID)
VALUES
    ('User Feedback Integration', 'Integrate user feedback for Feature 6', 12),
    ('UI/UX Tweaks', 'Make UI/UX tweaks as per feedback', 12),
    ('Cross-Browser Testing', 'Conduct cross-browser testing for Feature 6', 12);

INSERT INTO TASK (TASK_NAME, DESCRIPTION, USER_STORY_ID)
VALUES
    ('Implement Feature 1', 'Implement new feature as specified', 13),
    ('QA Testing', 'Perform quality assurance testing for Feature 1', 13),
    ('Documentation Update', 'Update documentation for Feature 1', 13);

INSERT INTO TASK (TASK_NAME, DESCRIPTION, USER_STORY_ID)
VALUES
    ('Implement Feature 2', 'Implement new feature as specified', 14),
    ('QA Tests', 'Conduct quality assurance tests for Feature 2', 14),
    ('Code Review', 'Perform a code review for Feature 2', 14);

INSERT INTO TASK (TASK_NAME, DESCRIPTION, USER_STORY_ID)
VALUES
    ('Design Improvements', 'Make design improvements as described', 15),
    ('Testing Updates', 'Update and conduct testing for Feature 3', 15),
    ('User Feedback Review', 'Review and address user feedback for Feature 3', 15);

INSERT INTO TASK (TASK_NAME, DESCRIPTION, USER_STORY_ID)
VALUES
    ('Performance Enhancements', 'Implement performance enhancements as outlined', 16),
    ('Security Testing', 'Perform security testing for Feature 4', 16),
    ('User Documentation', 'Create user documentation for Feature 4', 16);

INSERT INTO TASK (TASK_NAME, DESCRIPTION, USER_STORY_ID)
VALUES
    ('Optimize Server-Side', 'Optimize server-side for improved performance', 17),
    ('Load Testing', 'Conduct load testing on the updated server-side', 17),
    ('Error Handling', 'Enhance error handling for Feature 5', 17);

INSERT INTO TASK (TASK_NAME, DESCRIPTION, USER_STORY_ID)
VALUES
    ('User Feedback Integration', 'Integrate user feedback for Feature 6', 18),
    ('UI/UX Tweaks', 'Make UI/UX tweaks as per feedback', 18),
    ('Cross-Browser Testing', 'Conduct cross-browser testing for Feature 6', 18);

INSERT INTO TASK (TASK_NAME, DESCRIPTION, USER_STORY_ID)
VALUES
    ('Implement Feature 1', 'Implement new feature as specified', 19),
    ('QA Testing', 'Perform quality assurance testing for Feature 1', 19),
    ('Documentation Update', 'Update documentation for Feature 1', 19);

INSERT INTO TASK (TASK_NAME, DESCRIPTION, USER_STORY_ID)
VALUES
    ('Implement Feature 2', 'Implement new feature as specified', 20),
    ('QA Tests', 'Conduct quality assurance tests for Feature 2', 20),
    ('Code Review', 'Perform a code review for Feature 2', 20);

INSERT INTO TASK (TASK_NAME, DESCRIPTION, USER_STORY_ID)
VALUES
    ('Design Improvements', 'Make design improvements as described', 21),
    ('Testing Updates', 'Update and conduct testing for Feature 3', 21),
    ('User Feedback Review', 'Review and address user feedback for Feature 3', 21);

INSERT INTO TASK (TASK_NAME, DESCRIPTION, USER_STORY_ID)
VALUES
    ('Performance Enhancements', 'Implement performance enhancements as outlined', 22),
    ('Security Testing', 'Perform security testing for Feature 4', 22),
    ('User Documentation', 'Create user documentation for Feature 4', 22);

INSERT INTO TASK (TASK_NAME, DESCRIPTION, USER_STORY_ID)
VALUES
    ('Optimize Server-Side', 'Optimize server-side for improved performance', 23),
    ('Load Testing', 'Conduct load testing on the updated server-side', 23),
    ('Error Handling', 'Enhance error handling for Feature 5', 23);

INSERT INTO TASK (TASK_NAME, DESCRIPTION, USER_STORY_ID)
VALUES
    ('User Feedback Integration', 'Integrate user feedback for Feature 6', 24),
    ('UI/UX Tweaks', 'Make UI/UX tweaks as per feedback', 24),
    ('Cross-Browser Testing', 'Conduct cross-browser testing for Feature 6', 24);


INSERT INTO USERS_PROJECTS(USER_ID, PROJECTS_ID)
VALUES (1, 1),
       (1, 2)
