package com.example.kichi.exu1adminrepos;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class RecetaFragment extends  android.support.v4.app.Fragment {


    public ClsConexion con;
    ImageButton btnfotoReceta;
    Button btnagregarIngrediente,btnguardar;
    LinearLayout linearingredientes;
    EditText ingrediente,txtnombre,txtdescripcion,txtpreparacion;
    private static final int CAMERA_PIC_REQUEST = 1;
    String encodedImage = null;
    private View view;
    int cont = 0;


    public RecetaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_receta, container, false);

        btnagregarIngrediente = (Button)view.findViewById(R.id.btnAgregarIngrediente);
        linearingredientes = (LinearLayout) view.findViewById(R.id.linearRepos);
        linearingredientes.setOrientation(LinearLayout.VERTICAL);
        btnguardar = (Button)view.findViewById(R.id.btnGuardar);

        configImageButton();
        configAgregarIngredientes();

        mtdGuardarReceta();
        return view;
    }


    private void configAgregarIngredientes() {

        ingrediente = (EditText)view.findViewById(R.id.txtIngrediente);
        btnagregarIngrediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                TextView ingre = new TextView(getActivity());
                cont++;
                ingre.setId(cont);
                ingre.setText(ingrediente.getText());
                ingre.setTextSize(16);
                ingre.setTextColor(Color.BLACK);
                ingre.setTypeface(null, Typeface.BOLD);
                Log.d("View","Start");
                try {
                    linearingredientes.addView(ingre);
                    ingrediente.setText("");
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    private void configImageButton(){
        btnfotoReceta = (ImageButton)view.findViewById(R.id.imgFotoReceta);
        btnfotoReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra("android.intent.extras.CAMERA_FACING_BACK", 1);
                startActivityForResult(cameraIntent,CAMERA_PIC_REQUEST);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap thumbnail = null;
        if (requestCode == CAMERA_PIC_REQUEST) {
            thumbnail = (Bitmap) data.getExtras().get("data");
            btnfotoReceta.setImageBitmap(thumbnail);
        }
        //Convertir bitmap en Base64 para enviarlo a BD
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG,100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        encodedImage = Base64.encodeToString(byteArray,Base64.DEFAULT);
    }



    private void mtdGuardarReceta() {

        txtnombre = (EditText)view.findViewById(R.id.txtNombre);
        txtdescripcion = (EditText)view.findViewById(R.id.txtDescripcion);
        txtpreparacion = (EditText)view.findViewById(R.id.txtPreparacion);
        final String dato1 = txtnombre.getText().toString();
        final String dato2 = txtdescripcion.getText().toString();
        final String dato3  =txtpreparacion.getText().toString();

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mensaje ="";

                con = new ClsConexion();

                String cadenaSql = "insert into Tb_Recetas values (?,?,?,?)";
                try{
                    Connection connection = con.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(cadenaSql);
                    preparedStatement.setString(1,dato1);
                    preparedStatement.setString(2,dato2);
                    preparedStatement.setString(3,dato3);
                    preparedStatement.setString(4,encodedImage);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    connection.close();
                    mensaje = "Resgistro correcto.";
                    Toast.makeText(view.getContext(),mensaje,Toast.LENGTH_LONG).show();
                } catch (SQLException e) {
                    e.printStackTrace();
                    mensaje = "Error al registrar.";
                    Toast.makeText(view.getContext(),mensaje,Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
