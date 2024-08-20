package ramble.sokol.residentardoftatarstan.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ramble.sokol.residentardoftatarstan.R
import ramble.sokol.residentardoftatarstan.databinding.FragmentBottomNavigationBinding


class BottomNavigationFragment(
    val currentFragment: Fragment
) : Fragment() {

    private var binding: FragmentBottomNavigationBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBottomNavigationBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        replaceFragment(currentFragment)
        binding!!.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId){
                R.id.navbar_main -> replaceFragment(MainFragment())
                R.id.navbar_payment -> replaceFragment(PaymentFragment())
                R.id.navbar_services -> replaceFragment(ServicesFragment())
                R.id.navbar_promotions -> replaceFragment(PromotionsFragment())
                R.id.navbar_news -> replaceFragment(NewsFragment())
                else -> {}
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment){

        val fragmentManager = parentFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.frame_layout, fragment)
        fragmentTransition.commit()

    }

}