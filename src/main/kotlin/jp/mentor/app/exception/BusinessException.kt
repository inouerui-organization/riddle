package jp.mentor.app.exception

class BusinessException(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause)