package jp.mentor.app.application.usecase.sample

import jp.mentor.app.application.commnd.SampleCommand
import jp.mentor.app.domain.model.Sample
import jp.mentor.app.domain.value.Age
import jp.mentor.app.domain.value.Email
import jp.mentor.app.domain.repositoty.SampleRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@DisplayName("Sampleを作成するユースケースのテスト")
class CreateSampleUseCaseTest {
    @Mock
    private lateinit var sampleRepository: SampleRepository

    @InjectMocks
    private lateinit var createSampleUseCase: CreateSampleUseCase

    private lateinit var sampleCommandField: Sample
    private lateinit var sampleResult: Sample

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        sampleCommandField = Sample(id = null, name = "山田", mail = Email("yamada@docomo"), age = Age(10))
        sampleResult = Sample(id = 1, name = "山田", mail = Email("yamada@docomo"), age = Age(10))
    }

    @Test
    @DisplayName("Sampleを作成する")
    fun test1() {
        whenever(sampleRepository.save(org.mockito.kotlin.any())).thenReturn(sampleResult)

        val command = SampleCommand(sampleCommandField)
        val result = createSampleUseCase.execute(command)

        assertNotNull(result)
        assertEquals(1, result.id)
        assertEquals("山田", result.name)
        assertEquals("yamada@docomo", result.email)
        assertEquals(10, result.age)
    }
}