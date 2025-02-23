package jp.mentor.app.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import jp.mentor.app.api.docs.SampleControllerDoc
import jp.mentor.app.api.dto.SampleDto
import jp.mentor.app.api.exception.BusinessException
import jp.mentor.app.api.request.SampleRequest
import jp.mentor.app.api.response.ErrorResponse
import jp.mentor.app.api.response.SampleResponse
import jp.mentor.app.service.SampleService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * サンプル用のコントローラ.
 *
 * @author rui.inoue
 */

@RestController
@RequestMapping("/")
class SampleController(
    private val sampleService: SampleService,
) {

    @Operation(
        summary = "Get Sample",
        description = SampleControllerDoc.Get.DESCRIPTION,
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Successful response",
                content = [
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                    )
                ]
            )
        ]
    )
    @GetMapping("/sample/{id}")
    fun getSample(@PathVariable id: Int): ResponseEntity<SampleDto> {
        val sampleDto = sampleService.getSample(id) ?: throw BusinessException("レコードがありません")
        return ResponseEntity.ok(sampleDto)
    }

    @Operation(
        summary = "Post Sample",
        description = SampleControllerDoc.Post.DESCRIPTION,
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "例のリクエストボディ",
            content = [
                Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = SampleRequest::class),
                    examples = [
                        ExampleObject(
                            value = SampleControllerDoc.Post.REQUEST_EXAMPLE
                        )
                    ]
                )
            ]
        ),
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Successful response",
                content = [
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = SampleResponse::class),
                        examples = [
                            ExampleObject(
                                value = SampleControllerDoc.Post.RESPONSE_EXAMPLE
                            )
                        ]
                    )
                ]
            ),
            ApiResponse(
                responseCode = "500",
                description = "Internal Server Error",
                content = [
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = ErrorResponse::class)
                    )
                ]
            )
        ]
    )
    @PostMapping("/sample")
    fun sample(@RequestBody @Valid request: SampleRequest): ResponseEntity<SampleResponse> {
        var sampleDto = SampleDto(
            name = request.name,
            mail = request.mail,
            age = request.age
        )
        sampleDto = sampleService.createSample(sampleDto)
        return ResponseEntity.ok(
            SampleResponse(
                message = "保存しました",
                payload = listOf(sampleDto)
            )
        )
    }
}