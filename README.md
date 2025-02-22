# アプリ開発

## 技術スタック

| 技術          | バージョン       |
|-------------|------------------|
| jdk         | amazon-correto21 |
| postgres    | 17               |
| Spring Boot | 3.4.2            |

## 環境構築

1. `Docker Desktop`などを起動
2. `docker-compose up --build`で初回起動
3. アプリケーションの起動（下記のいずれかで起動）
    - `src/main/java/com/example/levtech/LevtechApplication.java`を起動
    - ターミナルで`./gradlew bootrun`

## データベースへのアクセス

- ローカルからのアクセス（ローカルにpsqlコマンドが入っている前提）
    - `psql -h localhost -U postgres -d app -p 5433`
- コンテナ内でアクセス
    - `docker exec -it levtech-db-1 bash`でコンテナ内に入る
    - `psql -U postgres -d app -p 5432`でデータベース内に入る