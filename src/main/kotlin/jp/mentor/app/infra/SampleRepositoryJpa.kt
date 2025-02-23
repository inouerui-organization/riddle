package jp.mentor.app.infra

import jp.mentor.app.domain.model.Sample
import jp.mentor.app.domain.repositoty.SampleRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * サンプルのリポジトリ.
 *
 * @author rui.inoue
 */
@Repository
interface SampleRepositoryJpa: JpaRepository<Sample, Int>, SampleRepository {
}