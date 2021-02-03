--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3 (Debian 12.3-1.pgdg100+1)
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

CREATE USER "goArena" superuser createdb createrole replication bypassrls;
ALTER USER "goArena" PASSWORD 'Qa1q2w3e!';

--
-- Name: goArena; Type: DATABASE; Schema: -; Owner: emrah
--

CREATE DATABASE "goArena" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.utf8';


ALTER DATABASE "goArena" OWNER TO "goArena";

\connect "goArena"

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
-- Name: dashboard; Type: SCHEMA; Schema: -; Owner: goArena
--

CREATE SCHEMA dashboard;


ALTER SCHEMA dashboard OWNER TO "goArena";

--
-- Name: feeds; Type: SCHEMA; Schema: -; Owner: goArena
--

CREATE SCHEMA feeds;


ALTER SCHEMA feeds OWNER TO "goArena";

--
-- Name: users; Type: SCHEMA; Schema: -; Owner: goArena
--

CREATE SCHEMA users;


ALTER SCHEMA users OWNER TO "goArena";

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: expectations; Type: TABLE; Schema: dashboard; Owner: goArena
--

CREATE TABLE dashboard.expectations (
    id bigint NOT NULL,
    product character varying(255),
    product_group character varying(255),
    quantity bigint,
    shop_id bigint,
    user_id bigint
);


ALTER TABLE dashboard.expectations OWNER TO "goArena";

--
-- Name: expectations_id_seq; Type: SEQUENCE; Schema: dashboard; Owner: goArena
--

CREATE SEQUENCE dashboard.expectations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE dashboard.expectations_id_seq OWNER TO "goArena";

--
-- Name: expectations_id_seq; Type: SEQUENCE OWNED BY; Schema: dashboard; Owner: goArena
--

ALTER SEQUENCE dashboard.expectations_id_seq OWNED BY dashboard.expectations.id;


--
-- Name: sales; Type: TABLE; Schema: dashboard; Owner: goArena
--

CREATE TABLE dashboard.sales (
    id bigint NOT NULL,
    amount numeric,
    date_time timestamp without time zone,
    product character varying(255),
    product_group character varying(255),
    quantity bigint,
    type character varying(255),
    shop_id bigint,
    user_id bigint
);


ALTER TABLE dashboard.sales OWNER TO "goArena";

--
-- Name: sales_id_seq; Type: SEQUENCE; Schema: dashboard; Owner: goArena
--

CREATE SEQUENCE dashboard.sales_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE dashboard.sales_id_seq OWNER TO "goArena";

--
-- Name: sales_id_seq; Type: SEQUENCE OWNED BY; Schema: dashboard; Owner: goArena
--

ALTER SEQUENCE dashboard.sales_id_seq OWNED BY dashboard.sales.id;


--
-- Name: comments; Type: TABLE; Schema: feeds; Owner: goArena
--

CREATE TABLE feeds.comments (
    id bigint NOT NULL,
    comment character varying(255),
    feed_id bigint,
    post_date timestamp without time zone,
    user_id bigint
);


ALTER TABLE feeds.comments OWNER TO "goArena";

--
-- Name: comments_id_seq; Type: SEQUENCE; Schema: feeds; Owner: goArena
--

CREATE SEQUENCE feeds.comments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE feeds.comments_id_seq OWNER TO "goArena";

--
-- Name: comments_id_seq; Type: SEQUENCE OWNED BY; Schema: feeds; Owner: goArena
--

ALTER SEQUENCE feeds.comments_id_seq OWNED BY feeds.comments.id;


--
-- Name: feeds; Type: TABLE; Schema: feeds; Owner: goArena
--

CREATE TABLE feeds.feeds (
    id bigint NOT NULL,
    likes integer,
    post_date timestamp without time zone,
    post_type character varying(255),
    status character varying(255),
    title character varying(255),
    user_id bigint,
    tags character varying(255)
);


ALTER TABLE feeds.feeds OWNER TO "goArena";

--
-- Name: feeds_id_seq; Type: SEQUENCE; Schema: feeds; Owner: goArena
--

CREATE SEQUENCE feeds.feeds_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE feeds.feeds_id_seq OWNER TO "goArena";

--
-- Name: feeds_id_seq; Type: SEQUENCE OWNED BY; Schema: feeds; Owner: goArena
--

ALTER SEQUENCE feeds.feeds_id_seq OWNED BY feeds.feeds.id;


--
-- Name: likes; Type: TABLE; Schema: feeds; Owner: goArena
--

CREATE TABLE feeds.likes (
    id bigint NOT NULL,
    feed_id bigint,
    post_date timestamp without time zone,
    comment character varying(255),
    user_id bigint
);


ALTER TABLE feeds.likes OWNER TO "goArena";

--
-- Name: likes_id_seq; Type: SEQUENCE; Schema: feeds; Owner: goArena
--

CREATE SEQUENCE feeds.likes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE feeds.likes_id_seq OWNER TO "goArena";

--
-- Name: likes_id_seq; Type: SEQUENCE OWNED BY; Schema: feeds; Owner: goArena
--

ALTER SEQUENCE feeds.likes_id_seq OWNED BY feeds.likes.id;


--
-- Name: media; Type: TABLE; Schema: feeds; Owner: goArena
--

CREATE TABLE feeds.media (
    id bigint NOT NULL,
    feed_id bigint,
    mime_type character varying(255),
    uri character varying(255),
    user_id bigint
);


ALTER TABLE feeds.media OWNER TO "goArena";

--
-- Name: media_id_seq; Type: SEQUENCE; Schema: feeds; Owner: goArena
--

CREATE SEQUENCE feeds.media_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE feeds.media_id_seq OWNER TO "goArena";

--
-- Name: media_id_seq; Type: SEQUENCE OWNED BY; Schema: feeds; Owner: goArena
--

ALTER SEQUENCE feeds.media_id_seq OWNED BY feeds.media.id;


--
-- Name: tags; Type: TABLE; Schema: feeds; Owner: goArena
--

CREATE TABLE feeds.tags (
    id bigint NOT NULL,
    sort bigint NOT NULL,
    tag character varying(255),
    popularity bigint NOT NULL
);


ALTER TABLE feeds.tags OWNER TO "goArena";

--
-- Name: tags_id_seq; Type: SEQUENCE; Schema: feeds; Owner: goArena
--

CREATE SEQUENCE feeds.tags_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE feeds.tags_id_seq OWNER TO "goArena";

--
-- Name: tags_id_seq; Type: SEQUENCE OWNED BY; Schema: feeds; Owner: goArena
--

ALTER SEQUENCE feeds.tags_id_seq OWNED BY feeds.tags.id;


--
-- Name: shops; Type: TABLE; Schema: users; Owner: goArena
--

CREATE TABLE users.shops (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE users.shops OWNER TO "goArena";

--
-- Name: shops_id_seq; Type: SEQUENCE; Schema: users; Owner: goArena
--

CREATE SEQUENCE users.shops_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users.shops_id_seq OWNER TO "goArena";

--
-- Name: shops_id_seq; Type: SEQUENCE OWNED BY; Schema: users; Owner: goArena
--

ALTER SEQUENCE users.shops_id_seq OWNED BY users.shops.id;


--
-- Name: users; Type: TABLE; Schema: users; Owner: goArena
--

CREATE TABLE users.users (
    id bigint NOT NULL,
    avatar character varying(255),
    email character varying(255),
    employee_type character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    password character varying(255),
    username character varying(255),
    shop_id bigint
);


ALTER TABLE users.users OWNER TO "goArena";

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: users; Owner: goArena
--

CREATE SEQUENCE users.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users.users_id_seq OWNER TO "goArena";

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: users; Owner: goArena
--

ALTER SEQUENCE users.users_id_seq OWNED BY users.users.id;


--
-- Name: expectations id; Type: DEFAULT; Schema: dashboard; Owner: goArena
--

ALTER TABLE ONLY dashboard.expectations ALTER COLUMN id SET DEFAULT nextval('dashboard.expectations_id_seq'::regclass);


--
-- Name: sales id; Type: DEFAULT; Schema: dashboard; Owner: goArena
--

ALTER TABLE ONLY dashboard.sales ALTER COLUMN id SET DEFAULT nextval('dashboard.sales_id_seq'::regclass);


--
-- Name: comments id; Type: DEFAULT; Schema: feeds; Owner: goArena
--

ALTER TABLE ONLY feeds.comments ALTER COLUMN id SET DEFAULT nextval('feeds.comments_id_seq'::regclass);


--
-- Name: feeds id; Type: DEFAULT; Schema: feeds; Owner: goArena
--

ALTER TABLE ONLY feeds.feeds ALTER COLUMN id SET DEFAULT nextval('feeds.feeds_id_seq'::regclass);


--
-- Name: likes id; Type: DEFAULT; Schema: feeds; Owner: goArena
--

ALTER TABLE ONLY feeds.likes ALTER COLUMN id SET DEFAULT nextval('feeds.likes_id_seq'::regclass);


--
-- Name: media id; Type: DEFAULT; Schema: feeds; Owner: goArena
--

ALTER TABLE ONLY feeds.media ALTER COLUMN id SET DEFAULT nextval('feeds.media_id_seq'::regclass);


--
-- Name: tags id; Type: DEFAULT; Schema: feeds; Owner: goArena
--

ALTER TABLE ONLY feeds.tags ALTER COLUMN id SET DEFAULT nextval('feeds.tags_id_seq'::regclass);


--
-- Name: shops id; Type: DEFAULT; Schema: users; Owner: goArena
--

ALTER TABLE ONLY users.shops ALTER COLUMN id SET DEFAULT nextval('users.shops_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: users; Owner: goArena
--

ALTER TABLE ONLY users.users ALTER COLUMN id SET DEFAULT nextval('users.users_id_seq'::regclass);


--
-- Data for Name: expectations; Type: TABLE DATA; Schema: dashboard; Owner: goArena
--

INSERT INTO dashboard.expectations (id, product, product_group, quantity, shop_id, user_id) VALUES (2, 'Apple Watch SE', 'Aksesuar', 30, 3, 14);
INSERT INTO dashboard.expectations (id, product, product_group, quantity, shop_id, user_id) VALUES (4, 'Telefon', 'Cihaz', 20, 3, 14);
INSERT INTO dashboard.expectations (id, product, product_group, quantity, shop_id, user_id) VALUES (5, 'Tablet', 'Cihaz', 20, 3, 14);
INSERT INTO dashboard.expectations (id, product, product_group, quantity, shop_id, user_id) VALUES (6, 'Faturalı Hat', 'Telko', 50, 3, 14);
INSERT INTO dashboard.expectations (id, product, product_group, quantity, shop_id, user_id) VALUES (7, 'Faturasız Hat', 'Telko', 50, 3, 14);
INSERT INTO dashboard.expectations (id, product, product_group, quantity, shop_id, user_id) VALUES (12, 'Kablosuz Kulaklık', 'Aksesuar', 30, 3, 15);
INSERT INTO dashboard.expectations (id, product, product_group, quantity, shop_id, user_id) VALUES (13, 'Apple Watch SE', 'Aksesuar', 30, 3, 15);
INSERT INTO dashboard.expectations (id, product, product_group, quantity, shop_id, user_id) VALUES (14, 'Telefon', 'Cihaz', 25, 3, 15);
INSERT INTO dashboard.expectations (id, product, product_group, quantity, shop_id, user_id) VALUES (15, 'Tablet', 'Cihaz', 25, 3, 15);
INSERT INTO dashboard.expectations (id, product, product_group, quantity, shop_id, user_id) VALUES (16, 'Faturalı Hat', 'Telko', 35, 3, 15);
INSERT INTO dashboard.expectations (id, product, product_group, quantity, shop_id, user_id) VALUES (17, 'Faturasız Hat', 'Telko', 35, 3, 15);
INSERT INTO dashboard.expectations (id, product, product_group, quantity, shop_id, user_id) VALUES (1, 'Kablosuz Kulaklık', 'Aksesuar', 20, 3, 16);
INSERT INTO dashboard.expectations (id, product, product_group, quantity, shop_id, user_id) VALUES (11, 'Faturasız Hat', 'Telko', 40, 3, 17);
INSERT INTO dashboard.expectations (id, product, product_group, quantity, shop_id, user_id) VALUES (3, 'Kablosuz Kulaklık', 'Aksesuar', 20, 3, 17);
INSERT INTO dashboard.expectations (id, product, product_group, quantity, shop_id, user_id) VALUES (8, 'Telefon', 'Cihaz', 15, 3, 17);
INSERT INTO dashboard.expectations (id, product, product_group, quantity, shop_id, user_id) VALUES (9, 'Tablet', 'Cihaz', 20, 3, 17);
INSERT INTO dashboard.expectations (id, product, product_group, quantity, shop_id, user_id) VALUES (10, 'Faturalı Hat', 'Telko', 40, 3, 17);
INSERT INTO dashboard.expectations (id, product, product_group, quantity, shop_id, user_id) VALUES (18, 'Faturasız Hat', 'Telko', 28, 3, 13);
INSERT INTO dashboard.expectations (id, product, product_group, quantity, shop_id, user_id) VALUES (19, 'Telefon', 'Cihaz', 30, 3, 13);
INSERT INTO dashboard.expectations (id, product, product_group, quantity, shop_id, user_id) VALUES (20, 'Tablet', 'Cihaz', 30, 3, 13);
INSERT INTO dashboard.expectations (id, product, product_group, quantity, shop_id, user_id) VALUES (21, 'Kablosuz Kulaklık', 'Aksesuar', 20, 3, 13);
INSERT INTO dashboard.expectations (id, product, product_group, quantity, shop_id, user_id) VALUES (22, 'Apple Watch SE', 'Aksesuar', 20, 3, 13);


--
-- Data for Name: sales; Type: TABLE DATA; Schema: dashboard; Owner: goArena
--

INSERT INTO dashboard.sales (id, amount, date_time, product, product_group, quantity, type, shop_id, user_id) VALUES (7, 20000, '2021-01-15 19:46:15.856', 'Tablet', 'Cihaz', 20, 'SALE', 3, 14);
INSERT INTO dashboard.sales (id, amount, date_time, product, product_group, quantity, type, shop_id, user_id) VALUES (6, 25000, '2021-01-08 19:41:14.456', 'Telefon', 'Cihaz', 25, 'SALE', 3, 14);
INSERT INTO dashboard.sales (id, amount, date_time, product, product_group, quantity, type, shop_id, user_id) VALUES (9, 0, '2021-01-10 19:59:00.915', 'Faturasız Hat', 'Telko', 40, 'SALE', 3, 14);
INSERT INTO dashboard.sales (id, amount, date_time, product, product_group, quantity, type, shop_id, user_id) VALUES (3, 30000, '2021-02-02 12:30:44.07', 'Apple Watch SE', 'Aksesuar', 30, 'SALE', 3, 14);
INSERT INTO dashboard.sales (id, amount, date_time, product, product_group, quantity, type, shop_id, user_id) VALUES (8, 0, '2021-01-10 19:59:00.915', 'Faturalı Hat', 'Telko', 0, 'SALE', 3, 14);
INSERT INTO dashboard.sales (id, amount, date_time, product, product_group, quantity, type, shop_id, user_id) VALUES (18, 3000, '2021-01-12 21:40:23.918', 'Faturalı Hat', 'Telko', 50, 'SALE', 3, 15);
INSERT INTO dashboard.sales (id, amount, date_time, product, product_group, quantity, type, shop_id, user_id) VALUES (15, 4000, '2021-01-12 21:40:23.918', 'Apple Watch SE', 'Aksesuar', 40, 'SALE', 3, 15);
INSERT INTO dashboard.sales (id, amount, date_time, product, product_group, quantity, type, shop_id, user_id) VALUES (14, 4000, '2021-01-12 21:40:23.918', 'Kablosuz Kulaklık', 'Aksesuar', 40, 'SALE', 3, 15);
INSERT INTO dashboard.sales (id, amount, date_time, product, product_group, quantity, type, shop_id, user_id) VALUES (17, 3000, '2021-01-12 21:40:23.918', 'Telefon', 'Tablet', 30, 'SALE', 3, 15);
INSERT INTO dashboard.sales (id, amount, date_time, product, product_group, quantity, type, shop_id, user_id) VALUES (16, 3000, '2021-01-12 21:40:23.918', 'Telefon', 'Cihaz', 30, 'SALE', 3, 15);
INSERT INTO dashboard.sales (id, amount, date_time, product, product_group, quantity, type, shop_id, user_id) VALUES (19, 3000, '2021-01-12 21:40:23.918', 'Faturasız Hat', 'Telko', 50, 'SALE', 3, 15);
INSERT INTO dashboard.sales (id, amount, date_time, product, product_group, quantity, type, shop_id, user_id) VALUES (1, 3000, '2021-02-02 12:30:44.07', 'Kablosuz Kulaklık', 'Aksesuar', 30, 'SALE', 3, 16);
INSERT INTO dashboard.sales (id, amount, date_time, product, product_group, quantity, type, shop_id, user_id) VALUES (13, 5000, '2021-01-15 21:02:26.699', 'Faturasız Hat', 'Telko', 50, 'SALE', 3, 17);
INSERT INTO dashboard.sales (id, amount, date_time, product, product_group, quantity, type, shop_id, user_id) VALUES (11, 25000, '2021-01-15 21:02:26.699', 'Tablet', 'Cihaz', 25, 'SALE', 3, 17);
INSERT INTO dashboard.sales (id, amount, date_time, product, product_group, quantity, type, shop_id, user_id) VALUES (10, 25000, '2021-01-15 21:02:26.699', 'Telefon', 'Cihaz', 25, 'SALE', 3, 17);
INSERT INTO dashboard.sales (id, amount, date_time, product, product_group, quantity, type, shop_id, user_id) VALUES (4, 1500, '2021-02-03 17:28:05.643', 'Kablosuz Kulaklık', 'Aksesuar', 15, 'SALE', 3, 17);
INSERT INTO dashboard.sales (id, amount, date_time, product, product_group, quantity, type, shop_id, user_id) VALUES (12, 5000, '2021-01-15 21:02:26.699', 'Faturalı Hat', 'Telko', 50, 'SALE', 3, 17);
INSERT INTO dashboard.sales (id, amount, date_time, product, product_group, quantity, type, shop_id, user_id) VALUES (20, 3000, '2021-02-03 11:16:33.213', 'Faturalı Hat', 'Telko', 30, 'SALE', 3, 17);
INSERT INTO dashboard.sales (id, amount, date_time, product, product_group, quantity, type, shop_id, user_id) VALUES (21, 3000, '2021-02-03 11:16:33.213', 'Faturalı Hat', 'Telko', 30, 'SALE', 3, 13);


--
-- Data for Name: comments; Type: TABLE DATA; Schema: feeds; Owner: goArena
--

INSERT INTO feeds.comments (id, comment, feed_id, post_date, user_id) VALUES (8, 'Bravo', 19, '2021-02-02 14:58:09.017', 9);
INSERT INTO feeds.comments (id, comment, feed_id, post_date, user_id) VALUES (7, 'Bravo', NULL, '2021-02-02 13:58:09.017', 9);
INSERT INTO feeds.comments (id, comment, feed_id, post_date, user_id) VALUES (9, 'Bravo', 19, '2021-02-02 14:38:09.017', 14);
INSERT INTO feeds.comments (id, comment, feed_id, post_date, user_id) VALUES (6, 'hç1m :)', 1, '2021-02-02 13:58:09.017', 14);
INSERT INTO feeds.comments (id, comment, feed_id, post_date, user_id) VALUES (10, ' :)', NULL, '2021-02-02 17:59:32.661', 14);
INSERT INTO feeds.comments (id, comment, feed_id, post_date, user_id) VALUES (3, 'Çok doğru.', 5, '2021-02-02 13:58:09.017', 16);
INSERT INTO feeds.comments (id, comment, feed_id, post_date, user_id) VALUES (2, 'Herkese çok selamlar', 4, '2021-02-02 13:58:09.017', 16);
INSERT INTO feeds.comments (id, comment, feed_id, post_date, user_id) VALUES (5, 'bipisenanlat, hç1m', 1, '2021-02-02 13:58:09.017', 16);
INSERT INTO feeds.comments (id, comment, feed_id, post_date, user_id) VALUES (4, 'Tebrikler, başarılar...', NULL, '2021-02-02 13:58:09.017', 16);
INSERT INTO feeds.comments (id, comment, feed_id, post_date, user_id) VALUES (1, 'Harika..!', NULL, '2021-02-02 13:58:09.017', 16);
INSERT INTO feeds.comments (id, comment, feed_id, post_date, user_id) VALUES (11, 'Çok doğru', 135, '2021-01-11 08:53:04.923', 14);


--
-- Data for Name: feeds; Type: TABLE DATA; Schema: feeds; Owner: goArena
--

INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (128, 0, '2021-02-03 10:21:47.621', 'TEXT', 'APPROVED', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 13, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (142, 0, '2021-02-03 13:30:43.316805', 'IMAGE', 'APPROVED', 'Herkese gunaydink', 8, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (131, 0, '2021-02-02 14:14:45.283', 'TEXT', 'APPROVED', 'Turkcell ile hayat güzel :)', 8, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (136, 5, '2021-02-02 08:49:33.475', 'TEXT', 'APPROVED', 'Hayattaki en büyük zafer hiçbir zaman düşmemekte değil, her düştüğünde ayağa kalkmakta yatar.', 16, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (139, 5, '2021-02-03 08:49:33.475', 'TEXT', 'APPROVED', 'Ne kadar yükselirsen, uçmayı bilmeyenlere o kadar küçük görünürsün.', 11, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (49, 0, '2021-02-03 09:52:03.901285', 'IMAGE', 'DRAFT', 'Keyifli akşamlar herkese :)', 16, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (50, 0, '2021-02-03 09:53:43.739177', 'IMAGE', 'DRAFT', 'Buralar eskiden dutluktu :) :)', 16, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (51, 0, '2021-02-03 09:54:15.921177', 'IMAGE', 'DRAFT', 'Yorucu bir gün', 16, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (46, 0, '2021-02-02 14:14:45.283', 'IMAGE', 'DECLINED', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 16, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (89, 0, '2021-02-02 22:00:40.407', 'TEXT', 'DECLINED', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (90, 0, '2021-02-02 22:02:13.258', 'TEXT', 'DECLINED', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (130, 0, '2021-02-03 10:24:13.044', 'TEXT', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 13, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (106, 0, '2021-02-02 23:42:19.966', 'IMAGE', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (59, 0, '2021-02-02 14:14:45.283', 'TEXT', 'APPROVED', 'Turkcell’le hayata bağlanıyoruz.', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (104, 0, '2021-02-02 23:19:12.081', 'TEXT', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (105, 0, '2021-02-02 23:27:11.805', 'TEXT', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (98, 0, '2021-02-02 22:24:16.642', 'TEXT', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (99, 0, '2021-02-02 22:48:46.313', 'TEXT', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (96, 0, '2021-02-02 22:17:30.937', 'TEXT', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (6, 17, '2021-02-02 14:28:34.385', 'TEXT', 'APPROVED', 'Mutlu olmayı yarına bırakmak, karşıya geçmek için nehrin durmasını beklemeye benzer ve bilirsin, o nehir asla durmaz. – Grange', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (4, 0, '2021-02-02 13:58:34.385', 'IMAGE', 'APPROVED', 'Arkadaşlarla keyifli bir gün :)', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (5, 17, '2021-02-02 14:28:34.385', 'TEXT', 'APPROVED', 'Kar taneleri ne güzel anlatıyor, birbirlerine zarar vermeden de yol almanın mümkün olduğunu.', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (88, 0, '2021-02-02 21:59:19.564', 'TEXT', 'DRAFT', 'fbfjrjdbsbdjrjdndndn', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (124, 0, '2021-02-03 04:15:50.999', 'TEXT', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 17, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (117, 0, '2021-02-03 02:32:12.15', 'TEXT', 'DRAFT', 'bipisenanlat her çeyrekte bir milyon update', 17, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (125, 0, '2021-02-03 04:42:46.273', 'TEXT', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 17, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (1, 0, '2021-02-02 23:36:41.64315', 'TEXT', 'DECLINED', 'bipisenanlat her çeyrekte bir milyon seffd', 16, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (114, 0, '2021-02-03 01:21:51.689', 'TEXT', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 17, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (115, 0, '2021-02-03 01:22:00.465', 'TEXT', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 17, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (110, 0, '2021-02-02 23:52:10.56', 'IMAGE', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (132, 0, '2021-02-03 08:40:18.415972', 'TEXT', 'APPROVED', 'Turkcell ile hayata bağlandım, mutluyum.', 13, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (135, 5, '2021-01-10 08:49:33.475', 'TEXT', 'APPROVED', 'Doğuştan sahip olduklarınızla yaşamayı öğrenmek bir süreç, bir katılım, yani yaşamınızın yoğrulmasıdır.', 16, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (137, 5, '2021-02-02 08:49:33.475', 'TEXT', 'APPROVED', 'Küçük işlere gereğinden çok önem verenler, elinden büyük iş gelmeyenlerdir. Eflatun', 16, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (138, 5, '2021-02-02 08:49:33.475', 'TEXT', 'APPROVED', 'Mutluluk her şeyden önce vücut sağlığındadır. Curtis', 11, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (123, 10, '2021-01-31 10:20:03.073', 'TEXT', 'APPROVED', 'Ayı hedeflerimizin üzerinde mutlu bir şekilde kapatmak güzel oldu :)', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (120, 10, '2021-01-11 00:20:03.073', 'TEXT', 'APPROVED', 'Yeni haftaya yüzdeyüzon parolasıyla girdik.', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (121, 10, '2021-02-03 01:09:56.250005', 'TEXT', 'APPROVED', 'Bipisenanlat ile müşterilerimizin degerli fikirlerini aldık.', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (119, 0, '2021-02-02 14:14:45.283', 'TEXT', 'DRAFT', 'Akka', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (116, 0, '2021-02-02 14:14:45.283', 'TEXT', 'DRAFT', 'bipisenanlat her çeyrekte bir milyon', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (140, 0, '2021-02-02 14:14:45.283', 'TEXT', 'APPROVED', 'By gun gunlerden carsamba', 8, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (111, 0, '2021-02-02 23:52:30.298', 'TEXT', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (19, 10, '2021-02-03 00:14:00.040111', 'IMAGE', 'APPROVED', 'Turkcell TIM Mağazalarımız yeni döneme hazır.', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (108, 0, '2021-02-02 23:48:29.311', 'IMAGE', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (143, 0, '2021-02-03 12:41:20.823', 'TEXT', 'APPROVED', 'Günaydın, iyi haftalar.', 13, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (92, 0, '2021-02-03 09:55:54.507444', 'IMAGE', 'DRAFT', 'Şaşırtıcı bir haber alınca ben :)', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (84, 0, '2021-02-03 09:56:26.306021', 'IMAGE', 'DRAFT', 'Film keyfi başlasın', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (122, 10, '2021-01-11 10:20:03.073', 'TEXT', 'APPROVED', 'Güler yüzlü elçilerimiz ile tüm Türkiye''nin hizmetindeyiz :)', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (85, 0, '2021-02-03 09:57:06.362838', 'IMAGE', 'DRAFT', 'Cras ultricies ligula sed magna dictum porta. Proin eget tortor risus. Cras ultricies ligula sed magna dictum porta. Curabitur aliquet quam id dui posuere blandit.', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (97, 0, '2021-02-02 22:23:13.218', 'TEXT', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (102, 0, '2021-02-02 22:53:59.422', 'TEXT', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (109, 0, '2021-02-02 23:51:22.003', 'IMAGE', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (112, 0, '2021-02-03 00:24:10.589', 'TEXT', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (107, 0, '2021-02-02 23:46:21.365', 'IMAGE', 'DRAFT', 'filter', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (118, 0, '2021-02-03 02:33:43.426', 'TEXT', 'DRAFT', 'bipisenanlat her çeyrekte bir milyon denemeee', 17, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (127, 5, '2021-01-10 07:16:55.687', 'TEXT', 'APPROVED', 'Turkcell hattınız var ise her yerden çeker.', 11, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (103, 0, '2021-02-02 22:59:05.446', 'TEXT', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (100, 0, '2021-02-02 22:49:37.378', 'TEXT', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (101, 0, '2021-02-02 22:52:06.871', 'TEXT', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (91, 0, '2021-02-02 22:05:54.139', 'TEXT', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (94, 0, '2021-02-02 22:14:00.959', 'TEXT', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (95, 0, '2021-02-02 22:14:58.383', 'TEXT', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (113, 0, '2021-02-03 01:21:44.748', 'TEXT', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (93, 0, '2021-02-02 22:12:33.712', 'TEXT', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (86, 0, '2021-02-02 21:45:33.811', 'TEXT', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (87, 0, '2021-02-02 21:51:27.119', 'TEXT', 'DRAFT', 'Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune', 14, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (145, 0, '2021-02-03 14:54:09.949', 'TEXT', 'DRAFT', 'iyi bir başlangıç ', 13, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (147, 0, '2021-02-03 15:20:08.038', 'TEXT', 'DRAFT', 'bugünlerden güzellik.. ', 13, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (150, 0, '2021-02-02 14:14:45.283', 'TEXT', 'DRAFT', 'Huvubub', 8, NULL);
INSERT INTO feeds.feeds (id, likes, post_date, post_type, status, title, user_id, tags) VALUES (151, 0, '2021-02-02 14:14:45.283', 'IMAGE', 'DRAFT', 'Yuksekler ', 8, NULL);


--
-- Data for Name: likes; Type: TABLE DATA; Schema: feeds; Owner: goArena
--

INSERT INTO feeds.likes (id, feed_id, post_date, comment, user_id) VALUES (1, 4, '2021-02-03 09:05:41.923', 'Gün', 16);
INSERT INTO feeds.likes (id, feed_id, post_date, comment, user_id) VALUES (2, 19, '2021-02-03 09:05:41.923', 'Mükemmel', 16);


--
-- Data for Name: media; Type: TABLE DATA; Schema: feeds; Owner: goArena
--

INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (7, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612285522.314939.png', 16);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (9, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612285522.455992.png', 16);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (3, 4, 'image/jpeg', 'https://i.hizliresim.com/Vpo0fu.jpg', 9);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (8, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612285522.314939.png', 16);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (10, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612285522.455992.png', 16);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (21, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612285964.2655768.png', 16);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (20, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612285963.996206.png', 16);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (32, 1, 'stringqwdqwdq', 'strinqwdqwdg', 16);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (31, 1, 'string', 'string', 16);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (25, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612286329.854769.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (24, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612286330.082385.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (23, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612286329.530459.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (22, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612286329.370147.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (111, NULL, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/1612336471084.png', 13);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (116, 151, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612359030.4128308.png', 8);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (59, 86, 'string', 'string', 0);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (60, 86, 'string', 'string', 0);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (61, 86, 'string', 'string', 0);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (6, NULL, 'string', 'string', 0);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (55, 84, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612291383.0044432.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (30, 1, 'string', 'string', 16);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (17, 51, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612285755.189821.png', 16);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (16, 50, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612285755.134295.png', 16);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (19, 50, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612285755.189821.png', 16);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (18, 50, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612285755.117917.png', 16);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (11, 49, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612285755.134295.png', 16);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (13, 49, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612285755.189821.png', 16);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (12, 51, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612285755.134295.png', 16);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (15, 51, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612285755.117917.png', 16);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (14, 49, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612285755.117917.png', 16);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (1, 1, 'image/jpeg', 'https://www.bambooworld.com.tr/images/galeri/masali-puflu-kose-takimi.jpg', 16);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (63, 87, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612291881699.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (62, 87, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612291881639.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (64, 88, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612292355824.png', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (112, 128, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/1612336897912.png', 13);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (33, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612288382.9422789.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (34, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612288619.3452768.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (35, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612288619.3452768.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (36, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612288975.950625.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (37, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612289716.3440762.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (40, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612290320.427325.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (41, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612290649.937879.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (43, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612290650.169091.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (42, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612290650.1139832.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (45, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612290650.2810168.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (44, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612290650.313036.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (46, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612290692.676474.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (47, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612291240.697334.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (114, 142, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612344672.875428.png', 8);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (54, 84, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612291382.461823.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (80, 95, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612293296118.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (29, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612287970.330217.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (2, NULL, 'video/mp4', 'http://turkcell.mtek.me/files/downloadFile/PexelVideos2099568.mp4', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (5, 19, 'image/jpeg', 'http://turkcell.mtek.me:8080/files/downloadFile/tim2.jpg', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (4, 19, 'image/jpeg', 'http://turkcell.mtek.me:8080/files/downloadFile/tim1.jpg', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (57, 84, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612291382.778794.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (56, 84, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612291383.065732.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (58, 84, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612291382.5972872.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (105, 110, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/1612299121953.png', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (104, 109, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/1612299074302.png', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (107, 111, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612299140043.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (106, 111, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612299140022.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (109, 124, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/1612314934837.png', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (110, 125, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/1612316557085.png', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (97, 103, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612295943770.png', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (96, 103, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612295943770.png', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (99, 104, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612297149604.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (98, 104, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612297149525.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (101, 106, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/1612298533240.png', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (100, 105, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_16122976172877841235538275867057.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (103, 108, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/1612298901241.png', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (102, 107, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/1612298775186.png', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (89, 99, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612295324292.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (88, 99, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612295324235.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (91, 100, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612295375634.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (90, 100, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612295375634.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (93, 101, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612295522629.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (92, 101, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612295522629.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (95, 103, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612295943770.png', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (94, 102, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612295637746.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (81, 95, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612293296118.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (108, 113, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/1612304495505.png', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (83, 96, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612293448532.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (82, 96, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612293448532.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (85, 97, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612293791316.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (84, 97, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612293791316.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (87, 98, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612293854619.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (86, 98, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612293854619.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (73, 91, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_16122927288647209846987806330984.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (72, 91, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_16122927288647209846987806330984.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (75, 92, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612292928502.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (74, 92, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612292928502.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (77, 93, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612293150295.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (76, 93, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612293150295.jpg', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (79, 94, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612293238401.png', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (78, 94, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612293238401.png', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (65, 88, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612292355947.png', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (27, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612286407.102381.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (26, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612286407.031662.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (28, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612286407.097347.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (38, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612289716.487935.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (39, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612290260.719555.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (49, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612291240.885164.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (48, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612291240.720509.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (51, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612291240.70898.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (50, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612291240.7477531.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (53, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612291369.565405.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (52, NULL, 'image/png', 'http://turkcell.mtek.me:8080/files/downloadFile/1612291369.3530269.png', 14);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (67, 89, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612292413040.png', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (66, 89, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612292412920.png', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (69, 90, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612292529490.png', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (68, 90, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612292529408.png', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (71, 90, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612292529490.png', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (70, 90, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/ei_1612292529408.png', 17);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (113, NULL, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/1612336994877.png', 13);
INSERT INTO feeds.media (id, feed_id, mime_type, uri, user_id) VALUES (115, NULL, 'image/*', 'http://turkcell.mtek.me:8080/files/downloadFile/1612356783891.png', 13);


--
-- Data for Name: tags; Type: TABLE DATA; Schema: feeds; Owner: goArena
--



--
-- Data for Name: shops; Type: TABLE DATA; Schema: users; Owner: goArena
--

INSERT INTO users.shops (id, name) VALUES (1, 'Turkcell İzmir');
INSERT INTO users.shops (id, name) VALUES (2, NULL);
INSERT INTO users.shops (id, name) VALUES (3, 'Demirsoy İletişim');
INSERT INTO users.shops (id, name) VALUES (4, 'Eskişehir İletişim');


--
-- Data for Name: users; Type: TABLE DATA; Schema: users; Owner: goArena
--

INSERT INTO users.users (id, avatar, email, employee_type, first_name, last_name, password, username, shop_id) VALUES (1, 'string', 'string', 'MANAGER', 'string', 'string', 'string', 'string', 1);
INSERT INTO users.users (id, avatar, email, employee_type, first_name, last_name, password, username, shop_id) VALUES (5, 'https://bursakuaforler.com/wp-content/uploads/2020/01/Quiff-erkek-sac-modeli.jpg', 'ahmet@gmail.com', 'MANAGER', 'Ahmet', 'ÇalıkOğlu', 'Dd123123', 'ahmetCC', 1);
INSERT INTO users.users (id, avatar, email, employee_type, first_name, last_name, password, username, shop_id) VALUES (6, 'https://bursakuaforler.com/wp-content/uploads/2020/01/Quiff-erkek-sac-modeli.jpg', 'ahmet@gmail.com', 'MANAGER', 'Ahmet', 'ÇalıkOğlu', 'Dd123123', 'ahmetCC', 1);
INSERT INTO users.users (id, avatar, email, employee_type, first_name, last_name, password, username, shop_id) VALUES (7, 'https://image.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg', 'olcaydemirsoy@gmail.com', 'MANAGER', 'Olcay', 'Demirsoy', '123456', 'OlcayDemirsoy', 3);
INSERT INTO users.users (id, avatar, email, employee_type, first_name, last_name, password, username, shop_id) VALUES (8, 'https://image.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg', 'ahmetsahin@gmail.com', 'MANAGER', 'Ahmet', 'Şahin', '123456', 'AhmetSahin', 3);
INSERT INTO users.users (id, avatar, email, employee_type, first_name, last_name, password, username, shop_id) VALUES (9, 'https://i.hizliresim.com/HfRJ6N.jpg', 'esrakara@gmail.com', 'MANAGER', 'Esra', 'Kara', '123456', 'EsraKara', 4);
INSERT INTO users.users (id, avatar, email, employee_type, first_name, last_name, password, username, shop_id) VALUES (10, 'https://image.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg', 'ademsönmez@gmail.com', 'EMPLOYEE', 'Adem', 'Sönmez', '123456', 'AdemSönmez', 3);
INSERT INTO users.users (id, avatar, email, employee_type, first_name, last_name, password, username, shop_id) VALUES (11, 'https://image.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg', 'mehmetkartal@gmail.com', 'EMPLOYEE', 'Mehmet', 'Kartal', '123456', 'MehmetKartal', 3);
INSERT INTO users.users (id, avatar, email, employee_type, first_name, last_name, password, username, shop_id) VALUES (12, 'https://pbs.twimg.com/profile_images/999391871108464640/kG3BcVBg_400x400.jpg', 'olcay@mtek.me', 'EMPLOYEE', 'olcay', 'dsoy', 'hebelekhubelek', 'olcayto', 3);
INSERT INTO users.users (id, avatar, email, employee_type, first_name, last_name, password, username, shop_id) VALUES (14, 'https://image.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg', 'ahmetsahin@gmail.com', 'MANAGER', 'Ahmet', 'Şahin', 'q1w2e3r4aqswdefr', 'AhmetSahin', 3);
INSERT INTO users.users (id, avatar, email, employee_type, first_name, last_name, password, username, shop_id) VALUES (15, 'https://image.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg', 'mehmetkartal@gmail.com', 'EMPLOYEE', 'Mehmet', 'Kartal', 'Anti4277', 'MehmetKartal', 3);
INSERT INTO users.users (id, avatar, email, employee_type, first_name, last_name, password, username, shop_id) VALUES (16, 'https://image.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg', 'olcaydemirsoy@gmail.com', 'MANAGER', 'Olcay', 'Demirsoy', 'Anti4277', 'OlcayDemirsoy', 3);
INSERT INTO users.users (id, avatar, email, employee_type, first_name, last_name, password, username, shop_id) VALUES (17, 'https://image.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg', 'ademsonmez@gmail.com', 'EMPLOYEE', 'Adem', 'Sönmez', 'Anti4277', 'AdemSönmez', 3);
INSERT INTO users.users (id, avatar, email, employee_type, first_name, last_name, password, username, shop_id) VALUES (13, 'https://image.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg', 'brkcnszgn@gmail.com', 'EMPLOYEE', 'Burakcan', 'Szgbn', 'Dd123123', 'brkcnszgn', 3);


--
-- Name: expectations_id_seq; Type: SEQUENCE SET; Schema: dashboard; Owner: goArena
--

SELECT pg_catalog.setval('dashboard.expectations_id_seq', 22, true);


--
-- Name: sales_id_seq; Type: SEQUENCE SET; Schema: dashboard; Owner: goArena
--

SELECT pg_catalog.setval('dashboard.sales_id_seq', 21, true);


--
-- Name: comments_id_seq; Type: SEQUENCE SET; Schema: feeds; Owner: goArena
--

SELECT pg_catalog.setval('feeds.comments_id_seq', 11, true);


--
-- Name: feeds_id_seq; Type: SEQUENCE SET; Schema: feeds; Owner: goArena
--

SELECT pg_catalog.setval('feeds.feeds_id_seq', 151, true);


--
-- Name: likes_id_seq; Type: SEQUENCE SET; Schema: feeds; Owner: goArena
--

SELECT pg_catalog.setval('feeds.likes_id_seq', 2, true);


--
-- Name: media_id_seq; Type: SEQUENCE SET; Schema: feeds; Owner: goArena
--

SELECT pg_catalog.setval('feeds.media_id_seq', 116, true);


--
-- Name: tags_id_seq; Type: SEQUENCE SET; Schema: feeds; Owner: goArena
--

SELECT pg_catalog.setval('feeds.tags_id_seq', 1, false);


--
-- Name: shops_id_seq; Type: SEQUENCE SET; Schema: users; Owner: goArena
--

SELECT pg_catalog.setval('users.shops_id_seq', 4, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: users; Owner: goArena
--

SELECT pg_catalog.setval('users.users_id_seq', 17, true);


--
-- Name: expectations expectations_pkey; Type: CONSTRAINT; Schema: dashboard; Owner: goArena
--

ALTER TABLE ONLY dashboard.expectations
    ADD CONSTRAINT expectations_pkey PRIMARY KEY (id);


--
-- Name: sales sales_pkey; Type: CONSTRAINT; Schema: dashboard; Owner: goArena
--

ALTER TABLE ONLY dashboard.sales
    ADD CONSTRAINT sales_pkey PRIMARY KEY (id);


--
-- Name: comments comments_pkey; Type: CONSTRAINT; Schema: feeds; Owner: goArena
--

ALTER TABLE ONLY feeds.comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);


--
-- Name: feeds feeds_pkey; Type: CONSTRAINT; Schema: feeds; Owner: goArena
--

ALTER TABLE ONLY feeds.feeds
    ADD CONSTRAINT feeds_pkey PRIMARY KEY (id);


--
-- Name: likes likes_pkey; Type: CONSTRAINT; Schema: feeds; Owner: goArena
--

ALTER TABLE ONLY feeds.likes
    ADD CONSTRAINT likes_pkey PRIMARY KEY (id);


--
-- Name: media media_pkey; Type: CONSTRAINT; Schema: feeds; Owner: goArena
--

ALTER TABLE ONLY feeds.media
    ADD CONSTRAINT media_pkey PRIMARY KEY (id);


--
-- Name: tags tags_pkey; Type: CONSTRAINT; Schema: feeds; Owner: goArena
--

ALTER TABLE ONLY feeds.tags
    ADD CONSTRAINT tags_pkey PRIMARY KEY (id);


--
-- Name: shops shops_pkey; Type: CONSTRAINT; Schema: users; Owner: goArena
--

ALTER TABLE ONLY users.shops
    ADD CONSTRAINT shops_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: users; Owner: goArena
--

ALTER TABLE ONLY users.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: expectations fk2h6tuh0xhg9wpjas9ip67fts8; Type: FK CONSTRAINT; Schema: dashboard; Owner: goArena
--

ALTER TABLE ONLY dashboard.expectations
    ADD CONSTRAINT fk2h6tuh0xhg9wpjas9ip67fts8 FOREIGN KEY (shop_id) REFERENCES users.shops(id);


--
-- Name: sales fk5bgaw8g0rrbqdvafq36g58smk; Type: FK CONSTRAINT; Schema: dashboard; Owner: goArena
--

ALTER TABLE ONLY dashboard.sales
    ADD CONSTRAINT fk5bgaw8g0rrbqdvafq36g58smk FOREIGN KEY (user_id) REFERENCES users.users(id);


--
-- Name: sales fkc66d1dedoql5rqahfxtxkg1kk; Type: FK CONSTRAINT; Schema: dashboard; Owner: goArena
--

ALTER TABLE ONLY dashboard.sales
    ADD CONSTRAINT fkc66d1dedoql5rqahfxtxkg1kk FOREIGN KEY (shop_id) REFERENCES users.shops(id);


--
-- Name: expectations fkmdnadxdcv5r3okl1dcb6jnu7k; Type: FK CONSTRAINT; Schema: dashboard; Owner: goArena
--

ALTER TABLE ONLY dashboard.expectations
    ADD CONSTRAINT fkmdnadxdcv5r3okl1dcb6jnu7k FOREIGN KEY (user_id) REFERENCES users.users(id);


--
-- Name: media fk7ng0nw4kpg0y285btg0udhiis; Type: FK CONSTRAINT; Schema: feeds; Owner: goArena
--

ALTER TABLE ONLY feeds.media
    ADD CONSTRAINT fk7ng0nw4kpg0y285btg0udhiis FOREIGN KEY (feed_id) REFERENCES feeds.feeds(id);


--
-- Name: comments fk8omq0tc18jd43bu5tjh6jvraq; Type: FK CONSTRAINT; Schema: feeds; Owner: goArena
--

ALTER TABLE ONLY feeds.comments
    ADD CONSTRAINT fk8omq0tc18jd43bu5tjh6jvraq FOREIGN KEY (user_id) REFERENCES users.users(id);


--
-- Name: feeds fka4nmt7wyx9clm9okj61dgd1tw; Type: FK CONSTRAINT; Schema: feeds; Owner: goArena
--

ALTER TABLE ONLY feeds.feeds
    ADD CONSTRAINT fka4nmt7wyx9clm9okj61dgd1tw FOREIGN KEY (user_id) REFERENCES users.users(id);


--
-- Name: likes fknvx9seeqqyy71bij291pwiwrg; Type: FK CONSTRAINT; Schema: feeds; Owner: goArena
--

ALTER TABLE ONLY feeds.likes
    ADD CONSTRAINT fknvx9seeqqyy71bij291pwiwrg FOREIGN KEY (user_id) REFERENCES users.users(id);


--
-- Name: comments fktkh5bu2eiao6weakchx22ffqh; Type: FK CONSTRAINT; Schema: feeds; Owner: goArena
--

ALTER TABLE ONLY feeds.comments
    ADD CONSTRAINT fktkh5bu2eiao6weakchx22ffqh FOREIGN KEY (feed_id) REFERENCES feeds.feeds(id);


--
-- Name: users fkbadc51v95q4hmcfflt8g3yfck; Type: FK CONSTRAINT; Schema: users; Owner: goArena
--

ALTER TABLE ONLY users.users
    ADD CONSTRAINT fkbadc51v95q4hmcfflt8g3yfck FOREIGN KEY (shop_id) REFERENCES users.shops(id);


--
-- PostgreSQL database dump complete
--

