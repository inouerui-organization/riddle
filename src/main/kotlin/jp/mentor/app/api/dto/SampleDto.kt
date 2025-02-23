package jp.mentor.app.api.dto

/**
 * サンプルのdto.
 *
 * @author rui.inoue
 */
data class SampleDto(
    val id: Int? = null,
    val name: String,
    val mail: String? = null,
    val age: Int? = null
)