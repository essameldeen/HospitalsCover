package com.example.essam.hospitalscover.ModelView;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class GoogleMapsNavigation /*implements LocationListener*/ {
    private Intent googleMapsIntent;
  //  private LocationManager locationManager;
   // private Context context;
  //  private android.location.Location lastLocation;

    public GoogleMapsNavigation(Context context, Double orignLat, Double orignLon,Double destinationLat,Double destinationLon)
    {
      //  this.context=context;
        googleMapsIntent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse(constructDrivingURL(  orignLat,  orignLon,destinationLat,destinationLon)));
    }
   public String constructDrivingURL(Double orignLat, Double orignLon,Double destinationLat,Double destinationLon)
    {
        String origin=getCurrentLocationURL( orignLat,  orignLon);
        String destination=String.valueOf(destinationLat)+","+String.valueOf(destinationLon);
        String drivingURL="https://www.google.com/maps/dir/?api=1&origin="+origin+"&destination="+destination+"&travelmode=driving";
        return drivingURL;
    }
   public String getCurrentLocationURL(Double orignLat, Double orignLon)
    {
        return (String.valueOf(orignLat)+","+String.valueOf(orignLon));
    }
    public Intent getGoogleMapsIntentAfterInit()
    {
        return googleMapsIntent;
    }
/*
    public void updateLocation() {
        try {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

            lastLocation= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(lastLocation==null)
            {
                lastLocation=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
       lastLocation=location;
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }*/


}
