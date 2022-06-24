package dev.khor.customfood.login_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dev.khor.customfood.DataContainer;
import dev.khor.customfood.principal_activity.MainActivity;
import dev.khor.customfood.R;
import dev.khor.customfood.user_register_activity.UserRegisterActivity;
import dev.khor.customfood.models.User;
import dev.khor.customfood.network.NetworkManager;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private NetworkListener networkListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        networkListener = new NetworkListener();
    }

    public void log_in(View view) {
        EditText em = findViewById(R.id.editTextTextPersonName);
        EditText pass = findViewById(R.id.editTextTextPassword);
        String email = em.getText().toString();
        String password = pass.getText().toString();

        Map<String, Object> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);

        NetworkManager.getInstance(this).login(
                networkListener.listenerResponse,
                networkListener.listenerError,
                body
        );
    }


    public void changeToRegister(View view) {
        startActivity(new Intent(LoginActivity.this,
                UserRegisterActivity.class));
    }

    public void changeToMenu() {
        startActivity(new Intent(LoginActivity.this,
                MainActivity.class));
    }

    private class NetworkListener {
        private final Listener<JSONObject> listenerResponse = response -> {

            Toast.makeText(LoginActivity.this, "inicio de sesión realizado Exitosamente", Toast.LENGTH_LONG).show();
            Log.d(LoginActivity.TAG + ": ", "Login Response : " + response.toString());
            DataContainer.user = new User(response);
            changeToMenu();
        };

        private final ErrorListener listenerError = error -> {
            Toast.makeText(LoginActivity.this, "Error durante el inicio de sesión", Toast.LENGTH_LONG).show();
            Log.d(TAG + ": ", "Error Response : " + error.toString());
        };
    }
}