package ramble.sokol.residentardoftatarstan.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import ramble.sokol.residentardoftatarstan.R
import ramble.sokol.residentardoftatarstan.databinding.FragmentOnBoardingBinding
import ramble.sokol.residentardoftatarstan.databinding.FragmentPayBinding

class PayFragment(
    val fragment: Fragment,
    val price: String
) : Fragment() {

    private var binding: FragmentPayBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPayBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        binding!!.sumPay.text = "${price}₽"
        binding!!.payOnline.setOnClickListener {
            binding!!.payOnline.visibility = View.INVISIBLE
            binding!!.progressPay.visibility = View.VISIBLE
            Handler().postDelayed(Runnable {
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                val bottomNavigationFragment = BottomNavigationFragment(ServicesFragment())
                transaction.replace(R.id.layout_fragment, bottomNavigationFragment)
                transaction.disallowAddToBackStack()
                transaction.commit()
                Toast.makeText(activity, "Оплата прошла успешно", Toast.LENGTH_SHORT).show()
            }, 1500)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.layout_fragment, fragment)
                transaction.disallowAddToBackStack()
                transaction.commit()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

}