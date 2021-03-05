package com.example.parcial_pregunta2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdaptadorPaises extends ArrayAdapter<Paises> {
    public AdaptadorPaises(Context context, ArrayList<Paises> datos) {
        super(context, R.layout.adaptadorpaises, datos);
    }
    @SuppressLint("SetTextI18n")
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        @SuppressLint({"ViewHolder", "InflateParams"}) View item = inflater.inflate(R.layout.adaptadorpaises, null);
        ImageView Pais = (ImageView)item.findViewById(R.id.idIMGpais);
        TextView ViewPais = (TextView)item.findViewById(R.id.idTextoPais);
        ViewPais.setText("País:"+getItem(position).getNombre());
        Glide.with(this.getContext()).load(getItem(position).getUrl()).into(Pais);
        return(item);
    }
}

