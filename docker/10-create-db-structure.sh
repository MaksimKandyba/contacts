#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL

    CREATE SEQUENCE public.contacts_id_seq
        INCREMENT 1
        START 1
        MINVALUE 1
        MAXVALUE 2147483647
        CACHE 1;

    ALTER SEQUENCE public.contacts_id_seq
        OWNER TO postgres;

    CREATE TABLE public.contacts
    (
        id integer NOT NULL DEFAULT nextval('contacts_id_seq'::regclass),
        name character varying COLLATE pg_catalog."default" NOT NULL,
        CONSTRAINT contacts_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

    ALTER TABLE public.contacts
        OWNER to postgres;

EOSQL