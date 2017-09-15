package com.example.kichi.exu1reposteria;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kichi on 15/9/2017.
 */

public class ReposDetailActivity extends AppCompatActivity {
    LinearLayout linearingredientes;
    public ClsCargarDatos cargarDatos;
    List<TextView> listaIngredientes;
    int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos_detail);

        Intent intent = getIntent();                                            //Obtenemos el intent
        Bundle extras = intent.getExtras();                                     //Obtenemos el conjunto de extras

        int numero = Integer.parseInt(extras.getString("ID"));
        //Obtenemos el extra ID
        cargarDatos = new ClsCargarDatos();
        ArrayList<String> plato = new ArrayList<>();
        listaIngredientes = new ArrayList<>();
        linearingredientes = (LinearLayout) this.findViewById(R.id.linearIngrediente);
        linearingredientes.setOrientation(LinearLayout.VERTICAL);


        ArrayList<String> ingredientes = new ArrayList<>();

        try {
            plato = cargarDatos.buscarDato(numero);
            ingredientes = cargarDatos.buscarIngrediente(numero);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Button btnLlamar = (Button) findViewById(R.id.btnLlamar);
        TextView txtNombre = (TextView) findViewById(R.id.txtNombre);
        TextView txtDescripcion = (TextView) findViewById(R.id.txtDescripcion);
        TextView txtPreparacion = (TextView) findViewById(R.id.txtPreparacion);
        byte[] decodedString = Base64.decode(plato.get(3), Base64.DEFAULT);
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);


        ImageView ivFoto = (ImageView) findViewById(R.id.foto);

        for (int i = 0; i < ingredientes.size(); i++) {
            TextView ingre = new TextView(this);
            listaIngredientes.add(ingre);
            cont++;
            ingre.setText(ingredientes.get(i));
            ingre.setTextSize(16);
            ingre.setTextColor(Color.BLACK);
            ingre.setTypeface(null, Typeface.BOLD);
            Log.d("View", "Start");
            try {
                linearingredientes.addView(ingre);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        txtNombre.setText(plato.get(0));
        txtDescripcion.setText(plato.get(1));
        txtPreparacion.setText(plato.get(2));
        ivFoto.setImageBitmap(decodedBitmap);

        btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                phoneIntent.setData(Uri.parse("tel:91-000-000-0000"));
                if (ActivityCompat.checkSelfPermission(ReposDetailActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(phoneIntent);
            }
        });


    }


}
