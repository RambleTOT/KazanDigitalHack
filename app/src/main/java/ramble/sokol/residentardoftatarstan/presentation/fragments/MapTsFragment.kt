package ramble.sokol.residentardoftatarstan.presentation.fragments

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.location.LocationManager
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.runtime.image.ImageProvider
import ramble.sokol.residentardoftatarstan.EndtsFragment
import ramble.sokol.residentardoftatarstan.R
import ramble.sokol.residentardoftatarstan.data.model.GetGroundsResponse
import ramble.sokol.residentardoftatarstan.databinding.FragmentMapBinding
import ramble.sokol.residentardoftatarstan.databinding.FragmentMapTsBinding
import ramble.sokol.residentardoftatarstan.presentation.managers.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapTsFragment : Fragment() {

    private var binding: FragmentMapTsBinding? = null
    private lateinit var mapObjectCollection: MapObjectCollection
    private lateinit var placeMarkMapObject: PlacemarkMapObject
    private var zoomValue: Float = 15.5f
    private lateinit var locationManager: LocationManager
    private lateinit var startLocation: Point
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var listPoint: List<Point>
    private var placemark: PlacemarkMapObject? = null
    private lateinit var listData: List<GetGroundsResponse>

    companion object {
        private const val PERMISSION_REQUEST_ACCESS_LOCATION = 100
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMapTsBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getMyLocation()
    }

    private fun init(){
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    private fun moveToStartLocation() {
        Log.d("MyLog", "location")
        binding!!.mapView.map.move(
            CameraPosition(startLocation, zoomValue, 0.0f, 0.0f)
        )
    }

    private fun createBitmapFromVector(art: Int): Bitmap? {
        val drawable = ContextCompat.getDrawable(requireActivity(), art) ?: return null
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        ) ?: return null
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    private fun getMyLocation(){
        if (checkPermissions()){
            if (ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermission()
                return
            }
            fusedLocationProviderClient.lastLocation.addOnCompleteListener(requireActivity()){
                    task ->
                val location: Location? = task.result
                //startLocation = Point(location!!.latitude, location!!.longitude, )
                startLocation = Point(55.780213, 49.133444)
                addPlacemark(startLocation)

                moveToStartLocation()
                getGrounds()
            }
        }else{
            requestPermission()
        }
    }

    private fun addPlacemark(location: Point) {
        // Загрузите изображение из ресурсов
        val drawable: Drawable? = ContextCompat.getDrawable(requireActivity(), R.drawable.current_location)
        val bitmap = (drawable as BitmapDrawable).bitmap

        // Создайте placemark с пользовательским значком
        if (placemark == null) {
            placemark = binding!!.mapView.map.mapObjects.addPlacemark(location, ImageProvider.fromBitmap(bitmap))
        } else {
            placemark!!.geometry = location
        }
    }

    private fun requestPermission(){
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_REQUEST_ACCESS_LOCATION
        )
    }

    private fun checkPermissions(): Boolean{
        if (ActivityCompat.checkSelfPermission(requireActivity(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true
        }else{
            return false
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode== PERMISSION_REQUEST_ACCESS_LOCATION){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getMyLocation()
            }
        }
    }

    private fun getGrounds(){
        RetrofitHelper().getApi().getGrounds().enqueue(object :
            Callback<List<GetGroundsResponse>> {

            override fun onResponse(
                call: Call<List<GetGroundsResponse>>,
                response: Response<List<GetGroundsResponse>>
            ) {
                if (response.isSuccessful){
                    listData = response.body()!!
                    Log.d("MyLog", listData.toString())
                    setMarkerInStartLocation()
                }
                Log.d("MyLog", response.toString())
            }

            override fun onFailure(call: Call<List<GetGroundsResponse>>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
                Toast.makeText(activity, "Возникла ошибка, проверьте подключение", Toast.LENGTH_SHORT).show()
            }

        })
    }
    private fun setMarkerInStartLocation() {
        val marker = createBitmapFromVector(R.drawable.icon_map)
        mapObjectCollection = binding!!.mapView.map.mapObjects

        for (i in listData){
            val p = Point(i.latitude!!.toDouble(), i.longitude!!.toDouble())
            placeMarkMapObject = mapObjectCollection.addPlacemark(p, ImageProvider.fromBitmap(marker))
            Log.d("MyLog", "listData $i")
            placeMarkMapObject.addTapListener { _, _ ->
                Log.d("MyLog", "click $i")
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.layout_fragment, EndtsFragment())
                transaction.disallowAddToBackStack()
                transaction.commit()
                true
            }
        }
    }

    override fun onStart() {
        super.onStart()
        binding!!.mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        binding!!.mapView.onStop()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.layout_fragment, OvzFragment())
                transaction.disallowAddToBackStack()
                transaction.commit()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

}