package jp.mentor.app.domain.value

import java.util.UUID

/**
 * sampleのidオブジェクト.
 *
 * @author rui.inoue
 */
@JvmInline
value class SampleId(val value: UUID) {
    companion object {
        fun fromString(id: String): SampleId {
            return SampleId(UUID.fromString(id))
        }
    }

    override fun toString(): String = value.toString()
}