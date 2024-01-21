--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

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
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: actor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.actor (
    id bigint NOT NULL,
    birthday timestamp without time zone,
    gender integer,
    known_as character varying(255),
    name character varying(255),
    picture character varying(255),
    surname character varying(255),
    married_to_id bigint
);


ALTER TABLE public.actor OWNER TO postgres;

--
-- Name: actors_productions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.actors_productions (
    production_id bigint NOT NULL,
    actor_id bigint NOT NULL
);


ALTER TABLE public.actors_productions OWNER TO postgres;

--
-- Name: episode; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.episode (
    id bigint NOT NULL,
    length integer,
    number integer,
    title character varying(255),
    next_episode_id bigint,
    season_id bigint
);


ALTER TABLE public.episode OWNER TO postgres;

--
-- Name: episode_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.episode_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.episode_id_seq OWNER TO postgres;

--
-- Name: episode_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.episode_id_seq OWNED BY public.episode.id;


--
-- Name: flyway_schema_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE public.flyway_schema_history OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: hibernate_sequences; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.hibernate_sequences (
    sequence_name character varying(255) NOT NULL,
    next_val bigint
);


ALTER TABLE public.hibernate_sequences OWNER TO postgres;

--
-- Name: movie; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movie (
    id bigint NOT NULL,
    genre character varying(255),
    language character varying(255),
    title character varying(255),
    length integer,
    year integer,
    director character varying(255),
    type character varying(255),
    description text,
    picture character varying(255)
);


ALTER TABLE public.movie OWNER TO postgres;

--
-- Name: production_actors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.production_actors (
    productions_id bigint NOT NULL,
    actors_id bigint NOT NULL
);


ALTER TABLE public.production_actors OWNER TO postgres;

--
-- Name: season; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.season (
    id bigint NOT NULL,
    number integer,
    year integer,
    series_id bigint
);


ALTER TABLE public.season OWNER TO postgres;

--
-- Name: season_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.season_id_seq OWNER TO postgres;

--
-- Name: season_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.season_id_seq OWNED BY public.season.id;


--
-- Name: series; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.series (
    id bigint NOT NULL,
    genre character varying(255),
    language character varying(255),
    title character varying(255),
    finished boolean,
    type character varying(255),
    description text,
    picture character varying(255)
);


ALTER TABLE public.series OWNER TO postgres;

--
-- Name: episode id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.episode ALTER COLUMN id SET DEFAULT nextval('public.episode_id_seq'::regclass);


--
-- Name: season id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.season ALTER COLUMN id SET DEFAULT nextval('public.season_id_seq'::regclass);


--
-- Data for Name: actor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.actor (id, birthday, gender, known_as, name, picture, surname, married_to_id) FROM stdin;
72	1954-02-18 00:00:00	0	John Travolta	John	https://brooklyneagle.com/wp-content/uploads/2020/02/John-Travolta-614x1024.jpg	Travolta	\N
68	1974-11-11 00:00:00	1	Leonardo Dicaprio	Leonardo Wilhelm	https://upload.wikimedia.org/wikipedia/commons/9/96/Leonardo_Dicaprio_Cannes_2019_2.jpg	Dicaprio	\N
81	1975-06-04 00:00:00	0	Angelina Jolie	Angelina	https://upload.wikimedia.org/wikipedia/commons/a/ad/Angelina_Jolie_2_June_2014_%28cropped%29.jpg	Jolie Voight	82
82	1963-12-18 00:00:00	1	Brad Pitt	William Bradley	https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Brad_Pitt_2019_by_Glenn_Francis.jpg/800px-Brad_Pitt_2019_by_Glenn_Francis.jpg	Pitt	81
84	1987-08-25 00:00:00	0	Blake Lively	Blake Ellender	https://www.nientespoiler.it/wp-content/uploads/2018/07/BlakeLively2.jpg	Brown	83
83	1976-10-23 00:00:00	1	Ryan Reynolds	Ryan Rodney	https://upload.wikimedia.org/wikipedia/commons/thumb/1/14/Deadpool_2_Japan_Premiere_Red_Carpet_Ryan_Reynolds_%28cropped%29.jpg/800px-Deadpool_2_Japan_Premiere_Red_Carpet_Ryan_Reynolds_%28cropped%29.jpg	Reynolds	84
85	1976-05-25 00:00:00	1	Cillian Murphy	Cillian	https://cinema.icrewplay.com/wp-content/uploads/2020/10/cillian-murphy-bbc-sounds.jpg	Murphy	\N
102	1969-06-01 01:00:00	1	Norman Reedus	Norman Mark	https://upload.wikimedia.org/wikipedia/commons/thumb/e/e6/Norman_Reedus_by_Gage_Skidmore_4.jpg/1200px-Norman_Reedus_by_Gage_Skidmore_4.jpg	Reedus	\N
103	1956-03-07 00:00:00	1	Bryan Cranston	Bryan Lee	https://upload.wikimedia.org/wikipedia/commons/thumb/8/8f/Bryan_Cranston_at_the_2018_Berlin_Film_Festival_%282%29.jpg/1024px-Bryan_Cranston_at_the_2018_Berlin_Film_Festival_%282%29.jpg	Cranston	\N
104	1952-10-22 00:00:00	1	Jeff Goldblum	Jeffrey Lynn	https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/Jeff_Goldblum_by_Gage_Skidmore.jpg/1024px-Jeff_Goldblum_by_Gage_Skidmore.jpg	Goldblum	\N
\.


--
-- Data for Name: actors_productions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.actors_productions (production_id, actor_id) FROM stdin;
33	68
86	85
2	102
3	103
1	104
11	72
105	83
\.


--
-- Data for Name: episode; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.episode (id, length, number, title, next_episode_id, season_id) FROM stdin;
34	58	1	Pilot	\N	20
35	48	2	Cat's in the Bag...	\N	20
36	48	3	...and the Bag's in the River	\N	20
37	48	4	Cancer Man	\N	20
38	48	5	Gray Matter	\N	20
39	47	6	Crazy Handful of Nothin'	\N	20
40	47	7	A No-Rough-Stuff-Type Deal	\N	20
42	47	1	Seven Thirty-Seven	\N	41
43	47	2	Grilled	\N	41
44	46	3	Bit by a Dead Bee	\N	41
45	47	4	Down	\N	41
46	47	5	Breakage	\N	41
49	47	8	Better Call Saul	\N	41
47	47	6	Peekaboo	\N	41
48	47	7	Negro y Azul	\N	41
26	67	1	Days Gone Bye	\N	25
27	45	2	Guts	\N	25
51	44	3	Tell It to the Frogs	\N	25
52	45	4	Vatos	\N	25
53	45	5	Wildfire	\N	25
54	45	6	TS-19	\N	25
89	57	1	Episode 1	\N	88
90	58	2	Episode 2	\N	88
91	55	3	Episode 3	\N	88
92	58	4	Episode 4	\N	88
93	56	5	Episode 5	\N	88
94	54	6	Episode 6	\N	88
96	59	1	Episode 1	\N	95
97	57	2	Episode 2	\N	95
98	58	3	Episode 3	\N	95
99	57	4	Episode 4	\N	95
100	58	5	Episode 5	\N	95
101	59	6	Episode 6	\N	95
\.


--
-- Data for Name: flyway_schema_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	1	ActorTable	SQL	V1__ActorTable.sql	-509849544	postgres	2020-12-11 11:51:15.896588	5	t
\.


--
-- Data for Name: hibernate_sequences; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.hibernate_sequences (sequence_name, next_val) FROM stdin;
default	3
\.


--
-- Data for Name: movie; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.movie (id, genre, language, title, length, year, director, type, description, picture) FROM stdin;
33	science fiction action	english	Inception	148	2010	Christopher Nolan	\N	Inception is a 2010 science fiction action film written and directed by Christopher Nolan, who also produced the film with his wife, Emma Thomas.	https://2.bp.blogspot.com/_Iau3R3yMIr4/TMBDGzcvwSI/AAAAAAAACbQ/77grl8TYdK4/s1600/inception1.jpg
11	neo-noir black comedy crime	english	Pulp Fiction	154	1994	Quentin Tarantino	\N	Pulp Fiction is a 1994 American neo-noir black comedy crime film written and directed by Quentin Tarantino, who conceived it with Roger Avary.	http://barkerhost.com/wp-content/uploads/sites/4/2015/11/dM2w364MScsjFf8pfMbaWUcWrR-0.jpg
1	science fiction adventure	english	Jurassic Park	127	1993	Steven Spielberg	\N	Jurassic Park is a 1993 American science fiction adventure film directed by Steven Spielberg and produced by Kathleen Kennedy and Gerald R. Molen.	https://images-na.ssl-images-amazon.com/images/I/816wlBnZx2L._SL1500_.jpg
105	superhero	english	Deadpool	108	2016	Tim Miller	\N	Deadpool is a 2016 American superhero film based on the Marvel Comics character of the same name. Distributed by 20th Century Fox, it is the eighth film in the X-Men film series. Directed by Tim Miller from a screenplay by Rhett Reese and Paul Wernick, it stars Ryan Reynolds in the title role alongside Morena Baccarin, Ed Skrein, T. J. Miller, Gina Carano, and Brianna Hildebrand. In the film, Wade Wilson hunts the man who gave him mutant abilities and a scarred physical appearance, becoming the beloved antihero Deadpool. 	https://upload.wikimedia.org/wikipedia/en/2/23/Deadpool_%282016_poster%29.png
\.


--
-- Data for Name: production_actors; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.production_actors (productions_id, actors_id) FROM stdin;
\.


--
-- Data for Name: season; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.season (id, number, year, series_id) FROM stdin;
1	1	2020	\N
2	2	2021	\N
3	3	2021	\N
15	1	2009	\N
24	1	0	\N
25	1	2011	2
20	1	2008	3
41	2	2009	3
88	1	2013	86
95	2	2014	86
\.


--
-- Data for Name: series; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.series (id, genre, language, title, finished, type, description, picture) FROM stdin;
2	post-apocalyptic horror	english	The Walking Dead	f	\N	The Walking Dead is an American post-apocalyptic horror television series based on the comic book series of the same name by Robert Kirkman, Tony Moore, and Charlie Adlard.	https://www.filmamo.it/assets/public/img/movies/1402-8.jpg
3	neo-Western crime drama	english	Breaking Bad	t	\N	Breaking Bad is an American neo-Western crime drama television series created and produced by Vince Gilligan. The show aired on AMC from January 20, 2008, to September 29, 2013, consisting of five seasons for a total of 62 episodes. It was set and filmed in Albuquerque, New Mexico, and tells the story of Walter White (Bryan Cranston), an underpaid and dispirited high school chemistry teacher who is struggling with a recent diagnosis of stage-three lung cancer.	https://i.pinimg.com/originals/94/03/f7/9403f70db5663588eacdf25ffdac832b.jpg
86	period crime drama	english	Peaky Blinders	f	\N	Peaky Blinders is a British period crime drama television series created by Steven Knight. Set in Birmingham, England, the series follows the exploits of the Shelby crime family in the direct aftermath of the First World War. The fictional family is loosely based on a real urban youth gang of the same name, who were active in the city from the 1890s to the early 20th century. 	https://assets.nflxext.com/us/boxshots/hd1080/80002479.jpg
\.


--
-- Name: episode_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.episode_id_seq', 1, true);


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 105, true);


--
-- Name: season_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.season_id_seq', 4, true);


--
-- Name: actor actor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actor
    ADD CONSTRAINT actor_pkey PRIMARY KEY (id);


--
-- Name: episode episode_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.episode
    ADD CONSTRAINT episode_pkey PRIMARY KEY (id);


--
-- Name: flyway_schema_history flyway_schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);


--
-- Name: hibernate_sequences hibernate_sequences_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hibernate_sequences
    ADD CONSTRAINT hibernate_sequences_pkey PRIMARY KEY (sequence_name);


--
-- Name: movie movie_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movie
    ADD CONSTRAINT movie_pkey PRIMARY KEY (id);


--
-- Name: season season_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (id);


--
-- Name: series series_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.series
    ADD CONSTRAINT series_pkey PRIMARY KEY (id);


--
-- Name: flyway_schema_history_s_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);


--
-- Name: actor fkf9dh19ywj9dugb6mvli7utgag; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actor
    ADD CONSTRAINT fkf9dh19ywj9dugb6mvli7utgag FOREIGN KEY (married_to_id) REFERENCES public.actor(id);


--
-- Name: season fkinfoupkdkuab2jvqx8i8dx05v; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.season
    ADD CONSTRAINT fkinfoupkdkuab2jvqx8i8dx05v FOREIGN KEY (series_id) REFERENCES public.series(id);


--
-- Name: actors_productions fkj7uq6b2fjak9pvmi8x2os2vjf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actors_productions
    ADD CONSTRAINT fkj7uq6b2fjak9pvmi8x2os2vjf FOREIGN KEY (actor_id) REFERENCES public.actor(id);


--
-- Name: episode fkr5ifuxa82mfaxrhgahps7iu2m; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.episode
    ADD CONSTRAINT fkr5ifuxa82mfaxrhgahps7iu2m FOREIGN KEY (season_id) REFERENCES public.season(id);


--
-- Name: episode fkvn7g3pot9dx0ujqxq2dbb563; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.episode
    ADD CONSTRAINT fkvn7g3pot9dx0ujqxq2dbb563 FOREIGN KEY (next_episode_id) REFERENCES public.episode(id);


--
-- PostgreSQL database dump complete
--

