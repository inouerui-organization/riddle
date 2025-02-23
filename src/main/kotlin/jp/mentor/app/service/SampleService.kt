package jp.mentor.app.service

import jp.mentor.app.api.dto.SampleDto
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

    fun createSample(dto: SampleDto): SampleDto {
        val sample = Sample(
            name = dto.name,
            mail = dto.mail,
            age = dto.age
        )
        val savedSample = sampleRepository.save(sample)
        return dto.copy(id = savedSample.id)
    }

    fun getSample(id: Int): SampleDto? {
        return sampleRepository.findById(id)
            .map { SampleDto(
                id = it.id,
                name = it.name,
                mail = it.mail,
                age = it.age
            ) }
            .orElse(null)
    }
}