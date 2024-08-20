package ramble.sokol.residentardoftatarstan.presentation.adapters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.transition.Visibility
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ramble.sokol.residentardoftatarstan.R
import ramble.sokol.residentardoftatarstan.data.model.GetGroundsResponse
import ramble.sokol.residentardoftatarstan.databinding.BottomSheetGroundBinding
import ramble.sokol.residentardoftatarstan.presentation.fragments.BottomNavigationFragment
import ramble.sokol.residentardoftatarstan.presentation.fragments.MainFragment
import ramble.sokol.residentardoftatarstan.presentation.fragments.PayFragment

class BottomSheetGround(val i: GetGroundsResponse) : BottomSheetDialogFragment() {

    private var binding: BottomSheetGroundBinding? = null

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetGroundBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MyLog", i.name.toString())
        binding!!.nameGround.text = i.name
        binding!!.descriptionGround.text = i.description
        binding!!.ratingGroundText.text = i.rating
        binding!!.priceGroundText.text = if (i.price == "0") "Бесплатно" else "от ${i.price} ₽"
        if (i.price == "0") binding!!.buttonBuyOrGround.visibility =  View.GONE
        binding!!.dateAddressGround.text = "${i.timetable} · ${i.address}"
        binding!!.buttonBuyOrGround.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.layout_fragment, PayFragment(i.price.toString()))
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
    }

    companion object {
        const val TAG = "AddBottomSheetGround"
    }

}