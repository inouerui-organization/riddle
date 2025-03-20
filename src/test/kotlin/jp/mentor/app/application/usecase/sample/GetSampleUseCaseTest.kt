package jp.mentor.app.application.usecase.sample

import jp.mentor.app.api.exception.BusinessException
import jp.mentor.app.domain.model.Sample
import jp.mentor.app.domain.value.Age
import jp.mentor.app.domain.value.Email
import jp.mentor.app.domain.repositoty.SampleRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@DisplayName("Sample取得ユースケースのテスト")
class GetSampleUseCaseTest {
    @Mock
    private lateinit var sampleRepository: SampleRepository

    @InjectMocks
    private lateinit var getSampleUseCase: GetSampleUseCase

    private lateinit var sampleResult: Sample

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        sampleResult = Sample(id = 1, name = "山田", mail = Email("yamada@docomo"), age = Age(10))
    }

    @Test
    @DisplayName("データが存在する場合、オブジェクトを返す")
    fun test1() {
        whenever(sampleRepository.findById(org.mockito.kotlin.any())).thenReturn(Optional.of(sampleResult))

        val result = getSampleUseCase.execute(1)

        assertNotNull(result)
        assertEquals(1, result.sample.id)
        assertEquals("山田", result.sample.name)
        assertEquals("yamada@docomo", result.sample.mail?.value)
        assertEquals(10, result.sample.age?.value)
    }

    @Test
    @DisplayName("データが存在しない場合、例外をスローする")
    fun test2() {
        whenever(sampleRepository.findById(org.mockito.kotlin.any())).thenReturn(Optional.empty())

        val exception = assertThrows<BusinessException> {
            getSampleUseCase.execute(1)
        }

        assertEquals("レコードがありません", exception.message)
    }
}