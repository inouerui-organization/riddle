CREATE TABLE app.user_puzzle_views (
    puzzle_id UUID NOT NULL REFERENCES puzzles(id) ON DELETE CASCADE,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    last_viewed_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (puzzle_id, user_id)
);