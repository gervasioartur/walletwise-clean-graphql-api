CREATE TABLE IF NOT EXISTS security.t_sessions
(
    id            UUID         NOT NULL PRIMARY KEY,
    token         VARCHAR(300) NOT NULL,
    fk_user_id    UUID         NOT NULL REFERENCES security.t_users,
    creation_date TIMESTAMP    NOT NULL,
    is_active     BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);