package jp.mentor.app.service

import jakarta.transaction.Transactional
import jp.mentor.app.api.exception.BusinessException
import jp.mentor.app.domain.model.Sample
import jp.mentor.app.domain.repositoty.SampleRepository
import org.springframework.stereotype.Service

/**
 * サンプルのサービス.
 *
 * @author rui.inoue
 */
@Service
class SampleService(
    private val sampleRepository: SampleRepository
) {

    @Transactional
    fun createSample(sample: Sample): Sample {
        return sampleRepository.save(sample)
    }

    fun getSample(id: Int): Sample {
        return sampleRepository.findById(id)
            .orElse(null)
            ?: throw BusinessException("レコードがありません")
    }
}