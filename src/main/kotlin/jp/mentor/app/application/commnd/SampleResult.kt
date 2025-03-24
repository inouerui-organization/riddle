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
    val sample: Sample
) {
    constructor(id: Int, name: String, email: Email?, age: Age?): this(
        Sample(id = id, name = name, mail = email, age = age)
    )
}