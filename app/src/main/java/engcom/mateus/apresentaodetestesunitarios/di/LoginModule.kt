package engcom.mateus.apresentaodetestesunitarios.di

import engcom.mateus.apresentaodetestesunitarios.domain.repositories.LoginRepository
import engcom.mateus.apresentaodetestesunitarios.domain.usecases.login.LoginUseCase
import engcom.mateus.apresentaodetestesunitarios.presentation.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {

    viewModel {
        LoginViewModel(
            loginUseCase = get()
        )
    }

    factory {
        LoginUseCase(LoginRepository())
    }
}
