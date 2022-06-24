package dev.khor.customfood.user_register_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Response;

import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dev.khor.customfood.R;
import dev.khor.customfood.login_activity.LoginActivity;
import dev.khor.customfood.models.User;
import dev.khor.customfood.network.NetworkManager;

public class UserRegisterActivity extends AppCompatActivity {
    private static final String TAG = "UserRegisterActivity";
    private NetworkListener networkListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        networkListener = new NetworkListener();
    }

    public void register(View view) {

        EditText em =findViewById(R.id.textEmail);//Poner los nombres correspondientes de cada componente
        EditText fn =findViewById(R.id.textName);
        EditText ln =findViewById(R.id.textLastname);
        EditText cell =findViewById(R.id.textNumberPhone);
        EditText bd =findViewById(R.id.textBirthdate);
        EditText dir =findViewById(R.id.textDirection);
        EditText ct =findViewById(R.id.textCity);
        EditText pass =findViewById(R.id.textPassword);
        EditText cpass =findViewById(R.id.textConfirmPassword);

        Map<String, Object> body = new HashMap<>();
        body.put("email", em.getText().toString());
        body.put("type", "customer");
        body.put("first_name", fn.getText().toString());
        body.put("last_name", ln.getText().toString());
        body.put("cell_phone", cell.getText().toString());
        body.put("birth_day", bd.getText().toString());
        body.put("direction", dir.getText().toString());
        body.put("city", ct.getText().toString());
        body.put("password", pass.getText().toString());
        body.put("password2", cpass.getText().toString());

        NetworkManager.getInstance(this).signup(
                networkListener.listenerResponse,
                networkListener.listenerError,
                body);

    }

    public void changeToLogin(){
        startActivity (new Intent( this,
                LoginActivity.class));
    }

    public void changeToLogin(View view){
        startActivity (new Intent( this,
                LoginActivity.class));
    }

    private class NetworkListener{
        private final Response.Listener<JSONObject> listenerResponse = response -> {
            Log.d(TAG + ": ", "Login Response : " + response.toString());
            Toast.makeText(UserRegisterActivity.this,"Registro Realizado Exitosamente", Toast.LENGTH_LONG).show();
            changeToLogin();
        };

        private final Response.ErrorListener listenerError = error -> {
            Toast.makeText(UserRegisterActivity.this,"Error durante el registro", Toast.LENGTH_LONG).show();
            Log.d(TAG + ": ", "Error Response : " + error.toString());
        };
    }

}