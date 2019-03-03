package com.example.kevin.mapdatabasesproject.fragment;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.kevin.mapdatabasesproject.R;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class LoginFragment extends Fragment {
    private View fragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.login_layout, container, false);
        return fragmentView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button loginButton = fragmentView.findViewById(R.id.login_cta);
        loginButton.setOnClickListener((view) -> {
            Gson gson = new Gson();
            EditText usernameField = fragmentView.findViewById(R.id.username_field);
            EditText passwordField = fragmentView.findViewById(R.id.password_field);

            Map<String, String> loginCredentialsMap = new HashMap<>();
            loginCredentialsMap.put("username", usernameField.getText().toString());
            // TODO encrypt the password
            loginCredentialsMap.put("password", passwordField.getText().toString());

            String json = gson.toJson(loginCredentialsMap);

            new LoginFragment.LoginCall().execute(json);

        });
    }

    public static class LoginCall extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Void doInBackground(String... args) {
            String json = args[0];
            return null;
        }


    }
}
