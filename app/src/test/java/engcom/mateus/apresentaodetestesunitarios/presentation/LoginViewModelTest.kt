package engcom.mateus.apresentaodetestesunitarios.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import assertk.assertThat
import assertk.assertions.isEqualTo
import engcom.mateus.apresentaodetestesunitarios.domain.Either
import engcom.mateus.apresentaodetestesunitarios.domain.usecases.login.LoginUseCase
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    @get:Rule
    val viewModelRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private lateinit var subject: LoginViewModel

    private val loginUseCase = mockk<LoginUseCase>()

    @Before
    fun setUp() {
        subject = LoginViewModel(loginUseCase)
    }

    @Test
    fun testeLogin(){

        //given
        coEvery { loginUseCase.login("teste@teste.com", "123456") } returns Either.Left(Unit)
        subject.email.postValue( "teste@teste.com")
        subject.password.postValue( "123456")

        //when
        subject.onClickLogin()

        //then
        assertThat(subject.showSuccess.value).isEqualTo(Unit)



    }
}
