package com.example.kichi.exu1reposteria;

import android.graphics.Bitmap;

/**
 * Created by Kichi on 15/9/2017.
 */

public class Lista_entrada {

    private String idRepo;
    private String textoEncima;
    private String textoDebajo;
    private Bitmap foto;

    public Lista_entrada (String IdRepo,Bitmap foto,String textoEncima, String textoDebajo) {
        this.foto = foto;
        this.idRepo = IdRepo;
        this.textoEncima = textoEncima;
        this.textoDebajo = textoDebajo;
    }

    public String get_textoEncima() {
        return textoEncima;
    }

    public String get_textoDebajo() {
        return textoDebajo;
    }

    public Bitmap get_Foto() {
        return foto;
    }

    public String get_IdRepo() {
        return idRepo;
    }
}
