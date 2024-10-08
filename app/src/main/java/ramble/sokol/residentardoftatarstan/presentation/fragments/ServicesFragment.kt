package ramble.sokol.residentardoftatarstan.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ramble.sokol.residentardoftatarstan.R
import ramble.sokol.residentardoftatarstan.databinding.FragmentServicesBinding

class ServicesFragment : Fragment() {

    private var binding: FragmentServicesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentServicesBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){

        binding!!.cardMap.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.layout_fragment, MapFragment())
            transaction.disallowAddToBackStack()
            transaction.commit()
        }

        binding!!.cardAfisha.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.layout_fragment, AfishaFragment())
            transaction.disallowAddToBackStack()
            transaction.commit()
        }

        binding!!.cardSection.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.layout_fragment, SectionFragment())
            transaction.disallowAddToBackStack()
            transaction.commit()
        }

        binding!!.cardStudent.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.layout_fragment, StudentFragment())
            transaction.disallowAddToBackStack()
            transaction.commit()
        }

        binding!!.cardOvz.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.layout_fragment, OvzFragment())
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
    }

}