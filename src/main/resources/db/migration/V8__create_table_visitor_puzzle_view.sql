CREATE TABLE app.visitor_puzzle_views (
    puzzle_id UUID NOT NULL REFERENCES puzzles(id) ON DELETE CASCADE,
    visitor_id UUID NOT NULL REFERENCES visitors(id) ON DELETE CASCADE,
    last_viewed_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (puzzle_id, visitor_id)
);