package engcom.mateus.apresentaodetestesunitarios

import android.app.Application
import engcom.mateus.apresentaodetestesunitarios.di.loginModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            koin.loadModules(
                listOf(loginModule)
            )
        }
    }
}
