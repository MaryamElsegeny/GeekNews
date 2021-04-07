package com.example.geeknews.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.geeknews.R;
import com.example.geeknews.interfaces.DrawerLocker;


public class LoginFragment extends Fragment {

    private Button loginBtn ;
    private TextView signUpTV;
    private TextView forgetPassTV;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        loginBtn = view.findViewById(R.id.login_btn);
        signUpTV = view.findViewById(R.id.sign_up_tv);
        forgetPassTV = view.findViewById(R.id.forgetPassTV);
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
       clickForgetPassTV();
        ((DrawerLocker) getActivity()).setDrawerEnabled(false);

    }

    private void clickLoginBtn(){
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginBtn.getBackground().setAlpha(100);

                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_categoriesFragment);
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
    private void clickForgetPassTV(){
        forgetPassTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgetPassTV.setTextColor(Color.parseColor("#0d6db1"));
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_forgetPassFragment);
            }
        });
    }
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