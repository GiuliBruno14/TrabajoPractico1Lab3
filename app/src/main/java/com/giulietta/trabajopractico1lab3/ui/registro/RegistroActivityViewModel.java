package com.giulietta.trabajopractico1lab3.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.giulietta.trabajopractico1lab3.model.Usuario;
import com.giulietta.trabajopractico1lab3.request.ApliClient;
import com.giulietta.trabajopractico1lab3.ui.login.MainActivity;

public class RegistroActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> mUsuario;

    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Usuario> getMUsuario(){
        if(mUsuario == null){
            mUsuario = new MutableLiveData<>();

        }
        return mUsuario;
    }

    public void leerDatos(boolean bool){
        Usuario usuario = ApliClient.leer(context);
        if(usuario.getDni().equals("-1") || !bool){
            mUsuario.setValue(new Usuario());
        } else {
            mUsuario.setValue(ApliClient.leer(context));
        }
    }

    public void editar(Usuario usuario){
        if(usuario.getDni().isEmpty() || usuario.getApellido().isEmpty() || usuario.getNombre().isEmpty() || usuario.getMail().isEmpty() ||usuario.getPassword().isEmpty()){
            Toast.makeText(context, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            ApliClient.guardar(context, usuario);
            Toast.makeText(context, "Usuario actualizado correctamente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }


}
