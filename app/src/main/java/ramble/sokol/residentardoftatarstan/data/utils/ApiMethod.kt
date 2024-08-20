package ramble.sokol.residentardoftatarstan.data.utils

import ramble.sokol.residentardoftatarstan.data.model.GetGroundsResponse
import retrofit2.Call
import retrofit2.http.GET


interface ApiMethod {

    @GET("grounds")
    fun getGrounds(
    ): Call<List<GetGroundsResponse>>

}