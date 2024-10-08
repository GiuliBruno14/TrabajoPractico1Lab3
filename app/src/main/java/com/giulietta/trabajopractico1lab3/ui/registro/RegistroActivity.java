package com.giulietta.trabajopractico1lab3.ui.registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.giulietta.trabajopractico1lab3.R;
import com.giulietta.trabajopractico1lab3.databinding.ActivityMainBinding;
import com.giulietta.trabajopractico1lab3.databinding.ActivityRegistroBinding;
import com.giulietta.trabajopractico1lab3.model.Usuario;
import com.giulietta.trabajopractico1lab3.ui.login.MainActivityViewModel;

public class RegistroActivity extends AppCompatActivity {
    public RegistroActivityViewModel mv;
    public ActivityRegistroBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mv= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mv.getMUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.etDni.setText(usuario.getDni());
                binding.etApellido.setText(usuario.getApellido());
                binding.etNombre.setText(usuario.getNombre());
                binding.etMail.setText(usuario.getMail());
                binding.etPassword.setText(usuario.getPassword());

            }
        });
        Intent intent = getIntent();
        boolean bool = intent.getBooleanExtra("login", false);
        mv.leerDatos(bool);
        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dni = binding.etDni.getText().toString();
                String apellido = binding.etApellido.getText().toString();
                String nombre = binding.etNombre.getText().toString();
                String mail = binding.etMail.getText().toString();
                String password = binding.etPassword.getText().toString();
                mv.editar(new Usuario(dni,apellido, nombre, mail, password));
            }
        });
    }
}