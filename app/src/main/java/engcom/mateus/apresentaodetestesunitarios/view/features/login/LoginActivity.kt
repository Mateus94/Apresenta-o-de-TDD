package engcom.mateus.apresentaodetestesunitarios.view.features.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import engcom.mateus.apresentaodetestesunitarios.R
import engcom.mateus.apresentaodetestesunitarios.databinding.ActivityLoginBinding
import engcom.mateus.apresentaodetestesunitarios.presentation.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel by viewModel<LoginViewModel>()

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        inscribeObservers()
    }

    private fun inscribeObservers() {
        viewModel.showSuccess.observe(this, Observer {
            Snackbar.make(binding.root, "Sucesso", Snackbar.LENGTH_SHORT).show()
        })
        viewModel.showError.observe(this, Observer {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        })
    }
}
