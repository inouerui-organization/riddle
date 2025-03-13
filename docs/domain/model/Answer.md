# Answer(回答)

| フィールド  | 型                                     | 説明           |
|:-------|:--------------------------------------|:-------------|
| id     | int                                   | 回答を一意に識別するid |
| user   | [User](./User.md)                     | 回答者       |
| puzzle | [Puzzle](./Puzzle.md)                 | 対象の謎解き  |
| answerText | [AnswerText](../object/AnswerText.md) | 回答内容  |
| submittedAt | LocalDate                             | 回答日時  |