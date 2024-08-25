package ramble.sokol.residentardoftatarstan.presentation.fragments

import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ramble.sokol.residentardoftatarstan.R
import ramble.sokol.residentardoftatarstan.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        binding!!.onboardingButton.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.layout_fragment, OnBoardingFragment())
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
        binding!!.createFamily.setOnClickListener{
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.layout_fragment, InviteFragment())
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
    }

    fun handleNfcIntent(intent: Intent) {
        val action = intent.action
        if (NfcAdapter.ACTION_NDEF_DISCOVERED == action ||
            NfcAdapter.ACTION_TECH_DISCOVERED == action ||
            NfcAdapter.ACTION_TAG_DISCOVERED == action) {

            // Обработка данных из NFC метки
            val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
            // Ваш код для работы с меткой
            Log.d("MyLog", tag.toString())
        }
    }

}