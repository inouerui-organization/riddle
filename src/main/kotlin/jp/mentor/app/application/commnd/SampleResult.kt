package jp.mentor.app.application.commnd


import java.util.*

/**
 * サンプルエンティティの取得結果を表すデータクラス.
 *
 * @author rui.inoue
 */

data class SampleResult(
    val id: UUID?,
    val name: String,
    val email: String?,
    val age: Int?
)