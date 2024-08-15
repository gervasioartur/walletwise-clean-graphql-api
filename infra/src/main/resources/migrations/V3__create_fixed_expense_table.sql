CREATE schema IF NOT EXISTS walletwise;

CREATE TABLE IF NOT EXISTS walletwise.t_fixed_expenses
(
    id            BIGSERIAL         NOT NULL PRIMARY KEY,
    fk_user_id    UUID NOT NULL REFERENCES security.t_users,
    description   VARCHAR(250) NOT NULL ,
    due_day       INT  NOT NULL,
    amount        DECIMAL NOT NULL,
    category      VARCHAR(50) NOT NULL,
    star_date     DATE NOT NULL,
    end_date      DATE NOT NULL,
    is_active     BOOLEAN NOT NULL DEFAULT TRUE,
    created_at    TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP
);