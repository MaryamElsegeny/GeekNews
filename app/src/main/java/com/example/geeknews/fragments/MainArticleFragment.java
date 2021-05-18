package com.example.geeknews.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.geeknews.R;

import static android.content.Context.MODE_PRIVATE;


public class MainArticleFragment extends Fragment {

private View view ;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String url;
    private WebView webView ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_article, container, false);
        webView = view.findViewById(R.id.webview);
        // Inflate the layout for this fragment
        return view ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onBackPressed();
        getUrl();
        WebView webView = new WebView(requireContext());
        requireActivity().setContentView(webView);
        webView.loadUrl(url);

    }

    public void onBackPressed() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(view).popBackStack();

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);

    }
    private void getUrl() {

        sharedPreferences = requireContext().getSharedPreferences("url", MODE_PRIVATE);
        url = sharedPreferences.getString("mainArticle", "");

    }
}