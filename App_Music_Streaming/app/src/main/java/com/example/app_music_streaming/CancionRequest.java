package com.example.app_music_streaming;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CancionRequest extends StringRequest {
    private static final String CANCION_REQUEST_URL="http://192.168.0.16/musicstreaming/Canciones.php";
    private Map<String,String> params;

    public CancionRequest(Response.Listener<String> listener)
    {
        super(Request.Method.POST, CANCION_REQUEST_URL, listener, null);

        params = new HashMap<String,String>();
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
