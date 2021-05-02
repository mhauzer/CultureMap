--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.2
-- Dumped by pg_dump version 9.5.2

-- Started on 2021-05-02 19:57:41

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2162 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 181 (class 1259 OID 1653107)
-- Name: t_album; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE t_album (
    id integer NOT NULL,
    title character varying,
    type character varying(2),
    url character varying,
    release_year integer,
    complete boolean DEFAULT false NOT NULL
);


--
-- TOC entry 182 (class 1259 OID 1653110)
-- Name: t_album_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE t_album_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2163 (class 0 OID 0)
-- Dependencies: 182
-- Name: t_album_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE t_album_id_seq OWNED BY t_album.id;


--
-- TOC entry 191 (class 1259 OID 1653185)
-- Name: t_album_tag; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE t_album_tag (
    album_id integer NOT NULL,
    dict_label character varying NOT NULL,
    item_label character varying NOT NULL
);


--
-- TOC entry 184 (class 1259 OID 1653122)
-- Name: t_band; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE t_band (
    id integer NOT NULL,
    name character varying,
    url character varying
);


--
-- TOC entry 186 (class 1259 OID 1653131)
-- Name: t_band_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE t_band_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2164 (class 0 OID 0)
-- Dependencies: 186
-- Name: t_band_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE t_band_id_seq OWNED BY t_band.id;


--
-- TOC entry 189 (class 1259 OID 1653160)
-- Name: t_dict; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE t_dict (
    label character varying NOT NULL
);


--
-- TOC entry 190 (class 1259 OID 1653169)
-- Name: t_dict_item; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE t_dict_item (
    dict_label character varying NOT NULL,
    label character varying NOT NULL,
    value character varying,
    descr character varying
);


--
-- TOC entry 187 (class 1259 OID 1653146)
-- Name: t_performing_band; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE t_performing_band (
    album_id integer NOT NULL,
    band_id integer NOT NULL
);


--
-- TOC entry 188 (class 1259 OID 1653151)
-- Name: t_performing_person; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE t_performing_person (
    album_id integer NOT NULL,
    person_id integer NOT NULL
);


--
-- TOC entry 183 (class 1259 OID 1653119)
-- Name: t_person; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE t_person (
    id integer NOT NULL,
    firstname character varying,
    firstname2 character varying,
    lastname character varying,
    lastname2 character varying,
    birth date,
    death date,
    nameprefix character varying,
    birth2 date,
    url character varying,
    title character varying
);


--
-- TOC entry 185 (class 1259 OID 1653125)
-- Name: t_person_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE t_person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2165 (class 0 OID 0)
-- Dependencies: 185
-- Name: t_person_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE t_person_id_seq OWNED BY t_person.id;


--
-- TOC entry 2019 (class 2604 OID 1653112)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY t_album ALTER COLUMN id SET DEFAULT nextval('t_album_id_seq'::regclass);


--
-- TOC entry 2022 (class 2604 OID 1653133)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY t_band ALTER COLUMN id SET DEFAULT nextval('t_band_id_seq'::regclass);


--
-- TOC entry 2021 (class 2604 OID 1653127)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY t_person ALTER COLUMN id SET DEFAULT nextval('t_person_id_seq'::regclass);


--
-- TOC entry 2024 (class 2606 OID 1653141)
-- Name: pk_album; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY t_album
    ADD CONSTRAINT pk_album PRIMARY KEY (id);


--
-- TOC entry 2038 (class 2606 OID 1653193)
-- Name: pk_album_fit; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY t_album_tag
    ADD CONSTRAINT pk_album_fit PRIMARY KEY (album_id, dict_label, item_label);


--
-- TOC entry 2028 (class 2606 OID 1653143)
-- Name: pk_band; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY t_band
    ADD CONSTRAINT pk_band PRIMARY KEY (id);


--
-- TOC entry 2034 (class 2606 OID 1653177)
-- Name: pk_dict; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY t_dict
    ADD CONSTRAINT pk_dict PRIMARY KEY (label);


--
-- TOC entry 2036 (class 2606 OID 1653179)
-- Name: pk_dict_item; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY t_dict_item
    ADD CONSTRAINT pk_dict_item PRIMARY KEY (dict_label, label);


--
-- TOC entry 2030 (class 2606 OID 1653157)
-- Name: pk_performing_band; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY t_performing_band
    ADD CONSTRAINT pk_performing_band PRIMARY KEY (album_id);


--
-- TOC entry 2032 (class 2606 OID 1653155)
-- Name: pk_performing_person; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY t_performing_person
    ADD CONSTRAINT pk_performing_person PRIMARY KEY (album_id, person_id);


--
-- TOC entry 2026 (class 2606 OID 1653145)
-- Name: pk_person; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY t_person
    ADD CONSTRAINT pk_person PRIMARY KEY (id);


--
-- TOC entry 2040 (class 2606 OID 1653194)
-- Name: fk_album_fit_album; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY t_album_tag
    ADD CONSTRAINT fk_album_fit_album FOREIGN KEY (album_id) REFERENCES t_album(id);


--
-- TOC entry 2041 (class 2606 OID 1653199)
-- Name: fk_album_fit_dict_item; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY t_album_tag
    ADD CONSTRAINT fk_album_fit_dict_item FOREIGN KEY (dict_label, item_label) REFERENCES t_dict_item(dict_label, label);


--
-- TOC entry 2039 (class 2606 OID 1653180)
-- Name: fk_dict_item_dict; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY t_dict_item
    ADD CONSTRAINT fk_dict_item_dict FOREIGN KEY (dict_label) REFERENCES t_dict(label);


-- Completed on 2021-05-02 19:57:42

--
-- PostgreSQL database dump complete
--

