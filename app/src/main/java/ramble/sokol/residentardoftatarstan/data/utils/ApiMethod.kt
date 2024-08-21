package ramble.sokol.residentardoftatarstan.data.utils

import ramble.sokol.residentardoftatarstan.data.model.GetEventsResponse
import ramble.sokol.residentardoftatarstan.data.model.GetGroundsResponse
import ramble.sokol.residentardoftatarstan.data.model.GetSectionsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiMethod {

    @GET("grounds")
    fun getGrounds(
    ): Call<List<GetGroundsResponse>>

    @GET("events")
    fun getEvents(
    ): Call<List<GetEventsResponse>>

    @GET("events")
    fun getEventsCategory(
        @Query("category") query: String
    ): Call<List<GetEventsResponse>>

    @GET("sections")
    fun getSections(
    ): Call<List<GetSectionsResponse>>

    @GET("sections")
    fun getSectionsCategory(
        @Query("category") query: String
    ): Call<List<GetSectionsResponse>>

}