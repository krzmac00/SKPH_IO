DROP TABLE IF EXISTS location_types CASCADE;

CREATE TABLE location_types (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    type_name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    is_polygon BOOLEAN NOT NULL
);

-- DROP TABLE IF EXISTS locations CASCADE;
--
-- CREATE TABLE locations (
--     id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
--     name VARCHAR(255) NOT NULL,
--     location_type_id BIGINT NOT NULL,
--     coordinates geometry(Point, 4326) NOT NULL,
--     CONSTRAINT fk_location_type FOREIGN KEY (location_type_id)
--         REFERENCES location_types (id) ON DELETE CASCADE
-- );
--
-- DROP TABLE IF EXISTS routes CASCADE;
--
-- CREATE TABLE routes (
--     id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
--     name VARCHAR(255) NOT NULL,
--     path geometry(LineString, 4326) NOT NULL
-- );