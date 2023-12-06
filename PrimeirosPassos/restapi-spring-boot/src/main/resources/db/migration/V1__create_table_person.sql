CREATE SEQUENCE person_id_seq;

CREATE TABLE public.person
(
    id bigint NOT NULL DEFAULT nextval('person_id_seq'::regclass),
    address character varying(255) COLLATE pg_catalog."default" NOT NULL,
    first_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    gender character varying(255) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT person_pkey PRIMARY KEY (id)
);


ALTER TABLE public.person
    OWNER to postgres;

