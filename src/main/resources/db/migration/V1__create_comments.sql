CREATE TABLE IF NOT EXISTS comments (
    id BIGSERIAL PRIMARY KEY,
    author VARCHAR(255),
    text TEXT,
    "timestamp" TIMESTAMP WITHOUT TIME ZONE,
    "postId" BIGINT
);