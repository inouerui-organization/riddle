package jp.mentor.app.application.commnd


import jp.mentor.app.domain.model.Sample
import jp.mentor.app.domain.value.Age
import jp.mentor.app.domain.value.Email

/**
 * サンプルエンティティの取得結果を表すデータクラス.
 *
 * @author rui.inoue
 */

data class SampleResult(
    val id: Int,
    val name: String,
    val email: String?,
    val age: Int?
)