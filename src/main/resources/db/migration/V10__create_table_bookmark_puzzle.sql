CREATE TABLE IF NOT EXISTS app.bookmark_puzzles (
    puzzle_id UUID NOT NULL REFERENCES app.puzzles(id) ON DELETE CASCADE,
    user_id UUID NOT NULL REFERENCES app.users(id) ON DELETE CASCADE,
    PRIMARY KEY (puzzle_id, user_id)
);