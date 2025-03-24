package jp.mentor.app.exception

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jp.mentor.app.api.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * エラーハンドリング.
 *
 * @author rui.inoue
 */
@RestControllerAdvice
class ExceptionHandler {

    /**
     * バリデーションエラーのハンドラ
     */
    @Operation(
        summary = "バリデーションエラー",
        description = "リクエストに対して、バリデーションエラーが発生した場合のエラーです",
        responses = [
            ApiResponse(
                responseCode = "400",
                description = "Bad Request",
                content = [
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = ErrorResponse::class)
                    )
                ]
            )
        ]
    )
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorResponse(
                message = "バリデーションエラーです",
                errors = listOf(
                    ex.bindingResult.fieldErrors.associate {
                        it.field to it.defaultMessage
                    }
                )
            )
        )
    }

    /**
     * BusinessExceptionのハンドラ
     */
    @Operation(
        summary = "業務例外",
        description = "業務処理中に発生した例外です",
        responses = [
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
    @ExceptionHandler(BusinessException::class)
    fun handleException(ex: BusinessException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            ErrorResponse(
                message = "業務例外が発生しました",
                errors = listOf(
                    ex.message
                )
            )
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            ErrorResponse(
                message = ex.message ?: "不明なエラーが発生しました",
                errors = listOf(
                    ex.cause
                )
            )
        )
    }
}