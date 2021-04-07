package engcom.mateus.apresentaodetestesunitarios.domain.repositories

import engcom.mateus.apresentaodetestesunitarios.domain.Either

interface LoginRepository {

    suspend fun login(email: String, password: String): Either<Unit, Exception>

    companion object {
        operator fun invoke() = object : LoginRepository {
            override suspend fun login(email: String, password: String) = Either.Left(Unit)
        }
    }
}
