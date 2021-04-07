package engcom.mateus.apresentaodetestesunitarios.domain.usecases.login

import assertk.assertThat
import assertk.assertions.isInstanceOf
import engcom.mateus.apresentaodetestesunitarios.domain.Either
import engcom.mateus.apresentaodetestesunitarios.domain.repositories.LoginRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginUseCaseTest {

    private val repository = mockk<LoginRepository>()

    private val subject = LoginUseCase(repository)


    @Test
    fun `check if login returns error, if  email is empty`() = runBlockingTest{

        //when
        val result = subject.login(null, null)

        //then
        assertThat(result).isInstanceOf(Either.Right(LoginUseCase.FormException.EmailIsEmptyException)::class.java)
    }

    @Test
    fun `check if login returns error, if  password is empty`() = runBlockingTest{

        //when
        val result = subject.login(SOME_VALID_EMAIL, null)

        //then
        assertThat(result).isInstanceOf(Either.Right(LoginUseCase.FormException.PasswordIsEmptyException)::class.java)
    }

    @Test
    fun `check if login returns error, if password size is less 6`() = runBlockingTest{

        //when
        val result = subject.login(SOME_VALID_EMAIL, SOME_INVALID_PASSWORD)

        //then
        assertThat(result).isInstanceOf(Either.Right(LoginUseCase.FormException.MinPasswordException)::class.java)
    }

    @Test
    fun `check if login returns success, if email and password is valid`() = runBlockingTest{

        //given
        coEvery { repository.login(SOME_VALID_EMAIL, SOME_VALID_PASSWORD)  } returns Either.Left(Unit)

        //when
        val result = subject.login(SOME_VALID_EMAIL_WITH_SPACE, SOME_VALID_PASSWORD)

        //then
        coVerify { repository.login(SOME_VALID_EMAIL, SOME_VALID_PASSWORD) }
        assertThat(result).isInstanceOf(Either.Left(Unit)::class.java)
    }

    companion object{
        const val SOME_VALID_EMAIL_WITH_SPACE = "teste@teste.com "
        const val SOME_VALID_EMAIL = "teste@teste.com"
        const val SOME_VALID_PASSWORD = "123456"
        const val SOME_INVALID_PASSWORD = "12345"
    }

}
