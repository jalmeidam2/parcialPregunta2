package com.example.parcial_pregunta2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask, AdapterView.OnItemClickListener {
    ArrayList<Paises> PaisesLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Map<String, String> datosMap = new HashMap<String, String>();
        WebService ws= new WebService("http://www.geognos.com/api/en/countries/info/all.json", datosMap, this, this);
        ws.execute("");
        ListView opciones =(ListView)findViewById(R.id.idPaises);
        opciones.setOnItemClickListener(this);
        getPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        getPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
    }
    @Override
    public void processFinish(String result) throws JSONException {
        Log.i("processFinish", result);
        JSONObject pais=  new JSONObject(result);
        PaisesLista =Paises.JsonObjectsBuild(pais);
        AdaptadorPaises adaptadorArticulos = new AdaptadorPaises(this, PaisesLista);
        ListView listaOpciones =(ListView)findViewById(R.id.idPaises);
        listaOpciones.setAdapter(adaptadorArticulos);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent (view.getContext(), activityMapa.class);
        intent.putExtra("listaCooordenadas", PaisesLista.get(position).getCoordenadas());
        intent.putExtra("url_imagen", PaisesLista.get(position).getUrl());
        intent.putExtra("nombre_pais", PaisesLista.get(position).getNombre());

        startActivityForResult(intent, 0);
    }
    public void getPermission(String permission){
        if (Build.VERSION.SDK_INT >= 23) {
            if (!(checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED))
                ActivityCompat.requestPermissions(this, new String[]{permission}, 1);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[]
            permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1)
        {
            Toast.makeText(this.getApplicationContext(),"OK", Toast.LENGTH_LONG).show();
        }
    }
}
