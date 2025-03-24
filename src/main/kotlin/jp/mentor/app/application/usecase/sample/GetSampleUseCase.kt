package jp.mentor.app.application.usecase.sample

import jakarta.transaction.Transactional
import jp.mentor.app.application.commnd.SampleResult
import jp.mentor.app.domain.repositoty.SampleRepository
import jp.mentor.app.domain.value.SampleId
import jp.mentor.app.exception.BusinessException
import org.springframework.stereotype.Service

/**
 * sampleを取得するユースケース.
 * @author rui.inoue
 */
@Service
@Transactional
class GetSampleUseCase(
    private val sampleRepository: SampleRepository
) {

    fun execute(id: String): SampleResult {
        val sampleId = SampleId.fromString(id)
        val sample = sampleRepository.findById(sampleId.value)
            .orElse(null)
            ?: throw BusinessException("レコードがありません")
        return SampleResult(id = sampleId.value, name = sample.name, email = sample.mail?.value, age = sample.age?.value)
    }
}