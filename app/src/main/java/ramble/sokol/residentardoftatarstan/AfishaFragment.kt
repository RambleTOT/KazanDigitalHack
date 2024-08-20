package ramble.sokol.residentardoftatarstan

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ramble.sokol.residentardoftatarstan.data.model.GetEventsResponse
import ramble.sokol.residentardoftatarstan.databinding.FragmentAfishaBinding
import ramble.sokol.residentardoftatarstan.databinding.FragmentPayBinding
import ramble.sokol.residentardoftatarstan.presentation.adapters.EventsAdapter
import ramble.sokol.residentardoftatarstan.presentation.fragments.BottomNavigationFragment
import ramble.sokol.residentardoftatarstan.presentation.fragments.ServicesFragment
import ramble.sokol.residentardoftatarstan.presentation.managers.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AfishaFragment : Fragment() {

    private var binding: FragmentAfishaBinding? = null
    private lateinit var eventsList: List<GetEventsResponse>
    private lateinit var sportList: List<GetEventsResponse>
    private lateinit var concertList: List<GetEventsResponse>
    private lateinit var cinemaList: List<GetEventsResponse>
    private lateinit var lectureList: List<GetEventsResponse>
    private lateinit var cultureList: List<GetEventsResponse>
    private lateinit var adapterEvents: EventsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAfishaBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getEvents()
        getEventsCategory("Спорт")
        getEventsCategory("Кино")
        getEventsCategory("Концерты")
        getEventsCategory("Лекции")
        getEventsCategory("Культура")
    }

    private fun init(){

    }

    private fun getEvents(){
        RetrofitHelper().getApi().getEvents().enqueue(object :
            Callback<List<GetEventsResponse>> {

            override fun onResponse(
                call: Call<List<GetEventsResponse>>,
                response: Response<List<GetEventsResponse>>
            ) {
                if (response.isSuccessful){
                    Log.d("MyLog", response.body().toString())
                    eventsList = response.body()!!
                    binding!!.recyclerViewRecommendEvent.apply {
                        adapterEvents = EventsAdapter(eventsList)
                        adapter = adapterEvents
                        layoutManager =
                            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
                        binding!!.recyclerViewRecommendEvent.visibility = View.VISIBLE
                        binding!!.progressRecommend.visibility = View.GONE

                    }

                }
                Log.d("MyLog", response.toString())
            }

            override fun onFailure(call: Call<List<GetEventsResponse>>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
                Toast.makeText(activity, "Возникла ошибка, проверьте подключение", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun getEventsCategory(category: String){
        when (category) {
            "Спорт" -> {
                RetrofitHelper().getApi().getEventsCategory(category).enqueue(object :
                    Callback<List<GetEventsResponse>> {

                    override fun onResponse(
                        call: Call<List<GetEventsResponse>>,
                        response: Response<List<GetEventsResponse>>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("MyLog", response.body().toString())
                            sportList = response.body()!!
                            binding!!.recyclerSportEvent.apply {
                                adapterEvents = EventsAdapter(sportList)
                                adapter = adapterEvents
                                layoutManager =
                                    LinearLayoutManager(
                                        requireActivity(),
                                        LinearLayoutManager.HORIZONTAL,
                                        false
                                    )
                                binding!!.recyclerSportEvent.visibility = View.VISIBLE
                                binding!!.progressSport.visibility = View.GONE

                            }

                        }
                        Log.d("MyLog", response.toString())
                    }

                    override fun onFailure(call: Call<List<GetEventsResponse>>, t: Throwable) {
                        Log.d("MyLog", t.message.toString())
                        Toast.makeText(
                            activity,
                            "Возникла ошибка, проверьте подключение",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
            }
            "Культура" -> {
                RetrofitHelper().getApi().getEventsCategory(category).enqueue(object :
                    Callback<List<GetEventsResponse>> {

                    override fun onResponse(
                        call: Call<List<GetEventsResponse>>,
                        response: Response<List<GetEventsResponse>>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("MyLog", response.body().toString())
                            cultureList = response.body()!!
                            binding!!.recyclerCultureEvent.apply {
                                adapterEvents = EventsAdapter(cultureList)
                                adapter = adapterEvents
                                layoutManager =
                                    LinearLayoutManager(
                                        requireActivity(),
                                        LinearLayoutManager.HORIZONTAL,
                                        false
                                    )
                                binding!!.recyclerCultureEvent.visibility = View.VISIBLE
                                binding!!.progressCulture.visibility = View.GONE

                            }

                        }
                        Log.d("MyLog", response.toString())
                    }

                    override fun onFailure(call: Call<List<GetEventsResponse>>, t: Throwable) {
                        Log.d("MyLog", t.message.toString())
                        Toast.makeText(
                            activity,
                            "Возникла ошибка, проверьте подключение",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
            }
            "Кино" -> {
                RetrofitHelper().getApi().getEventsCategory(category).enqueue(object :
                    Callback<List<GetEventsResponse>> {

                    override fun onResponse(
                        call: Call<List<GetEventsResponse>>,
                        response: Response<List<GetEventsResponse>>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("MyLog", response.body().toString())
                            cinemaList = response.body()!!
                            binding!!.recyclerCinemaEvent.apply {
                                adapterEvents = EventsAdapter(cinemaList)
                                adapter = adapterEvents
                                layoutManager =
                                    LinearLayoutManager(
                                        requireActivity(),
                                        LinearLayoutManager.HORIZONTAL,
                                        false
                                    )
                                binding!!.recyclerCinemaEvent.visibility = View.VISIBLE
                                binding!!.progressCinema.visibility = View.GONE

                            }

                        }
                        Log.d("MyLog", response.toString())
                    }

                    override fun onFailure(call: Call<List<GetEventsResponse>>, t: Throwable) {
                        Log.d("MyLog", t.message.toString())
                        Toast.makeText(
                            activity,
                            "Возникла ошибка, проверьте подключение",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
            }
            "Концерты" -> {
                RetrofitHelper().getApi().getEventsCategory(category).enqueue(object :
                    Callback<List<GetEventsResponse>> {

                    override fun onResponse(
                        call: Call<List<GetEventsResponse>>,
                        response: Response<List<GetEventsResponse>>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("MyLog", response.body().toString())
                            concertList = response.body()!!
                            binding!!.recyclerConcertEvent.apply {
                                adapterEvents = EventsAdapter(concertList)
                                adapter = adapterEvents
                                layoutManager =
                                    LinearLayoutManager(
                                        requireActivity(),
                                        LinearLayoutManager.HORIZONTAL,
                                        false
                                    )
                                binding!!.recyclerConcertEvent.visibility = View.VISIBLE
                                binding!!.progressConcert.visibility = View.GONE

                            }

                        }
                        Log.d("MyLog", response.toString())
                    }

                    override fun onFailure(call: Call<List<GetEventsResponse>>, t: Throwable) {
                        Log.d("MyLog", t.message.toString())
                        Toast.makeText(
                            activity,
                            "Возникла ошибка, проверьте подключение",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
            }
            "Лекции" -> {
                RetrofitHelper().getApi().getEventsCategory(category).enqueue(object :
                    Callback<List<GetEventsResponse>> {

                    override fun onResponse(
                        call: Call<List<GetEventsResponse>>,
                        response: Response<List<GetEventsResponse>>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("MyLog", response.body().toString())
                            lectureList = response.body()!!
                            binding!!.recyclerLectureEvent.apply {
                                adapterEvents = EventsAdapter(lectureList)
                                adapter = adapterEvents
                                layoutManager =
                                    LinearLayoutManager(
                                        requireActivity(),
                                        LinearLayoutManager.HORIZONTAL,
                                        false
                                    )
                                binding!!.recyclerLectureEvent.visibility = View.VISIBLE
                                binding!!.progressLecture.visibility = View.GONE

                            }

                        }
                        Log.d("MyLog", response.toString())
                    }

                    override fun onFailure(call: Call<List<GetEventsResponse>>, t: Throwable) {
                        Log.d("MyLog", t.message.toString())
                        Toast.makeText(
                            activity,
                            "Возникла ошибка, проверьте подключение",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
            }
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.layout_fragment, BottomNavigationFragment(ServicesFragment()))
                transaction.disallowAddToBackStack()
                transaction.commit()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

}