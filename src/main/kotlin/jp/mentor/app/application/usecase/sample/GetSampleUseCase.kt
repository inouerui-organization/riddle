package jp.mentor.app.application.usecase.sample

import jakarta.transaction.Transactional
import jp.mentor.app.exception.BusinessException
import jp.mentor.app.application.commnd.SampleResult
import jp.mentor.app.domain.repositoty.SampleRepository
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

    fun execute(id: Int): SampleResult {
        val sample = sampleRepository.findById(id)
            .orElse(null)
            ?: throw BusinessException("レコードがありません")
        return SampleResult(id = id, name = sample.name, email = sample.mail?.value, age = sample.age?.value)
    }
}