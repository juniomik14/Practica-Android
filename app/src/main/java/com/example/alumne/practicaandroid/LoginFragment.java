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
public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        final View view = inflater.inflate(R.layout.fragment_login, container, false);
        Button RegisterButton  = view.findViewById(R.id.RegisterButton);

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              MainActivity aux = (MainActivity) getActivity();
                aux.replaceFragmentRegister();
            }
        });

        Button LoginButton = view.findViewById(R.id.LoginButton);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity act = getActivity();
                MainActivity main = (MainActivity) act;
                SharedPreferences datos = getActivity().getSharedPreferences("baseDeDatos", Context.MODE_PRIVATE);

                String userName,passName;

                EditText user = (EditText) getActivity().findViewById(R.id.campoUserNameRe);
                EditText pass = (EditText) getActivity().findViewById(R.id.campoPasswordRe);
                userName = user.getText().toString();
                passName = pass.getText().toString();

                if (userName.isEmpty()||passName.isEmpty()){
                    Toast.makeText(getActivity(),"El usuario o la contraseña estan vacios",Toast.LENGTH_SHORT).show();
                }else{
                    if (userName.equals(datos.getString("username","no hay info"))){

                        if (passName.equals(datos.getString("password","no hay info"))) {
                            SharedPreferences.Editor editor = datos.edit();
                            editor.putString("username", userName);
                            editor.putString("password", passName);
                            editor.apply();

                            Toast.makeText(getActivity(), "Usuario " + userName + " se ha logeado correctamente", Toast.LENGTH_LONG).show();
                            MainActivity aux = (MainActivity) getActivity();
                            aux.intent();
                        }else{
                            Toast.makeText(getActivity(),"Las contraseñas no son las mismas",Toast.LENGTH_SHORT).show();

                        }
                    }else{
                        Toast.makeText(getActivity(),"El usuario no sea encontrado",Toast.LENGTH_SHORT).show();

                    }

                }

            }
        });
        return view;





    }


}
