package com.example.alumne.practicaandroid;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_register, container, false);

        Button RegisterButton = view.findViewById(R.id.RegisterButtonRe);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences datos = getActivity().getSharedPreferences("baseDeDatos", Context.MODE_PRIVATE);

                String userName,passName,pasNameRe;

                EditText user = (EditText) view.findViewById(R.id.campoUserNameRe);
                EditText pass = (EditText) view.findViewById(R.id.campoPasswordRe);
                EditText passRe = (EditText) view.findViewById(R.id.campoRePasswordRe);

                userName = user.getText().toString();
                passName = pass.getText().toString();
                pasNameRe = passRe.getText().toString();
                if (userName.isEmpty()||passName.isEmpty()){
                    Toast.makeText(getActivity(),"El usuario o la contraseña estan vacios",Toast.LENGTH_SHORT).show();


                }else{
                    if (userName.equals(datos.getString("username","no hay info"))){
                        Toast.makeText(getActivity(),"El usuario ya esta registrado",Toast.LENGTH_SHORT).show();
                    }else{
                        if (passName.equals(pasNameRe)) {
                            SharedPreferences.Editor editor = datos.edit();
                            editor.putString("username", userName);
                            editor.putString("password", passName);
                            editor.apply();

                            Toast.makeText(getActivity(), "Usuario " + userName + " se ha registrado correctamente", Toast.LENGTH_LONG).show();
                            MainActivity aux = (MainActivity) getActivity();
                            aux.intent();
                        }else{
                            Toast.makeText(getActivity(),"las contraseñas no son las mismas",Toast.LENGTH_SHORT).show();
                        }
                    }

                }

            }
        });
        return view;
    }

}
