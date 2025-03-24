package jp.mentor.app.domain.value

/**
 * 年齢の値オブジェクト.
 * @author rui.inoue
 */
@JvmInline
value class Age(val value: Int) {
    init {
        require(value in 1..100) {
            "Age must be between 1 and 100"
        }
    }
}