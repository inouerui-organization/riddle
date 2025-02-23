package jp.mentor.app.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import jp.mentor.app.api.docs.SampleControllerDoc
import jp.mentor.app.api.exception.BusinessException
import jp.mentor.app.api.request.SampleRequest
import jp.mentor.app.api.response.BaseResponse
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
    fun getSample(@PathVariable id: Int): ResponseEntity<SampleResponse> {
        val sample = sampleService.getSample(id)

        return ResponseEntity.ok(SampleResponse.from(sample))
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
    fun sample(@RequestBody @Valid request: SampleRequest): ResponseEntity<BaseResponse> {
        val sample = sampleService.createSample(request.toDomain())
        return ResponseEntity.ok(
            BaseResponse(
                message = "作成されました",
                payload = listOf(
                    SampleResponse.from(sample)
                )
            )
        )
    }
}