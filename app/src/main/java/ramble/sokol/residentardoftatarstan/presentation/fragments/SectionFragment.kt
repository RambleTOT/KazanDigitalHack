package ramble.sokol.residentardoftatarstan.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ramble.sokol.residentardoftatarstan.R
import ramble.sokol.residentardoftatarstan.data.model.GetSectionsResponse
import ramble.sokol.residentardoftatarstan.databinding.FragmentSectionBinding
import ramble.sokol.residentardoftatarstan.presentation.adapters.BottomSheetGround
import ramble.sokol.residentardoftatarstan.presentation.adapters.BottomSheetSection
import ramble.sokol.residentardoftatarstan.presentation.adapters.SectionsAdapter
import ramble.sokol.residentardoftatarstan.presentation.managers.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SectionFragment : Fragment() {

    private var binding: FragmentSectionBinding? = null
    private lateinit var sectionsList: List<GetSectionsResponse>
    private lateinit var sportList: List<GetSectionsResponse>
    private lateinit var intellectList: List<GetSectionsResponse>
    private lateinit var tvoriList: List<GetSectionsResponse>
    private lateinit var adaptiveList: List<GetSectionsResponse>
    private lateinit var adapterSections: SectionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSectionBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSections()
        getSectionCategory("Спорт")
        getSectionCategory("Интеллектуальные")
        getSectionCategory("Творчество")
        getSectionCategory("Адаптивные")
    }

    private fun getSections(){
        RetrofitHelper().getApi().getSections().enqueue(object :
            Callback<List<GetSectionsResponse>> {

            override fun onResponse(
                call: Call<List<GetSectionsResponse>>,
                response: Response<List<GetSectionsResponse>>
            ) {
                if (response.isSuccessful){
                    Log.d("MyLog", response.body().toString())
                    sectionsList = response.body()!!
                    binding!!.recyclerViewRecommendSection.apply {
                        adapterSections = SectionsAdapter(sectionsList)
                        adapterSections.onItemClick = {
                            showBottomSheet(it)
                        }
                        adapter = adapterSections
                        layoutManager =
                            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
                        binding!!.recyclerViewRecommendSection.visibility = View.VISIBLE
                        binding!!.progressRecommend.visibility = View.GONE

                    }

                }
                Log.d("MyLog", response.toString())
            }

            override fun onFailure(call: Call<List<GetSectionsResponse>>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
                Toast.makeText(activity, "Возникла ошибка, проверьте подключение", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun getSectionCategory(category: String){
        when (category) {
            "Спорт" -> {
                RetrofitHelper().getApi().getSectionsCategory(category).enqueue(object :
                    Callback<List<GetSectionsResponse>> {

                    override fun onResponse(
                        call: Call<List<GetSectionsResponse>>,
                        response: Response<List<GetSectionsResponse>>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("MyLog", response.body().toString())
                            sportList = response.body()!!
                            binding!!.recyclerSportSection.apply {
                                adapterSections = SectionsAdapter(sportList)
                                adapterSections.onItemClick = {
                                    showBottomSheet(it)
                                }
                                adapter = adapterSections
                                layoutManager =
                                    LinearLayoutManager(
                                        requireActivity(),
                                        LinearLayoutManager.HORIZONTAL,
                                        false
                                    )
                                binding!!.recyclerSportSection.visibility = View.VISIBLE
                                binding!!.progressSport.visibility = View.GONE

                            }

                        }
                        Log.d("MyLog", response.toString())
                    }

                    override fun onFailure(call: Call<List<GetSectionsResponse>>, t: Throwable) {
                        Log.d("MyLog", t.message.toString())
                        Toast.makeText(
                            activity,
                            "Возникла ошибка, проверьте подключение",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
            }
            "Интеллектуальные" -> {
                RetrofitHelper().getApi().getSectionsCategory(category).enqueue(object :
                    Callback<List<GetSectionsResponse>> {

                    override fun onResponse(
                        call: Call<List<GetSectionsResponse>>,
                        response: Response<List<GetSectionsResponse>>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("MyLog", response.body().toString())
                            intellectList = response.body()!!
                            binding!!.recyclerIntellectSection.apply {
                                adapterSections = SectionsAdapter(intellectList)
                                adapterSections.onItemClick = {
                                    showBottomSheet(it)
                                }
                                adapter = adapterSections
                                layoutManager =
                                    LinearLayoutManager(
                                        requireActivity(),
                                        LinearLayoutManager.HORIZONTAL,
                                        false
                                    )
                                binding!!.recyclerIntellectSection.visibility = View.VISIBLE
                                binding!!.progressIntellect.visibility = View.GONE

                            }

                        }
                        Log.d("MyLog", response.toString())
                    }

                    override fun onFailure(call: Call<List<GetSectionsResponse>>, t: Throwable) {
                        Log.d("MyLog", t.message.toString())
                        Toast.makeText(
                            activity,
                            "Возникла ошибка, проверьте подключение",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
            }
            "Творчество" -> {
                RetrofitHelper().getApi().getSectionsCategory(category).enqueue(object :
                    Callback<List<GetSectionsResponse>> {

                    override fun onResponse(
                        call: Call<List<GetSectionsResponse>>,
                        response: Response<List<GetSectionsResponse>>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("MyLog", response.body().toString())
                            tvoriList = response.body()!!
                            binding!!.recyclerTvoriSection.apply {
                                adapterSections = SectionsAdapter(tvoriList)
                                adapterSections.onItemClick = {
                                    showBottomSheet(it)
                                }
                                adapter = adapterSections
                                layoutManager =
                                    LinearLayoutManager(
                                        requireActivity(),
                                        LinearLayoutManager.HORIZONTAL,
                                        false
                                    )
                                binding!!.recyclerTvoriSection.visibility = View.VISIBLE
                                binding!!.progressTvori.visibility = View.GONE

                            }

                        }
                        Log.d("MyLog", response.toString())
                    }

                    override fun onFailure(call: Call<List<GetSectionsResponse>>, t: Throwable) {
                        Log.d("MyLog", t.message.toString())
                        Toast.makeText(
                            activity,
                            "Возникла ошибка, проверьте подключение",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
            }
            "Адаптивные" -> {
                RetrofitHelper().getApi().getSectionsCategory(category).enqueue(object :
                    Callback<List<GetSectionsResponse>> {

                    override fun onResponse(
                        call: Call<List<GetSectionsResponse>>,
                        response: Response<List<GetSectionsResponse>>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("MyLog", response.body().toString())
                            adaptiveList = response.body()!!
                            binding!!.recyclerAdaptiveSection.apply {
                                adapterSections = SectionsAdapter(adaptiveList)
                                adapterSections.onItemClick = {
                                    showBottomSheet(it)
                                }
                                adapter = adapterSections
                                layoutManager =
                                    LinearLayoutManager(
                                        requireActivity(),
                                        LinearLayoutManager.HORIZONTAL,
                                        false
                                    )
                                binding!!.recyclerAdaptiveSection.visibility = View.VISIBLE
                                binding!!.progressAdaptive.visibility = View.GONE

                            }

                        }
                        Log.d("MyLog", response.toString())
                    }

                    override fun onFailure(call: Call<List<GetSectionsResponse>>, t: Throwable) {
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

    private fun showBottomSheet(i: GetSectionsResponse){
        val bottomSheet = BottomSheetSection(i)
        val fragmentManager = (activity as FragmentActivity).supportFragmentManager
        fragmentManager.let {
            bottomSheet.show(it, BottomSheetGround.TAG)
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