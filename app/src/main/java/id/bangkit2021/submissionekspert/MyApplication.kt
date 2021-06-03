package id.bangkit2021.submissionekspert

import android.app.Application
import id.bangkit2021.submissionekspert.core.di.CoreModule
import id.bangkit2021.submissionekspert.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    AppModule().useCaseModule,
                    AppModule().viewModelModule,
                    CoreModule().databaseModule,
                    CoreModule().networkModule,
                    CoreModule().repositoryModule
                )
            )
        }
    }
}