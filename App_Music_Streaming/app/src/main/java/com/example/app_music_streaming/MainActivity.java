package com.example.app_music_streaming;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText txtNickUsu,txtContraUsu;
    Button btnIniciar, btnCrear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIniciar = (Button) findViewById(R.id.btnIniciar);
        btnCrear = (Button) findViewById(R.id.btnCrear);
        txtNickUsu = (EditText)findViewById(R.id.txtUsuario);
        txtContraUsu = (EditText)findViewById(R.id.txtContraseña);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nickUsuario = txtNickUsu.getText().toString();
                final String contraUsu = txtContraUsu.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("mensaje solicitud", nickUsuario + " - " + contraUsu );
                        Log.e("response", response);
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                String nickUsu =  jsonResponse.getString("nickUsuario");
                                String contraUsu =  jsonResponse.getString("contraUsuario");

                                Intent intent = new Intent(MainActivity.this, MainStreaming.class);
                                startActivity(new Intent(MainActivity.this, MainStreaming.class));

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("Usuario no registrado")
                                        .setNegativeButton("Reintentar", null)
                                        .create().show();
                            }
                        }catch (JSONException e ){
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(nickUsuario, contraUsu, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
            }
        });

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainRegistro.class));
            }
        });
    }


}