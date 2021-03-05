package com.example.parcial_pregunta2;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

public class activityMapa extends AppCompatActivity implements OnMapReadyCallback {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adaptadormapa);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.idMap);
        mapFragment.getMapAsync(this);
        ImageView Pais= (ImageView)findViewById(R.id.idImgPais);
        TextView textoPais = (TextView)findViewById(R.id.txtNombrePais);
        textoPais.setText(getIntent().getStringExtra("nombre_pais"));
        Glide.with( this.getApplicationContext()).load(getIntent().getStringExtra("url_imagen")).into(Pais);
    }
    GoogleMap Gmap;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Gmap = googleMap;
        Gmap.getUiSettings().setZoomControlsEnabled(true);
        Gmap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        double [] lstCoord = getIntent().getDoubleArrayExtra("listaCooordenadas");
        CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(new LatLng(lstCoord[4],lstCoord[5]), 5);
        Gmap.moveCamera(camera);
        PolylineOptions Poligono = new PolylineOptions()
                .add(new LatLng(lstCoord[2], lstCoord[0]))
                .add(new LatLng(lstCoord[2], lstCoord[1]))
                .add(new LatLng(lstCoord[3], lstCoord[1]))
                .add(new LatLng(lstCoord[3], lstCoord[0]))
                .add(new LatLng(lstCoord[2], lstCoord[0]));
        Poligono.width(8);
        Poligono.color(Color.BLUE);
        Gmap.addPolyline(Poligono);
    }
}
