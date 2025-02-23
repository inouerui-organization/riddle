package jp.mentor.app.api.docs

class SampleControllerDoc {

    class Get {
        companion object {
            const val DESCRIPTION = "GETメソッドのサンプルです。"
        }
    }

    class Post {
        companion object {
            const val DESCRIPTION = "POSTメソッドのサンプルです。"
            const val REQUEST_EXAMPLE =
                """
                {
                  "name": "山田",
                  "mail": "sample@apple.com",
                  "age": 38
                }
                """
            const val RESPONSE_EXAMPLE =
                """
                {
                  "message": "作成されました",
                  "payload": [
                    {
                      "id": 202,
                      "name": "山田",
                      "mail": "sample@apple.com",
                      "age": 38
                    }
                  ]
                }
                """
        }
    }
}