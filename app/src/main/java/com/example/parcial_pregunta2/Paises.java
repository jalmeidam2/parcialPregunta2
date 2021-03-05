package com.example.parcial_pregunta2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Paises {

    private String nombre;
    private String url;
    private double [] coordenadas;
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Paises(String npais, String nurl, double [] ncoordenadasPais) throws JSONException {
        nombre = npais;
        url = nurl;
        coordenadas =ncoordenadasPais;
    }
    public static ArrayList<Paises> JsonObjectsBuild(JSONObject datos) throws JSONException {
        ArrayList<Paises> paises = new ArrayList<>();
        JSONObject results=datos.getJSONObject("Results");
        JSONArray namesBD=results.names();
        for (int i = 0; i < namesBD.length(); i++) {
            String nobres = namesBD.getString(i);
            JSONObject objetosBD =results.getJSONObject(nobres);
            String nombrePais= objetosBD.getString("Name");
            JSONObject coidgoCiudad = objetosBD.getJSONObject("CountryCodes");
            String iso2= coidgoCiudad.getString("iso2");
            JSONObject rectanguloPoligono = objetosBD.getJSONObject("GeoRectangle");
            JSONArray geopt= objetosBD.getJSONArray("GeoPt");
            double [] datosRectangulo = new double[6];
            datosRectangulo[0]= rectanguloPoligono.getDouble("West");
            datosRectangulo[1]= rectanguloPoligono.getDouble("East");
            datosRectangulo[2]= rectanguloPoligono.getDouble("North");
            datosRectangulo[3]= rectanguloPoligono.getDouble("South");
            datosRectangulo[4]=geopt.getDouble(0);
            datosRectangulo[5]=geopt.getDouble(1);
            paises.add(new com.example.parcial_pregunta2.Paises(nombrePais,"http://www.geognos.com/api/en/countries/flag/"+iso2+".png",datosRectangulo));
        }
        return paises;
    }
    public double [] getCoordenadas() {
        return coordenadas;
    }
    public void setCoordenadas(double [] coordenadas) {
        this.coordenadas = coordenadas;
    }
}
