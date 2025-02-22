package jp.mentor.app.api.exception

import jp.mentor.app.api.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
     * BusinessExceptionのハンドラ
     */
    @ExceptionHandler(BusinessException::class)
    fun handleException(ex: BusinessException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            ErrorResponse(
                message = ex.message ?: "業務例外が発生しました",
                errors = listOf(
                    ex.cause
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