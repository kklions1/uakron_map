package com.example.kevin.mapdatabasesproject.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.kevin.mapdatabasesproject.R;
import com.example.kevin.mapdatabasesproject.activity.MapsActivity;
import com.example.kevin.mapdatabasesproject.activity.MeanderActivity;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
    public void onResume() {
        super.onResume();

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

            // TODO should probably encrypt the password here, but meh, thats for later
            editor.putString(USERNAME_KEY, username);
            editor.putString(PASSWORD_KEY, password);

            editor.apply();

            ((MeanderActivity) getActivity()).navigateToMap();

            new LoginFragment.LoginCall().execute(json);

            // TODO before we navigate away, we need to check to make sure the login was successful
        });

        Button loginLater = fragmentView.findViewById(R.id.skip_login_button);
        loginLater.setOnClickListener((view) -> ((MeanderActivity) getActivity()).navigateToMap());
    }

    private final String PASS = "test_key";

    private String encrypt() {
        try {
            DESKeySpec keySpec = new DESKeySpec(PASS.getBytes(StandardCharsets.UTF_8));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key =
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class LoginCall extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... args) {
            String json = args[0];

            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);

            Request request = new Request.Builder()
                    .url("https://secure-outpost-229516.appspot.com/") // This is butts, but its the easiest way to do it
                    .post(body)
                    .build();

            OkHttpClient client = new OkHttpClient();

            try {
                client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
                return null;
        }
    }
}
