package br.edu.ifpr.jonatas.maps

import android.Manifest
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class MainActivity : AppCompatActivity() {

    lateinit var map : GoogleMap
    lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadMap()


    }

    fun loadMap() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync {map ->
            this.map = map
            map.uiSettings.isZoomControlsEnabled = true

            checkPermission()

        }
    }

    fun moveCamera(latitude : Double, longitude : Double) {
        val local = LatLng(latitude, longitude)
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(local, 15f)
        map.moveCamera(cameraUpdate)
    }

    fun addMarker(latitude : Double, longitude : Double, title : String? = null) {
        val local = LatLng(latitude, longitude)
        map.addMarker(MarkerOptions().position(local).title(title))
    }

    fun checkPermission() {
        Dexter.withActivity(this)
            .withPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    if(report!!.areAllPermissionsGranted())
                        trackLocation()

                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) { }

            })
            .check()
    }

    @SuppressLint("MissingPermission")
    fun trackLocation() {
        map.isMyLocationEnabled = true
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            var latitude = location.latitude
            var longitude = location.longitude
            moveCamera(latitude, longitude )
            addMarker(latitude, longitude)
        }

        val locationRequest = LocationRequest.create()?.apply {
            interval = 2000
            fastestInterval = 1000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        fusedLocationClient.requestLocationUpdates(locationRequest, object : LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult?) {
                //super.onLocationResult(locationResult)
                val location = locationResult!!.lastLocation
                var latitude = location.latitude
                var longitude = location.longitude
                moveCamera(latitude, longitude )
                addMarker(latitude, longitude)
            }
        },null)
    }
}
