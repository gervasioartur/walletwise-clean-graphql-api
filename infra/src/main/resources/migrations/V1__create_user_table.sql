CREATE schema IF NOT EXISTS security;

CREATE TABLE IF NOT EXISTS security.t_users
(
    id         UUID         NOT NULL PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname  VARCHAR(100) NOT NULL,
    username   VARCHAR(100) NOT NULL UNIQUE,
    email      VARCHAR(100) NOT NULL UNIQUE,
    password   VARCHAR(64)  NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- CREATE OR REPLACE FUNCTION security.fn_update_timestamp_column()
--     RETURNS TRIGGER AS $$
--     BEGIN
--         NEW.updated_at = NOW();
--         RETURN NEW;
--     END;
--     $$
-- LANGUAGE plpgsql;
--
-- CREATE TRIGGER security.tr_set_timestamp
--     BEFORE UPDATE
--     ON security.t_users
--     FOR EACH ROW
--     EXECUTE FUNCTION security.fn_update_timestamp_column();