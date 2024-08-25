package ramble.sokol.residentardoftatarstan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ramble.sokol.residentardoftatarstan.databinding.FragmentEndTestBinding
import ramble.sokol.residentardoftatarstan.databinding.FragmentEndtsBinding
import ramble.sokol.residentardoftatarstan.presentation.fragments.BottomNavigationFragment
import ramble.sokol.residentardoftatarstan.presentation.fragments.MainFragment

class EndtsFragment : Fragment() {

    private var binding: FragmentEndtsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEndtsBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        binding!!.buttonToMainTest.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.layout_fragment, BottomNavigationFragment(MainFragment()))
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
    }

}