package jp.mentor.app.domain.value

/**
 * emailの値オブジェクト.
 * @author rui.inoue
 */
@JvmInline
value class Email(val value: String) {
    init {
        require(value.contains("@")) {
            "Invalid email format"
        }
    }
}