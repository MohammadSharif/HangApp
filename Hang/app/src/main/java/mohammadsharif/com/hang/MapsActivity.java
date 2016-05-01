package mohammadsharif.com.hang;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Timer;
import java.util.TimerTask;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    static MarkerOptions[] options = new MarkerOptions[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap map)
    {
        TimerHelper timer = new TimerHelper();
        Thread timerThread = (new Thread(timer));
        timerThread.start();

        options[0] = new MarkerOptions()
                .position(new LatLng(34.063048, -118.448068))
                .title("Jenny G. wants to eat at In N' Out. Join her?");
        options[1] = new MarkerOptions()
                .position(new LatLng(34.070058, -118.448987))
                .title("Bill G. wants to play tennis. Join him?");
        options[2] = new MarkerOptions()
                .position(new LatLng(34.071997, -118.451565))
                .title("Donald T. wants to Netflix & Chill. Join him?");
        options[3] = new MarkerOptions()
                .position(new LatLng(34.059472, -118.443571))
                .title("Arman H. wants to visit the museum. Join him?");
        options[4] = new MarkerOptions()
                .position(new LatLng(34.074336, -118.451191))
                .title("Michelle P. wants to go swimming. Join her?");
        options[5] = new MarkerOptions()
                .position(new LatLng(34.062842, -118.443984))
                .title("Dwight S. wants to go people watching. Join him?");

        map.addMarker(options[0]);
        map.addMarker(options[1]);
        map.addMarker(options[2]);
        map.addMarker(options[3]);
        map.addMarker(options[4]);
        map.addMarker(options[5]);
        map.addMarker(new MarkerOptions()
                .position(new LatLng(34.070562, -118.446891))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .title("This is you. Want to search for pals?"));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(34.070562, -118.446891))      // Sets the center of the map to Your Location
                .zoom(15)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
}
