package jp.mentor.app.application.commnd

import jp.mentor.app.domain.model.Sample
import jp.mentor.app.domain.`object`.Age
import jp.mentor.app.domain.`object`.Email

data class SampleResult(
    val sample: Sample
) {
    constructor(id: Int, name: String, email: Email?, age: Age?): this(
        Sample(id = id, name = name, mail = email, age = age)
    )
}