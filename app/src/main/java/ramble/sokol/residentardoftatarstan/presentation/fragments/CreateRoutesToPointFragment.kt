package ramble.sokol.residentardoftatarstan.presentation.fragments

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.geometry.Polyline
import com.yandex.mapkit.location.LocationManager
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectCollection
import ramble.sokol.residentardoftatarstan.R
import ramble.sokol.residentardoftatarstan.databinding.FragmentCreateRoutesToPointBinding

class CreateRoutesToPointFragment(
    val end: Point,
    val fragment: Fragment
) : Fragment() {

    private var binding: FragmentCreateRoutesToPointBinding? = null
    private var zoomValue: Float = 15.5f
    private lateinit var locationManager: LocationManager
    private lateinit var startLocation: Point
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var listPoint: List<Point>


    companion object {
        private const val PERMISSION_REQUEST_ACCESS_LOCATION = 100
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateRoutesToPointBinding.inflate(inflater, container, false)
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
        drawRoute(startLocation, end)
    }

    private fun drawRoute(startPoint: Point, endPoint: Point) {
        val mapObjects: MapObjectCollection = binding!!.mapView.map.mapObjects.addCollection()

        // Создаем список точек для линии
        val points = listOf(startPoint, endPoint)

        // Добавляем полилинию на карту
        mapObjects.addPolyline(Polyline(points))
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
                moveToStartLocation()
            }
        }else{
            requestPermission()
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
                transaction.replace(R.id.layout_fragment, fragment)
                transaction.disallowAddToBackStack()
                transaction.commit()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

}