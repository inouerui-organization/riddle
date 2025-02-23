package jp.mentor.app.api.exception

import jp.mentor.app.api.response.ErrorResponse
import org.springframework.http.HttpStatus
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