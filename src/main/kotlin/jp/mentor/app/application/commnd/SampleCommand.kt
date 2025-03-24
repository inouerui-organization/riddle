package jp.mentor.app.application.commnd

import jp.mentor.app.domain.model.Sample
import jp.mentor.app.domain.value.Age
import jp.mentor.app.domain.value.Email

/**
 * サンプルエンティティの作成を目的としたコマンドオブジェクト.
 *
 * @author rui.inoue
 */
data class SampleCommand(
    val sample: Sample
) {
    constructor(name: String, email: String?, age: Int?): this(
        Sample(id = null, name = name, mail = email?.let { Email(it) }, age = age?.let { Age(it) })
    )
}