package jp.mentor.app.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.models.examples.Example
import jp.mentor.app.api.docs.SampleControllerDoc
import jp.mentor.app.api.exception.BusinessException
import jp.mentor.app.api.request.SampleRequest
import jp.mentor.app.api.response.ErrorResponse
import jp.mentor.app.api.response.SampleResponse
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * サンプル用のコントローラ.
 *
 * @author rui.inoue
 */

@RestController
@RequestMapping("/")
class SampleController {

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
    @GetMapping("/sample")
    fun getSample(): ResponseEntity<String> {
        return ResponseEntity.ok("成功です")
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
    fun sample(@RequestBody request: SampleRequest): ResponseEntity<SampleResponse> {
        val name = request.name ?: "Unknown"
        val age = request.age ?: 0
//        throw BusinessException(message = "エラーだよ", cause = null);
        return ResponseEntity.ok(
            SampleResponse(
                message = "$name($age)",
                payload = listOf("aaa", "bbb")
            )
        )
    }
}