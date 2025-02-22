package jp.mentor.app.api.exception

class BusinessException(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause)