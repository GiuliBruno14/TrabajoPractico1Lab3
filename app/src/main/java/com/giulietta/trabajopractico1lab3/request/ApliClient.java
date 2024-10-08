package com.giulietta.trabajopractico1lab3.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.giulietta.trabajopractico1lab3.model.Usuario;

public class ApliClient {
    private static SharedPreferences sp;

    private static SharedPreferences conectar (Context context){
        if(sp == null){
            sp = context.getSharedPreferences("datos",0);

        }
        return sp;
    }

    public static void guardar(Context context, Usuario usuario){
        SharedPreferences shared = conectar(context);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("DNI", usuario.getDni());
        editor.putString("Apellido", usuario.getApellido());
        editor.putString("Nombre", usuario.getNombre());
        editor.putString("Mail", usuario.getMail());
        editor.putString("Password", usuario.getPassword());
        editor.commit();
    }

    public static Usuario leer(Context context){
        SharedPreferences shared = conectar(context);
        String dni = shared.getString("DNI","-1");
        String apellido = shared.getString("Apellido", "-1");
        String nombre = shared.getString("Nombre", "-1");
        String mail = shared.getString("Mail","-1");
        String password = shared.getString("Password","-1");
        return new Usuario(dni, apellido, nombre, mail, password);
    }

    public static Usuario login(Context context, String mail, String password){
        Usuario usuario = null;
        SharedPreferences shared = conectar(context);
        String dni = shared.getString("DNI","-1");
        String apellido = shared.getString("Apellido", "-1");
        String nombre = shared.getString("Nombre", "-1");
        String mail2 = shared.getString("Mail","-1");
        String password2 = shared.getString("Password","-1");
        if(mail2.equals(mail) && password2.equals(password)){
            usuario = new Usuario(dni, apellido, nombre, mail2, password2);
        }
        return usuario;
    }
}
