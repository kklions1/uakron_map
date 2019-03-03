package com.example.kevin.mapdatabasesproject.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.kevin.mapdatabasesproject.R;
import com.example.kevin.mapdatabasesproject.activity.MapsActivity;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class LoginFragment extends Fragment {
    private View fragmentView;
    private final String USERNAME_KEY = "username";
    private final String PASSWORD_KEY = "password";

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
            String username = usernameField.getText().toString();
            String password = passwordField.getText().toString();

            Map<String, String> loginCredentialsMap = new HashMap<>();
            loginCredentialsMap.put(USERNAME_KEY, username);
            // TODO encrypt the password
            loginCredentialsMap.put(PASSWORD_KEY, password);

            String json = gson.toJson(loginCredentialsMap);
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.shared_preferences_key),
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            // TODO should probably encrypt the password here, but meh.
            editor.putString(USERNAME_KEY, username);
            editor.putString(PASSWORD_KEY, password);


            // TODO make the network post asynchronously
//            new LoginFragment.LoginCall().execute(json);

            // TODO before we navigate away, we need to check to make sure the login was successful

            ((MapsActivity) getActivity()).navigateFromLoginToMap();
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
