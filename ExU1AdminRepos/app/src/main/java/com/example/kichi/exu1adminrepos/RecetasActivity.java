package com.example.kichi.exu1adminrepos;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.Toast;

public class RecetasActivity extends AppCompatActivity {

   // boolean click = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetas);

        final FloatingActionButton frgReceta = (FloatingActionButton) findViewById(R.id.agregar_receta);
        frgReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.layoutReceta);

                if (currentFragment == null) {

                    /*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        Interpolator interpolador = AnimationUtils.loadInterpolator(getBaseContext(),
                                android.R.interpolator.fast_out_slow_in);

                        v.animate()
                                .rotation(click ? 45f : 0)
                                .setInterpolator(interpolador)
                                .start();

                    }*/

                    frgReceta.setBackgroundTintList(ColorStateList.valueOf(Color.RED));

                    //Paso 1: Obtener la instancia del administrador de fragmentos
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    //Paso 2: Crear una nueva transacción
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    //Paso 3: Crear un nuevo fragmento y añadirlo
                    RecetaFragment fragmentReceta = new RecetaFragment();
                    transaction.add(R.id.layoutReceta,fragmentReceta);
                    transaction.addToBackStack(null);
                    //Paso 4: Confirmar el cambio
                    transaction.commit();
                }else{
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.popBackStack();
                    frgReceta.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
                }
            }
        });

    }

    ClsConexion conexion = new ClsConexion();

}
