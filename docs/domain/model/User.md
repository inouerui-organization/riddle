# User(ユーザ)

| フィールド | 型                                    | 説明            |
|:------|:-------------------------------------|:--------------|
| id    | int                                  | ユーザを一意に識別するid |
| name  | String                               | ユーザの名前        |
| email | [Email](../object/Email.md)          | ユーザのメールアドレス   |
| bookmarks | List<[Bookmark](./Bookmark.md)>      | ブックマークのリスト     |