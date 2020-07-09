package com.example.app_music_streaming;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegistrarUsuario extends StringRequest{
    private static final String REGISTER_REQUEST_URL="http://192.168.0.16/musicstreaming/Register.php";
    private Map<String,String> params;

    public RegistrarUsuario(String nombreUsu, String correoUsu, String nickUsu, String password, Response.Listener<String> listener)
    {
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("nombreUsuario", nombreUsu);
        params.put("correoUsuario", correoUsu);
        params.put("nickUsuario", nickUsu);
        params.put("contraUsuario", password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
