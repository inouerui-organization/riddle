package jp.mentor.app.api.response

import jp.mentor.app.domain.model.Sample

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
        fun from(sample: Sample) = SampleResponse(sample.id!!, sample.name, sample.mail, sample.age)
    }
}