package com.example.app_music_streaming;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainRegistro extends AppCompatActivity {

    Button btnCrearUsu,btnCancelar;
    EditText txtNombreUsu,txtCorreoUsu, txtNickUsu, txtContraUsua, txtVerificaUsu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registro);

        btnCrearUsu = (Button) findViewById(R.id.btnCrearUsu);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        txtNombreUsu = (EditText) findViewById(R.id.txtNombreUsu);
        txtCorreoUsu = (EditText) findViewById(R.id.txtCorreoUsu);
        txtNickUsu = (EditText) findViewById(R.id.txtNickUsu);
        txtContraUsua = (EditText) findViewById(R.id.txtContraUsu);
        txtVerificaUsu = (EditText) findViewById(R.id.txtVerificaContra);

        btnCrearUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nombreUsu= txtNombreUsu.getText().toString();
                final String correoUsu= txtCorreoUsu.getText().toString();
                final String password= txtContraUsua.getText().toString();
                final String verificaContra= txtVerificaUsu.getText().toString();
                final String nickUsu= txtNickUsu.getText().toString();
                if(password.equals(verificaContra)){
                    Response.Listener<String> respoListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean succes = jsonResponse.getBoolean("succes");

                                if(succes)
                                {
                                    Intent intent= new Intent(MainRegistro.this, MainActivity.class);
                                    startActivity(intent);
                                }
                                else{
                                    AlertDialog.Builder builder = new AlertDialog.Builder(MainRegistro.this);
                                    builder.setMessage("Error de Registro")
                                            .setNegativeButton("Reintentar", null)
                                            .create().show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    };

                    RegistrarUsuario registrarUsuario = new RegistrarUsuario(nombreUsu,correoUsu, nickUsu, password, respoListener);
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    queue.add(registrarUsuario);
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainRegistro.this);
                    builder.setMessage("Contraseñas no coinciden")
                            .setNegativeButton("Retry", null)
                            .create().show();
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainRegistro.this, MainActivity.class));
            }
        });
    }
}