# Puzzle(謎解き)

| フィールド | 型                                       | 説明             |
|:------|:----------------------------------------|:---------------|
| id    | int                                     | 謎解きを一意に識別するid  |
| title | [PuzzleTitle](../object/PuzzleTitle.md) | 謎解きのタイトル       |
| content | String                                  | 謎解きの本文         |
| creator | [User](./User.md)                       | 作成者            |
| visibility | [Visibility](../object/Visibility.md)   | 公開範囲           |
| createdAt | LocalDate                               | 作成日時           |
| updatedAt | LocalDate                               | 更新日時           |
| likes | List<[Like](./Like.md)>                 | いいねのリスト        |
| answers | List<[Answer](Answer.md)>               | 回答のリスト         |

## Method

- `addLike(user: User)`
　　- いいねを追加
- `addBookmark(user: User)`
  - ブックマークを追加
- `submitAnswer(user: User, answerText: AnswerText)`
  - 回答を登録し、正解判定を行う