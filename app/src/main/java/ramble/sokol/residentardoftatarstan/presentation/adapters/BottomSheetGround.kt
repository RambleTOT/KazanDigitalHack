package ramble.sokol.residentardoftatarstan.presentation.adapters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso
import com.yandex.mapkit.geometry.Point
import ramble.sokol.residentardoftatarstan.presentation.fragments.CreateRoutesToPointFragment
import ramble.sokol.residentardoftatarstan.R
import ramble.sokol.residentardoftatarstan.data.model.GetGroundsResponse
import ramble.sokol.residentardoftatarstan.databinding.BottomSheetGroundBinding
import ramble.sokol.residentardoftatarstan.presentation.fragments.MapFragment
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
        if (binding!!.imageGround == null){
            binding!!.imageGround.setBackgroundResource(R.drawable.image_card)
        }else {
            Picasso.with(context).load(i.image).into(binding!!.imageGround)
        }
        binding!!.buttonBuyOrGround.setOnClickListener {
            onStop()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.layout_fragment, PayFragment(MapFragment(),i.price.toString()))
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
        binding!!.buttonCreateRoutes.setOnClickListener {
            onStop()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.layout_fragment, CreateRoutesToPointFragment(Point(i.latitude!!.toDouble(), i.longitude!!.toDouble()),MapFragment()))
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
    }

    companion object {
        const val TAG = "AddBottomSheetGround"
    }

}