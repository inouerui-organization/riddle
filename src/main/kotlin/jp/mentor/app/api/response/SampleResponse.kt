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
            result.sample.id!!,
            result.sample.name,
            result.sample.mail?.value,
            result.sample.age?.value
        )
    }
}