package jp.mentor.app.api.response

/**
 * 共通のレスポンスクラス.
 * payloadにカスタムしたレスポンスクラスを入れる.
 *
 * @author rui.inoue
 */
data class BaseResponse(
    val message: String,
    val payload: List<*>
)
