package engcom.mateus.apresentaodetestesunitarios.domain.usecases.login

import engcom.mateus.apresentaodetestesunitarios.domain.Either
import engcom.mateus.apresentaodetestesunitarios.domain.repositories.LoginRepository

class LoginUseCase(
    private val loginRepository: LoginRepository
) {
    suspend fun login(email: String?, password: String?): Either<Unit, Exception> {
        return if (email.isNullOrBlank())
             Either.Right(FormException.EmailIsEmptyException)
        else if (password.isNullOrBlank()) {
             Either.Right(FormException.PasswordIsEmptyException)
        } else if (password.length < MIN_PASSWORD_SIZE) {
             Either.Right(FormException.MinPasswordException)
        } else loginRepository.login(email.trim(), password)
    }

    sealed class FormException : Exception() {
        object EmailIsEmptyException : FormException()
        object PasswordIsEmptyException : FormException()
        object MinPasswordException : FormException()
    }

    companion object {
        const val MIN_PASSWORD_SIZE = 6
    }
}
