package ramble.sokol.residentardoftatarstan.presentation.activity

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.os.Bundle
import android.os.PersistableBundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.yandex.mapkit.MapKitFactory
import ramble.sokol.residentardoftatarstan.BuildConfig
import ramble.sokol.residentardoftatarstan.R
import ramble.sokol.residentardoftatarstan.presentation.fragments.MainFragment
import ramble.sokol.residentardoftatarstan.presentation.fragments.SplashScreenFragment

class MainActivity : AppCompatActivity() {

    companion object {
        const val MAPKIT_API_KEY = BuildConfig.MAP_KEY
    }

    private lateinit var nfcAdapter: NfcAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setApiKey(savedInstanceState)
        MapKitFactory.initialize(this)

        setContentView(R.layout.activity_main)

        val splashScreenFragment = SplashScreenFragment()
        val fragment : Fragment? =

            supportFragmentManager.findFragmentByTag(SplashScreenFragment::class.java.simpleName)

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        // Проверка на наличие NFC
        if (nfcAdapter == null) {
            Toast.makeText(this, "NFC не поддерживается", Toast.LENGTH_SHORT).show()
            finish()
        }

        if (fragment !is SplashScreenFragment){
            supportFragmentManager.beginTransaction()
                .add(R.id.layout_fragment, splashScreenFragment, SplashScreenFragment::class.java.simpleName)
                .commit()
        }

    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        intent?.let {
            val fragment = supportFragmentManager.findFragmentById(R.id.layout_fragment) as? MainFragment
            fragment?.handleNfcIntent(it)
        }
    }

    override fun onResume() {
        super.onResume()
        // Настройка фильтра для NFC
        val intent = Intent(this, javaClass).apply {
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val filters = arrayOf(NfcAdapter.ACTION_NDEF_DISCOVERED)
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null)
    }

    override fun onPause() {
        super.onPause()
        nfcAdapter.disableForegroundDispatch(this)
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putBoolean("haveApiKey", true)
    }

    private fun setApiKey(savedInstanceState: Bundle?) {
        val haveApiKey = savedInstanceState?.getBoolean("haveApiKey") ?: false
        if (!haveApiKey) {
            MapKitFactory.setApiKey(MAPKIT_API_KEY)
        }
    }

}