package ramble.sokol.residentardoftatarstan.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import ramble.sokol.residentardoftatarstan.R

class InviteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_invite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.layout_fragment, BottomNavigationFragment(MainFragment()))
                transaction.disallowAddToBackStack()
                transaction.commit()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

}