CREATE TABLE IF NOT EXISTS genesys.sensor
(
    id bigint NOT NULL,
    description character varying(255) COLLATE pg_catalog."default",
    model character varying(16) COLLATE pg_catalog."default",
    brand character varying(16) COLLATE pg_catalog."default",
    locale character varying(16) COLLATE pg_catalog."default",
    CONSTRAINT sensor_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS genesys.sensor
    OWNER to postgres;