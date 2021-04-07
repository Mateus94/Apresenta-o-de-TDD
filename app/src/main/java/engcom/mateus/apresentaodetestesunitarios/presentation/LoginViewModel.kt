package engcom.mateus.apresentaodetestesunitarios.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import engcom.mateus.apresentaodetestesunitarios.domain.Either
import engcom.mateus.apresentaodetestesunitarios.domain.usecases.login.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean> get() = _showLoading
    private val _showSuccess = MutableLiveData<Unit>()
    val showSuccess: LiveData<Unit> get() = _showSuccess
    private val _showError = MutableLiveData<String>()
    val showError: LiveData<String> get() = _showError

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun onClickLogin() {
        doLogin(email.value, password.value)
    }

    private fun doLogin(email: String?, password: String?) {
        viewModelScope.launch {
            when (val result = loginUseCase.login(email, password)) {
                is Either.Left -> _showSuccess.postValue(Unit)
                is Either.Right -> when (result.value) {
                    is LoginUseCase.FormException.EmailIsEmptyException -> _showError.postValue("Preencha o email")
                    is LoginUseCase.FormException.PasswordIsEmptyException -> _showError.postValue("Preencha a senha")
                    is LoginUseCase.FormException.MinPasswordException -> _showError.postValue("A senha deve ter no minimo seis digitos")
                }
            }
        }
    }
}
