package dev.khor.customfood.principal_activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dev.khor.customfood.DataContainer;
import dev.khor.customfood.R;
import dev.khor.customfood.models.User;
import dev.khor.customfood.network.NetworkManager;

public class UserFragment extends Fragment implements View.OnClickListener{

    public boolean is_editing=false;
    public View view;
    public Button modButton, cancelButton;
    private static final String TAG = "Principal Activity";
    private NetworkListener networkListener;

    public TextInputEditText textname, textLastname, textEmail, textCity, textDirection, textBirthdate, textNumberPhone
            , textPassword, textConfirmPassword;

    public UserFragment() {
    }

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        networkListener = new NetworkListener();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_first, container, false);
        modButton = (Button) view.findViewById(R.id.modifyButton);
        modButton.setOnClickListener(this);
        cancelButton= (Button) view.findViewById(R.id.cancelUserButton);
        cancelButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textname =  view.findViewById(R.id.textName);
        textname.setText(DataContainer.user.getFirstName());
        textLastname =  view.findViewById(R.id.textLastname);
        textLastname.setText(DataContainer.user.getLastName());
        textEmail =  view.findViewById(R.id.textEmail);
        textEmail.setText(DataContainer.user.getEmail());
        textCity =  view.findViewById(R.id.textCity);
        textCity.setText(DataContainer.user.getCity());
        textDirection =  view.findViewById(R.id.textDirection);
        textDirection.setText(DataContainer.user.getDirection());
        textBirthdate =  view.findViewById(R.id.textBirthdate);
        textBirthdate.setText(DataContainer.user.getBirthday());
        textNumberPhone =  view.findViewById(R.id.textNumberPhone);
        textNumberPhone.setText(DataContainer.user.getCellPhone());
        textPassword =  view.findViewById(R.id.textPassword);
        textPassword.setText("");
        textConfirmPassword =  view.findViewById(R.id.textConfirmPassword);
        textConfirmPassword.setText("");
    }

    public void modifyUser(View view){
        if(is_editing){

            //Toca enviar una petición al servidor de actualizar.
            Map<String, Object> body = new HashMap<>();
            body.put("id",DataContainer.user.getId());
            body.put("email",textEmail.getText().toString());
            body.put("type", "customer");
            body.put("first_name", textname.getText().toString());
            body.put("last_name", textLastname.getText().toString());
            body.put("cell_phone", textNumberPhone.getText().toString());
            body.put("birth_day", textBirthdate.getText().toString());
            body.put("direction", textDirection.getText().toString());
            body.put("city", textCity.getText().toString());
            body.put("password", textPassword.getText().toString());
            body.put("password2", textConfirmPassword.getText().toString());

            NetworkManager.getInstance(getActivity()).modifyUser(
                    DataContainer.user.getId(),
                    networkListener.listenerResponse,
                    networkListener.listenerError,
                    body);

            //Se bloquean los campos nuevamente y se cambia de nombre al botón
            cancelModification(view);
        }else{
            modButton.setText("Confirmar Modificación");
            is_editing=true;
            cancelButton.setEnabled(true);
            textname.setEnabled(true);
            textLastname.setEnabled(true);
            textEmail.setEnabled(true);
            textCity.setEnabled(true);
            textDirection.setEnabled(true);
            textBirthdate.setEnabled(true);
            textNumberPhone.setEnabled(true);
            textPassword.setEnabled(true);
            textConfirmPassword.setEnabled(true);
        }

    }

    public void cancelModification(View view){
        modButton.setText("Modificar Información");
        cancelButton.setEnabled(false);
        textname.setEnabled(false);
        textLastname.setEnabled(false);
        textEmail.setEnabled(false);
        textCity.setEnabled(false);
        textDirection.setEnabled(false);
        textBirthdate.setEnabled(false);
        textNumberPhone.setEnabled(false);
        textPassword.setEnabled(false);
        textConfirmPassword.setEnabled(false);
        is_editing=false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.modifyButton:
                modifyUser(view);
                break;

            case R.id.cancelUserButton:
                cancelModification(view);
                break;
        }
    }

    private class NetworkListener {
        private final Response.Listener<JSONObject> listenerResponse = response -> {

            Toast.makeText(getActivity(), "Modificación realizada exitosamente", Toast.LENGTH_LONG).show();
            Log.d(TAG + ": ", "Login Response : " + response.toString());
            DataContainer.user = new User(response);
        };

        private final Response.ErrorListener listenerError = error -> {
            Toast.makeText(getActivity(), "Error durante la modificación", Toast.LENGTH_LONG).show();
            Log.d(TAG + ": ", "Error Response : " + error.toString());
        };
    }
}