package ramble.sokol.residentardoftatarstan.presentation.managers

import ramble.sokol.residentardoftatarstan.BuildConfig
import ramble.sokol.residentardoftatarstan.data.utils.ApiMethod
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    companion object {

        private val BASE_URL = BuildConfig.API_KEY

    }

    fun getApi() : ApiMethod {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiMethod::class.java)
    }

}