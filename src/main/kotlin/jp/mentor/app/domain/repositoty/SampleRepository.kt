package jp.mentor.app.domain.repositoty

import jp.mentor.app.domain.model.Sample
import java.util.*

/**
 * サンプルのリポジトリのインターフェース.
 *
 * @author rui.inoue
 */
interface SampleRepository {
    fun save(sample: Sample): Sample
    fun findById(id: UUID): Optional<Sample>
}