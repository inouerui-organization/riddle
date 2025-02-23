package jp.mentor.app.service

import jp.mentor.app.domain.model.Sample
import jp.mentor.app.domain.repositoty.SampleRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class SampleServiceTest {
    @Mock
    private lateinit var sampleRepository: SampleRepository

    @InjectMocks
    private lateinit var sampleService: SampleService

    private lateinit var sample: Sample

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        // テスト用のサンプルデータの初期化
        sample = Sample(id = 1, name = "山田", mail = "yamada@yamada", age = 32)
    }

    @Test
    fun test1() {
        whenever(sampleRepository.save(org.mockito.kotlin.any())).thenReturn(sample)

        val requestSample = Sample(name = "山田", mail = "yamada@yamada", age = 32)
        val result = sampleService.createSample(requestSample)

        assertNotNull(result.id)
        assertEquals(result.name, "山田")
        assertEquals(result.mail, "yamada@yamada")
        assertEquals(result.age, 32)

        verify(sampleRepository).save(org.mockito.kotlin.any())
    }
}
