package com.example.kichi.exu1reposteria;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {

    private ListView lista;
    public ClsCargarDatos cargardatos;
    ArrayList<String> listaReposteria = null;
    public int COD = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        cargardatos = new ClsCargarDatos();
        listaReposteria = new ArrayList<>();

        try {
            listaReposteria = cargardatos.cargarRepos();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<Lista_entrada> datos = new ArrayList<>();


        for (int o = 0; o <= listaReposteria.size() - 5; o += 5) {

            String idRepo = listaReposteria.get(o);
            byte[] decodedString = Base64.decode(listaReposteria.get(o+4),Base64.DEFAULT);
            Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString,0,decodedString.length);
            String Nombre =  listaReposteria.get(o + 1);
            String Descripcion =  listaReposteria.get(o + 2);

            datos.add(new Lista_entrada(idRepo,decodedBitmap,Nombre,Descripcion));
        }
        lista = (ListView) findViewById(R.id.lista_repostera);
        lista.setAdapter(new Lista_adaptador(this, R.layout.fila_reposteria, datos){
            @Override
            public void onEntrada(Object entrada, View view) {

               TextView texto_ID = (TextView)view.findViewById(R.id.txtID);
                texto_ID.setText(((Lista_entrada)entrada).get_IdRepo());

                TextView texto_superior_entrada = (TextView) view.findViewById(R.id.nombre_reposteria);
                texto_superior_entrada.setText(((Lista_entrada)entrada).get_textoEncima());

                TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.descripcion_reposteria);
                texto_inferior_entrada.setText(((Lista_entrada)entrada).get_textoDebajo());


                ImageView imagen_entrada = (ImageView)view.findViewById(R.id.foto_reposteria);
                if(imagen_entrada != null) {
                    imagen_entrada.setImageBitmap(((Lista_entrada)entrada).get_Foto());
                }
               }
        });
        this.lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
                Lista_entrada elegido = (Lista_entrada)pariente.getItemAtPosition(posicion);
                String texto = elegido.get_textoEncima();
                Toast toast = Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_SHORT);
                toast.show();

                Intent reposDetail = new Intent(view.getContext(), ReposDetailActivity.class);
                // 3
                reposDetail.putExtra("ID", elegido.get_IdRepo());
                // 4
                startActivity(reposDetail);
            }
        });



    }
}

