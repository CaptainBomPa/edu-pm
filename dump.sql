--
-- PostgreSQL database cluster dump
--

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Drop databases (except postgres and template1)
--





--
-- Drop roles
--

DROP ROLE postgres;


--
-- Roles
--

CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'SCRAM-SHA-256$4096:sXPNrYp5kH7w/Q2dRqV8nA==$FsSuqg+iOLuHsfVkuSm1YCkila9c/8R+Ndzqm+uZg0E=:AJeC8WNR3H+861RcgoGF7C9l3cOngy7zHQqTICKLj5U=';

--
-- User Configurations
--








--
-- Databases
--

--
-- Database "template1" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0 (Debian 16.0-1.pgdg120+1)
-- Dumped by pg_dump version 16.0 (Debian 16.0-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

UPDATE pg_catalog.pg_database SET datistemplate = false WHERE datname = 'template1';
DROP DATABASE template1;
--
-- Name: template1; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE template1 WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE template1 OWNER TO postgres;

\connect template1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: DATABASE template1; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE template1 IS 'default template for new databases';


--
-- Name: template1; Type: DATABASE PROPERTIES; Schema: -; Owner: postgres
--

ALTER DATABASE template1 IS_TEMPLATE = true;


\connect template1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: DATABASE template1; Type: ACL; Schema: -; Owner: postgres
--

REVOKE CONNECT,TEMPORARY ON DATABASE template1 FROM PUBLIC;
GRANT CONNECT ON DATABASE template1 TO PUBLIC;


--
-- PostgreSQL database dump complete
--

--
-- Database "postgres" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0 (Debian 16.0-1.pgdg120+1)
-- Dumped by pg_dump version 16.0 (Debian 16.0-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE postgres;
--
-- Name: postgres; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE postgres OWNER TO postgres;

\connect postgres

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: DATABASE postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: feature; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.feature (
    id integer NOT NULL,
    description character varying(255),
    feature_name character varying(255),
    project_id integer
);


ALTER TABLE public.feature OWNER TO postgres;

--
-- Name: feature_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.feature_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.feature_id_seq OWNER TO postgres;

--
-- Name: feature_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.feature_id_seq OWNED BY public.feature.id;


--
-- Name: iteration; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.iteration (
    it_number integer NOT NULL,
    end_date timestamp(6) without time zone,
    start_date timestamp(6) without time zone
);


ALTER TABLE public.iteration OWNER TO postgres;

--
-- Name: iteration_it_number_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.iteration_it_number_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.iteration_it_number_seq OWNER TO postgres;

--
-- Name: iteration_it_number_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.iteration_it_number_seq OWNED BY public.iteration.it_number;


--
-- Name: project; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.project (
    id integer NOT NULL,
    project_name character varying(255)
);


ALTER TABLE public.project OWNER TO postgres;

--
-- Name: project_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.project_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.project_id_seq OWNER TO postgres;

--
-- Name: project_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.project_id_seq OWNED BY public.project.id;


--
-- Name: tag; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tag (
    id integer NOT NULL,
    tag_name character varying(255)
);


ALTER TABLE public.tag OWNER TO postgres;

--
-- Name: tag_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tag_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tag_id_seq OWNER TO postgres;

--
-- Name: tag_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tag_id_seq OWNED BY public.tag.id;


--
-- Name: tag_user_stories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tag_user_stories (
    tag_id integer NOT NULL,
    user_stories_id integer NOT NULL
);


ALTER TABLE public.tag_user_stories OWNER TO postgres;

--
-- Name: task; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.task (
    id integer NOT NULL,
    description character varying(255),
    task_name character varying(255),
    user_story_id integer
);


ALTER TABLE public.task OWNER TO postgres;

--
-- Name: task_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.task_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.task_id_seq OWNER TO postgres;

--
-- Name: task_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.task_id_seq OWNED BY public.task.id;


--
-- Name: team; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.team (
    id integer NOT NULL,
    team_name character varying(255),
    project_id integer
);


ALTER TABLE public.team OWNER TO postgres;

--
-- Name: team_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.team_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.team_id_seq OWNER TO postgres;

--
-- Name: team_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.team_id_seq OWNED BY public.team.id;


--
-- Name: user_story; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_story (
    id integer NOT NULL,
    block_reason character varying(255),
    blocked boolean DEFAULT false,
    description character varying(2000),
    state character varying(250) DEFAULT 'NEW'::character varying,
    story_points integer,
    user_story_name character varying(255),
    assigned_user_id integer,
    feature_id integer,
    iteration_it_number integer,
    team_id integer
);


ALTER TABLE public.user_story OWNER TO postgres;

--
-- Name: user_story_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_story_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.user_story_id_seq OWNER TO postgres;

--
-- Name: user_story_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_story_id_seq OWNED BY public.user_story.id;


--
-- Name: user_story_tags; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_story_tags (
    user_story_id integer NOT NULL,
    tags_id integer NOT NULL
);


ALTER TABLE public.user_story_tags OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    account_activated boolean,
    email character varying(255),
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    password character varying(255),
    roles character varying(255)[],
    username character varying(255),
    project_id integer,
    team_id integer
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: feature id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feature ALTER COLUMN id SET DEFAULT nextval('public.feature_id_seq'::regclass);


--
-- Name: iteration it_number; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.iteration ALTER COLUMN it_number SET DEFAULT nextval('public.iteration_it_number_seq'::regclass);


--
-- Name: project id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project ALTER COLUMN id SET DEFAULT nextval('public.project_id_seq'::regclass);


--
-- Name: tag id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tag ALTER COLUMN id SET DEFAULT nextval('public.tag_id_seq'::regclass);


--
-- Name: task id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task ALTER COLUMN id SET DEFAULT nextval('public.task_id_seq'::regclass);


--
-- Name: team id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.team ALTER COLUMN id SET DEFAULT nextval('public.team_id_seq'::regclass);


--
-- Name: user_story id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_story ALTER COLUMN id SET DEFAULT nextval('public.user_story_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: feature; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.feature (id, description, feature_name, project_id) FROM stdin;
1	We need to aim high, and beat the best companies on the market	New search algorithm to beat Google	1
2	New UI/UX for most visited pages. Need to refresh and make this pages younger	New design on most visited pages	2
3	Backend improvements to speed up the response from server	Improvements to performance on server side	3
\.


--
-- Data for Name: iteration; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.iteration (it_number, end_date, start_date) FROM stdin;
1	2023-11-11 00:00:00	2023-10-28 00:00:00
2	2023-11-25 00:00:00	2023-11-11 00:00:01
3	2023-12-09 00:00:00	2023-11-25 00:00:01
4	2023-12-23 00:00:00	2023-12-09 00:00:01
5	2024-01-06 00:00:00	2023-12-23 00:00:01
6	2024-01-20 00:00:00	2024-01-06 00:00:01
7	2024-02-03 00:00:00	2024-01-20 00:00:01
8	2024-02-17 00:00:00	2024-02-03 00:00:01
9	2024-03-02 00:00:00	2024-02-17 00:00:01
10	2024-03-16 00:00:00	2024-03-02 00:00:01
11	2024-03-30 00:00:00	2024-03-16 00:00:01
\.


--
-- Data for Name: project; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.project (id, project_name) FROM stdin;
1	Project Alpha
2	Project Beta
3	Project Charlie
\.


--
-- Data for Name: tag; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tag (id, tag_name) FROM stdin;
1	Release 1.0
2	Release 1.1
3	Product Stabilization
\.


--
-- Data for Name: tag_user_stories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tag_user_stories (tag_id, user_stories_id) FROM stdin;
\.


--
-- Data for Name: task; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.task (id, description, task_name, user_story_id) FROM stdin;
1	Implement new feature as specified	Implement Feature 1	1
2	Perform quality assurance testing for Feature 1	QA Testing	1
3	Update documentation for Feature 1	Documentation Update	1
4	Implement new feature as specified	Implement Feature 2	2
5	Conduct quality assurance tests for Feature 2	QA Tests	2
6	Perform a code review for Feature 2	Code Review	2
7	Make design improvements as described	Design Improvements	3
8	Update and conduct testing for Feature 3	Testing Updates	3
9	Review and address user feedback for Feature 3	User Feedback Review	3
10	Implement performance enhancements as outlined	Performance Enhancements	4
11	Perform security testing for Feature 4	Security Testing	4
12	Create user documentation for Feature 4	User Documentation	4
13	Optimize server-side for improved performance	Optimize Server-Side	5
14	Conduct load testing on the updated server-side	Load Testing	5
15	Enhance error handling for Feature 5	Error Handling	5
16	Integrate user feedback for Feature 6	User Feedback Integration	6
17	Make UI/UX tweaks as per feedback	UI/UX Tweaks	6
18	Conduct cross-browser testing for Feature 6	Cross-Browser Testing	6
19	Implement new feature as specified	Implement Feature 1	7
20	Perform quality assurance testing for Feature 1	QA Testing	7
21	Update documentation for Feature 1	Documentation Update	7
22	Implement new feature as specified	Implement Feature 2	8
23	Conduct quality assurance tests for Feature 2	QA Tests	8
24	Perform a code review for Feature 2	Code Review	8
25	Make design improvements as described	Design Improvements	9
26	Update and conduct testing for Feature 3	Testing Updates	9
27	Review and address user feedback for Feature 3	User Feedback Review	9
28	Implement performance enhancements as outlined	Performance Enhancements	10
29	Perform security testing for Feature 4	Security Testing	10
30	Create user documentation for Feature 4	User Documentation	10
31	Optimize server-side for improved performance	Optimize Server-Side	11
32	Conduct load testing on the updated server-side	Load Testing	11
33	Enhance error handling for Feature 5	Error Handling	11
34	Integrate user feedback for Feature 6	User Feedback Integration	12
35	Make UI/UX tweaks as per feedback	UI/UX Tweaks	12
36	Conduct cross-browser testing for Feature 6	Cross-Browser Testing	12
37	Implement new feature as specified	Implement Feature 1	13
38	Perform quality assurance testing for Feature 1	QA Testing	13
39	Update documentation for Feature 1	Documentation Update	13
40	Implement new feature as specified	Implement Feature 2	14
41	Conduct quality assurance tests for Feature 2	QA Tests	14
42	Perform a code review for Feature 2	Code Review	14
43	Make design improvements as described	Design Improvements	15
44	Update and conduct testing for Feature 3	Testing Updates	15
45	Review and address user feedback for Feature 3	User Feedback Review	15
46	Implement performance enhancements as outlined	Performance Enhancements	16
47	Perform security testing for Feature 4	Security Testing	16
48	Create user documentation for Feature 4	User Documentation	16
49	Optimize server-side for improved performance	Optimize Server-Side	17
50	Conduct load testing on the updated server-side	Load Testing	17
51	Enhance error handling for Feature 5	Error Handling	17
52	Integrate user feedback for Feature 6	User Feedback Integration	18
53	Make UI/UX tweaks as per feedback	UI/UX Tweaks	18
54	Conduct cross-browser testing for Feature 6	Cross-Browser Testing	18
55	Implement new feature as specified	Implement Feature 1	19
56	Perform quality assurance testing for Feature 1	QA Testing	19
57	Update documentation for Feature 1	Documentation Update	19
58	Implement new feature as specified	Implement Feature 2	20
59	Conduct quality assurance tests for Feature 2	QA Tests	20
60	Perform a code review for Feature 2	Code Review	20
61	Make design improvements as described	Design Improvements	21
62	Update and conduct testing for Feature 3	Testing Updates	21
63	Review and address user feedback for Feature 3	User Feedback Review	21
64	Implement performance enhancements as outlined	Performance Enhancements	22
65	Perform security testing for Feature 4	Security Testing	22
66	Create user documentation for Feature 4	User Documentation	22
67	Optimize server-side for improved performance	Optimize Server-Side	23
68	Conduct load testing on the updated server-side	Load Testing	23
69	Enhance error handling for Feature 5	Error Handling	23
70	Integrate user feedback for Feature 6	User Feedback Integration	24
71	Make UI/UX tweaks as per feedback	UI/UX Tweaks	24
72	Conduct cross-browser testing for Feature 6	Cross-Browser Testing	24
\.


--
-- Data for Name: team; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.team (id, team_name, project_id) FROM stdin;
1	Team Bulldogs	1
2	Team Lions	1
3	Team Eagles	1
\.


--
-- Data for Name: user_story; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_story (id, block_reason, blocked, description, state, story_points, user_story_name, assigned_user_id, feature_id, iteration_it_number, team_id) FROM stdin;
1	\N	f	Enhancing the filtering options for search results to provide a better user experience.	NEW	1	Improved search result filtering	1	1	1	1
2	\N	f	Implementing advanced sorting algorithms for search results to boost performance.	NEW	2	Advanced search result sorting	1	1	1	1
3	\N	f	Redesigning the homepage with a user-centric approach to improve engagement.	NEW	3	User-friendly homepage redesign	2	2	1	1
4	\N	f	Giving the profile page a fresh new look to increase user satisfaction.	NEW	5	Profile page makeover	2	2	2	1
5	\N	f	Implementing server-side caching techniques to speed up data retrieval.	NEW	8	Optimized server-side caching	3	3	1	1
6	\N	f	Improving database indexing for faster query processing and scalability.	NEW	13	Enhanced database indexing	3	3	1	1
7	\N	f	Simplifying the user registration process for a smoother onboarding experience.	NEW	1	Streamlined registration process	4	1	1	1
8	\N	f	Implementing recommendation algorithms for personalized user content suggestions.	NEW	2	Personalized user recommendations	4	1	2	1
9	\N	f	Enhancing data synchronization processes to ensure data consistency across devices.	NEW	3	Efficient data synchronization	5	2	1	2
10	\N	f	Adapting the interface for mobile devices to cater to a broader user base.	NEW	5	Mobile-friendly interface	5	2	2	2
11	\N	f	Enhancing the notification system to keep users informed and engaged.	NEW	8	Improved notification system	6	3	2	2
12	\N	f	Implementing advanced security measures to protect user data and privacy.	NEW	13	Security enhancements	6	3	1	2
13	\N	f	Integrating a user feedback system to gather valuable insights and suggestions.	NEW	1	User feedback integration	7	1	1	2
14	\N	f	Adding options for users to easily share content with their social networks.	NEW	2	Content sharing options	7	1	1	2
15	\N	f	Allowing users to log in using their social media accounts for added convenience.	NEW	3	Social media login	8	2	1	2
16	\N	f	Introducing gamification elements to increase user engagement and interaction.	NEW	5	Gamification elements	8	2	1	2
17	\N	f	Improving image uploading functionality for a smoother user experience.	NEW	8	Enhanced image uploading	9	3	2	3
18	\N	f	Implementing a real-time chat feature for users to connect and communicate.	NEW	13	Integrated user chat	9	3	1	3
19	\N	f	Developing a recommendation engine to suggest content based on user preferences.	NEW	1	Content recommendation engine	10	1	2	3
20	\N	f	Enhancing the platforms accessibility features to cater to all users.	NEW	2	Accessibility improvements	10	1	2	3
21	\N	f	Adding support for multiple languages and localized content.	NEW	3	Localized content support	10	2	1	3
22	\N	f	Creating interactive tutorials to help users make the most of the platform.	NEW	5	Interactive user tutorials	11	2	1	3
23	\N	f	Improving video streaming quality and performance for better user experience.	NEW	8	Enhanced video streaming	11	3	2	3
24	\N	f	Implementing analytics to track user engagement and content performance.	NEW	13	Advanced content analytics	11	3	1	3
25	\N	f	Allowing users to customize their profiles with personal information and themes.	NEW	1	User profile customization	11	1	1	3
26	\N	f	Do it asap	NEW	8	Fix bugs in API	\N	1	\N	1
27	\N	f	Do it asap	NEW	5	Fix bugs in frontend	\N	1	\N	1
28	\N	f	Do it asap	NEW	3	Upgrade libraries in frontend	\N	1	\N	1
29	\N	f	Do it asap	NEW	8	Upgrade libraries in backend	\N	1	\N	1
30	\N	f	Do it asap	NEW	8	Create documentation	\N	1	\N	1
31	\N	f	Do it asap	NEW	8	Create more bugs in API	\N	1	\N	2
32	\N	f	Do it asap	NEW	5	Create more bugs in frontend	\N	1	\N	2
33	\N	f	Do it asap	NEW	3	Delete old libraries in frontend	\N	1	\N	2
34	\N	f	Do it asap	NEW	8	Delete old libraries in backend	\N	1	\N	2
35	\N	f	Do it asap	NEW	8	Create much more documentation	\N	1	\N	2
36	\N	f	Do it asap	NEW	13	Write documentation for new workers	\N	1	\N	2
37	\N	f	Do it asap	NEW	8	Meet with customer for new details	\N	1	\N	2
38	\N	f	Do it asap	NEW	8	Cleanup code in AP	\N	1	\N	3
39	\N	f	Do it asap	NEW	5	Cleanup code in frontend	\N	1	\N	3
40	\N	f	Do it asap	NEW	3	Create new build gradle	\N	1	\N	3
41	\N	f	Do it asap	NEW	8	Fix problems in npm	\N	1	\N	3
42	\N	f	Do it asap	NEW	8	Document everything on developer wikipedia	\N	1	\N	3
43	\N	f	Do it asap	NEW	8	Think how to make more money on our project	\N	1	\N	\N
44	\N	f	Do it asap	NEW	5	How to find more clients	\N	1	\N	\N
45	\N	f	Do it asap	NEW	3	How to make our program more efficient	\N	1	\N	\N
\.


--
-- Data for Name: user_story_tags; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_story_tags (user_story_id, tags_id) FROM stdin;
1	1
1	3
2	2
3	1
4	1
4	3
5	1
6	1
7	1
7	3
8	1
9	1
10	1
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, account_activated, email, first_name, last_name, password, roles, username, project_id, team_id) FROM stdin;
1	t	\N	Jan	Kowalski	$2a$10$R2rNkxslm96X9a/hOUx05..X8EmTJ89tjlyylGvBlAbN0qAQNKiAS	{ADMINISTRATOR,PROJECT_SUPERVISOR,EDITING}	admin	1	1
2	t	\N	Adam	Nowak	$2a$10$.bdr4pJ94f3sAUTBzy2G9eADtbMPyohOzo397uXUhKJ6EQZ93fqAK	{EDITING}	user	1	1
3	t	\N	Krzysztof	Nowak	$2a$10$rCCbi2RNkNPycu8WIJyKCeidli6kvZEnU7XQHsUtiyz/Li7f0yp2e	\N	user1	1	1
4	t	\N	John	Smith	$2a$10$QqOTJhnIGGSg/mqoxkScxutbWudW4hFTWLK0tEhHAKWGET5wHplaS	{EDITING}	user2	1	1
5	t	\N	Michael	Anderson	$2a$10$AULhsFKeUaSmGH2RVzgmWOp.LVqN.GCK4Tr8QeqIGxRHwP7/m0AfC	{EDITING}	user3	1	2
6	t	\N	Christopher	Martinez	$2a$10$Yx70ZocViQHQ/3GeBkyoae3zDCdGnig7uxRR6vaXuouc7LpLzWw.6	{EDITING}	user4	1	2
7	t	\N	Sophia	Brown	$2a$10$QrjBFWm9SjwpBFFCbb.n.ujAIij8392v7Gtazf1R6YnRyI2N.Rmwy	{EDITING}	user5	1	2
8	t	\N	Jennifer	Miller	$2a$10$.yg0sg/fPvEHTV3jqYF4VuhWY5H34IIPy.y/iWZWsWazKkemibGkq	{EDITING}	user6	1	2
9	t	\N	Benjamin	Hall	$2a$10$Znw2pC9ZSYy1NYiXUaYnleyLe8y6q2wvovG/J4SsZRSpiCNqtKsre	{EDITING}	user7	1	3
10	t	\N	Emma	Clark	$2a$10$6mw/SkwJwUBR/fAYL09gq.VwLqy9TotXOoO7DqCuwy0uGIao01vbi	{EDITING}	user8	1	3
11	t	\N	Joshua	Walker	$2a$10$/ZONodOoDdOCxllx7diEnOspRuffJpjUCyIpGBi1KuosIHViCzsjO	{EDITING}	user9	1	3
12	t	\N	Jacob	Turner	$2a$10$gSJ2MxbT93qOk4KxpSTEPe9gZ3swguhoE5ltA608KWoLiw3OD8oOW	{EDITING}	user10	1	3
13	f	\N	Jan	Kowalski	$2a$10$gSJ2MxbT93qOk4KxpSTEPe9gZ3swguhoE5ltA608KWoLiw3OD8oOW	\N	user11	\N	\N
14	f	\N	Mariusz	Kowalczyk	$2a$10$gSJ2MxbT93qOk4KxpSTEPe9gZ3swguhoE5ltA608KWoLiw3OD8oOW	\N	user12	\N	\N
15	f	\N	John	Johnson	$2a$10$gSJ2MxbT93qOk4KxpSTEPe9gZ3swguhoE5ltA608KWoLiw3OD8oOW	\N	user13	\N	\N
\.


--
-- Name: feature_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.feature_id_seq', 3, true);


--
-- Name: iteration_it_number_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.iteration_it_number_seq', 11, true);


--
-- Name: project_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.project_id_seq', 3, true);


--
-- Name: tag_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tag_id_seq', 3, true);


--
-- Name: task_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.task_id_seq', 72, true);


--
-- Name: team_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.team_id_seq', 3, true);


--
-- Name: user_story_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_story_id_seq', 45, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 15, true);


--
-- Name: feature feature_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feature
    ADD CONSTRAINT feature_pkey PRIMARY KEY (id);


--
-- Name: iteration iteration_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.iteration
    ADD CONSTRAINT iteration_pkey PRIMARY KEY (it_number);


--
-- Name: project project_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project
    ADD CONSTRAINT project_pkey PRIMARY KEY (id);


--
-- Name: tag tag_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (id);


--
-- Name: tag_user_stories tag_user_stories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tag_user_stories
    ADD CONSTRAINT tag_user_stories_pkey PRIMARY KEY (tag_id, user_stories_id);


--
-- Name: task task_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT task_pkey PRIMARY KEY (id);


--
-- Name: team team_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.team
    ADD CONSTRAINT team_pkey PRIMARY KEY (id);


--
-- Name: users uk_r43af9ap4edm43mmtq01oddj6; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_r43af9ap4edm43mmtq01oddj6 UNIQUE (username);


--
-- Name: user_story user_story_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_story
    ADD CONSTRAINT user_story_pkey PRIMARY KEY (id);


--
-- Name: user_story_tags user_story_tags_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_story_tags
    ADD CONSTRAINT user_story_tags_pkey PRIMARY KEY (user_story_id, tags_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: feature fk5klk2ih7ytb7n4t8myt6msi74; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feature
    ADD CONSTRAINT fk5klk2ih7ytb7n4t8myt6msi74 FOREIGN KEY (project_id) REFERENCES public.project(id);


--
-- Name: user_story fk5yfk9dali0jnv240vh415it8g; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_story
    ADD CONSTRAINT fk5yfk9dali0jnv240vh415it8g FOREIGN KEY (assigned_user_id) REFERENCES public.users(id);


--
-- Name: user_story_tags fkbnanujm2t430xg4iudhurido8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_story_tags
    ADD CONSTRAINT fkbnanujm2t430xg4iudhurido8 FOREIGN KEY (user_story_id) REFERENCES public.user_story(id);


--
-- Name: users fkdbmgdlpkonl25aiqghmhywhlg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fkdbmgdlpkonl25aiqghmhywhlg FOREIGN KEY (project_id) REFERENCES public.project(id);


--
-- Name: user_story_tags fkfi9fpgrjqm5wj4no1vphcijqg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_story_tags
    ADD CONSTRAINT fkfi9fpgrjqm5wj4no1vphcijqg FOREIGN KEY (tags_id) REFERENCES public.tag(id);


--
-- Name: tag_user_stories fkfnvihj4kbu2qbpioxg7tf0jqy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tag_user_stories
    ADD CONSTRAINT fkfnvihj4kbu2qbpioxg7tf0jqy FOREIGN KEY (tag_id) REFERENCES public.tag(id);


--
-- Name: users fkhn2tnbh9fqjqeuv8ehw5ple7a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fkhn2tnbh9fqjqeuv8ehw5ple7a FOREIGN KEY (team_id) REFERENCES public.team(id);


--
-- Name: tag_user_stories fkjhbhdvk9ph0ewydw24ul1xa1j; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tag_user_stories
    ADD CONSTRAINT fkjhbhdvk9ph0ewydw24ul1xa1j FOREIGN KEY (user_stories_id) REFERENCES public.user_story(id);


--
-- Name: user_story fkk7k7cg7hw6jq9rg4mm0me35vb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_story
    ADD CONSTRAINT fkk7k7cg7hw6jq9rg4mm0me35vb FOREIGN KEY (feature_id) REFERENCES public.feature(id);


--
-- Name: task fklso25fkuj3mijovbxi6md7c39; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT fklso25fkuj3mijovbxi6md7c39 FOREIGN KEY (user_story_id) REFERENCES public.user_story(id);


--
-- Name: team fkp6ovpc4soflfcjbafch33w2ky; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.team
    ADD CONSTRAINT fkp6ovpc4soflfcjbafch33w2ky FOREIGN KEY (project_id) REFERENCES public.project(id);


--
-- Name: user_story fksgjc9adccy8kod1dq1vf18dso; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_story
    ADD CONSTRAINT fksgjc9adccy8kod1dq1vf18dso FOREIGN KEY (team_id) REFERENCES public.team(id);


--
-- Name: user_story fktndxvwmbdticjwpcwapixdqot; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_story
    ADD CONSTRAINT fktndxvwmbdticjwpcwapixdqot FOREIGN KEY (iteration_it_number) REFERENCES public.iteration(it_number);


--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database cluster dump complete
--

