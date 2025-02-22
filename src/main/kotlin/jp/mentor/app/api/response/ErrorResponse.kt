package jp.mentor.app.api.response

/**
 * エラー用のレスポンスクラス.
 *
 * @author rui.inoue
 */
data class ErrorResponse(
    val message: String?,
    val errors: List<*>
)