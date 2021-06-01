package com.example.geeknews.fragments;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geeknews.R;
import com.example.geeknews.classes.MyApplication;
import com.example.geeknews.interfaces.DrawerLocker;
import com.example.geeknews.models.User;
import com.example.geeknews.retrofit.ApiInterface;
import com.example.geeknews.retrofit.RetrofitFactory;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.io.IOException;


public class LoginFragment extends Fragment {

    private Button loginBtn ;
    private TextView signUpTV;
    private TextView forgetPassTV;
    private TextInputEditText userNameEditText ;
    private TextInputEditText passEditText ;
    private String username;
    private String password ;
    private ApiInterface apiInterface;
    private ProgressBar progressBar ;
    private View view ;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_login, container, false);
        loginBtn = view.findViewById(R.id.login_btn);
        signUpTV = view.findViewById(R.id.sign_up_tv);
        userNameEditText = view.findViewById(R.id.emailEd);
        passEditText = view.findViewById(R.id.passEd);
        progressBar = view.findViewById(R.id.progressBar);
//        forgetPassTV = view.findViewById(R.id.forgetPassTV);
        // Inflate the layout for this fragment
        return view ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        onBackPressed();
       clickLoginBtn();
       clickSignUpTV();
//       clickForgetPassTV();
        ((DrawerLocker) getActivity()).setDrawerEnabled(false);

    }

    private void getDataFromEd(){
        if (!userNameEditText.getText().toString().trim().equalsIgnoreCase("")) {
            username = userNameEditText.getText().toString();
        }
        if (!passEditText.getText().toString().trim().equalsIgnoreCase("")) {
            password = passEditText.getText().toString();
        }
    }

    private boolean isValid() {
        boolean valid;
        if (!userNameEditText.getText().toString().trim().equalsIgnoreCase("")) {
            valid = true;
        }
        if (!passEditText.getText().toString().trim().equalsIgnoreCase("")) {
            valid = true;
        } else valid = false;
        return valid;

    }
    private void postLogin() {
        getDataFromEd();
        apiInterface = RetrofitFactory.getRetrofit().create(ApiInterface.class);
        User user = new User(username, password);
        Call<User> postLogin = apiInterface.postLogin(user);
        postLogin.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                progressBar.setVisibility(View.INVISIBLE);

                if (response.code() == 200) {

                    if (isAdded()) {

                        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_categoriesFragment);
                    }
                    //save user login
                    Boolean saveUserLogin = true;
                    sharedPreferences = requireActivity().getSharedPreferences("saveUserLogin", 0);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("saveUserLogin", saveUserLogin);
                    editor.commit();

                    //save token
                    String token = response.body().getAccessToken();
                    MyApplication.tokenApplication = token ;
                    sharedPreferences = requireActivity().getSharedPreferences("token", 0);
                    editor = sharedPreferences.edit();
                    editor.putString("token", token);
                    editor.commit();
                }
                else if (response.code() == 401) {

                    String error = null;
                    try {
                        error = response.errorBody().string();
                    } catch (IOException e) {
//                            e.printStackTrace();
                    }
                    try {
                        JSONObject jObjError = new JSONObject(error);
                        Toast.makeText(getContext(), jObjError.getString("detail"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {

                    }

                } else {
                    Toast.makeText(requireContext(), "Something Error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                if (isAdded()) {
                    progressBar.setVisibility(View.INVISIBLE);

                    Toast.makeText(requireContext(), "No Internet Connection" , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void clickLoginBtn(){
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loginBtn.getBackground().setAlpha(100);
                progressBar.setVisibility(View.VISIBLE);

                if (isValid()) {
//                    Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_categoriesFragment);

                    postLogin();
                }
                else {
                    Toast.makeText(requireContext(), "please enter username & password", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);

                }

            }
        });
    }
    private void clickSignUpTV(){
        signUpTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpTV.setTextColor(Color.parseColor("#0d6db1"));
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_signUpFragment);
            }
        });
    }
//    private void clickForgetPassTV(){
//        forgetPassTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                forgetPassTV.setTextColor(Color.parseColor("#0d6db1"));
//                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_forgetPassFragment);
//            }
//        });
//    }
    public void onBackPressed() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finishAffinity();

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);

    }

}