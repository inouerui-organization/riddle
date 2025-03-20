package jp.mentor.app.api.response

import jp.mentor.app.application.commnd.SampleResult

/**
 * サンプル用のレスポンスデータクラス.
 *
 * @author rui.inoue
 */
data class SampleResponse(
    val id: Int,
    val name: String,
    val mail: String?,
    val age: Int?
) {
    companion object {
        fun from(result: SampleResult) = SampleResponse(
            result.id,
            result.name,
            result.email,
            result.age
        )
    }
}