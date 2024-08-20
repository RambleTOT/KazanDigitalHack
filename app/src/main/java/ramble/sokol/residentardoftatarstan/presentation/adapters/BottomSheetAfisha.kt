package ramble.sokol.residentardoftatarstan.presentation.adapters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yandex.mapkit.geometry.Point
import ramble.sokol.residentardoftatarstan.presentation.fragments.CreateRoutesToPointFragment
import ramble.sokol.residentardoftatarstan.R
import ramble.sokol.residentardoftatarstan.data.model.GetEventsResponse
import ramble.sokol.residentardoftatarstan.data.model.GetGroundsResponse
import ramble.sokol.residentardoftatarstan.databinding.BottomSheetEventBinding
import ramble.sokol.residentardoftatarstan.databinding.BottomSheetGroundBinding
import ramble.sokol.residentardoftatarstan.presentation.fragments.AfishaFragment
import ramble.sokol.residentardoftatarstan.presentation.fragments.MapFragment
import ramble.sokol.residentardoftatarstan.presentation.fragments.PayFragment
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BottomSheetAfisha(val i: GetEventsResponse) : BottomSheetDialogFragment() {

    private var binding: BottomSheetEventBinding? = null

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetEventBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MyLog", i.name.toString())
        val beginningDateTime = parseDateTime(i.beginningAt.toString())
        binding!!.nameEvent.text = i.name
        binding!!.descriptionEvent.text = i.description
        binding!!.dateAddressEvent.text = "${i.address} · ${formatDateTime(beginningDateTime)}"
        binding!!.priceEventText.text = if (i.price == "0") "Бесплатно" else "от ${i.price} ₽"
        if (i.price == "0") binding!!.buttonBuyOrGround.visibility =  View.GONE
        binding!!.buttonBuyOrGround.setOnClickListener {
            onStop()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.layout_fragment, PayFragment(AfishaFragment(),i.price.toString()))
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
    }

    companion object {
        const val TAG = "AddBottomSheetAfisha"
    }

    fun formatDateTime(dateTime: LocalDateTime): String {
        val date = dateTime.format(DateTimeFormatter.ofPattern("dd MMMM"))
        return "$date"
    }

    fun parseDateTime(dateTimeString: String): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        return LocalDateTime.parse(dateTimeString, formatter)
    }

}