package com.example.myl4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myl4.databinding.ActivityProfileActiviy2Binding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActiviy2 extends AppCompatActivity {

    //View binding
    private ActivityProfileActiviy2Binding binding;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProfileActiviy2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //inicia firebase auth

        firebaseAuth = FirebaseAuth.getInstance();
        checkUsers();
        //handle click, logout
        binding.BtnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                checkUsers();


            }
        });

    }

    private void checkUsers() {
        //obtener usuario actual
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser == null){
            //usuario no logeado
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }else{
            //Usuario logeado
            //obtener informacion de usuario Email y Nombre son seteados en pantalla de Inicio
            String email = firebaseUser.getEmail();
            String name = firebaseUser.getDisplayName();
            //set email
            binding.TvNombre.setText(name);
            binding.TvEmail.setText(email);

        }

    }
}