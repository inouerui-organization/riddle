CREATE TABLE app.bookmark_puzzles (
    puzzle_id UUID NOT NULL REFERENCES puzzles(id) ON DELETE CASCADE,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    PRIMARY KEY (puzzle_id, user_id)
);