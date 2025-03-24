package jp.mentor.app.application.usecase.sample

import jakarta.transaction.Transactional
import jp.mentor.app.application.commnd.SampleCommand
import jp.mentor.app.application.commnd.SampleResult
import jp.mentor.app.domain.repositoty.SampleRepository
import org.springframework.stereotype.Service
import java.util.UUID

/**
 * sampleを作成するユースケースクラス.
 * @author rui.inoue
 */
@Service
@Transactional
class CreateSampleUseCase(
    private val sampleRepository: SampleRepository
) {
    fun execute(command: SampleCommand): SampleResult {
        val sample = sampleRepository.save(command.sample)
        return SampleResult(id = sample.id, name = sample.name, email = sample.mail?.value, age = sample.age?.value)
    }
}