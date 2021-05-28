package com.example.geeknews.fragments;


import android.Manifest;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geeknews.R;
import com.example.geeknews.interfaces.DrawerLocker;
import com.example.geeknews.models.PostDetails;
import com.example.geeknews.retrofit.ApiInterface;
import com.example.geeknews.retrofit.RetrofitFactory;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.DOWNLOAD_SERVICE;
import static android.content.Context.MODE_PRIVATE;

public class PostFragment extends Fragment {

    private View view;
    private TextView scienceTopicTV;
    private TextView postTypeTv;
    private TextView titleTV;
    private TextView postDateTV;
    private TextView abstractTv;
    private ProgressBar progressBar;
    private String scirnceTopic;
    private String scirnceTopicSideMenu;
    private NavController navController;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String categoryName;
    private String categoryNameSideMenu;
    private int id;
    private ApiInterface apiInterface;
    private String mainArticle;
    private String downloadArticle;
    private Button mainArticleBtn;
    private Button downloadBtn;
    private WebView webView;
    private static final int PERMISSION_STORAGE_CODE = 1000;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_post, container, false);
        scienceTopicTV = view.findViewById(R.id.topicTV);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        postTypeTv = view.findViewById(R.id.postTypeTV);
        postDateTV = view.findViewById(R.id.postDateTV);
        titleTV = view.findViewById(R.id.titleTV);
        abstractTv = view.findViewById(R.id.abstract_tv);
        progressBar = view.findViewById(R.id.progressBar);
        mainArticleBtn = view.findViewById(R.id.mainArticleBtn);
        downloadBtn = view.findViewById(R.id.downloadBtn);
        webView = view.findViewById(R.id.webview);


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((DrawerLocker) getActivity()).setDrawerEnabled(true);
        mainArticleBtn.setEnabled(false);
        downloadBtn.setEnabled(false);

        setScienceTopicText();
        onBackPressed();
        getDetails();
        clickButtons();
    }

    private void getCategoryNameFromSideMenu() {
        sharedPreferences = requireContext().getSharedPreferences("category name in navDrawer", MODE_PRIVATE);
        categoryNameSideMenu = sharedPreferences.getString("name", "");
        scirnceTopicSideMenu = sharedPreferences.getString("topic", "");

    }

    private void setScienceTopicText() {

        if (navController.getGraph().getStartDestination() == R.id.homeFragment) {
            getCategoryNameFromSideMenu();

            scienceTopicTV.setText(scirnceTopicSideMenu);

        } else {
            getCategoryNameFromCategoriesFragment();

            scienceTopicTV.setText(scirnceTopic);

        }
    }

    private void getCategoryNameFromCategoriesFragment() {

        sharedPreferences = requireContext().getSharedPreferences("category name", MODE_PRIVATE);
        categoryName = sharedPreferences.getString("name", "");
        scirnceTopic = sharedPreferences.getString("topic", "");

    }

    private void gettId() {
        sharedPreferences = requireContext().getSharedPreferences("post id", MODE_PRIVATE);
        id = sharedPreferences.getInt("id", 0);
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

    public void getDetails() {
        gettId();
        apiInterface = RetrofitFactory.getRetrofit().create(ApiInterface.class);
        Call<PostDetails> getDetails = apiInterface.getDetails(id);
        getDetails.enqueue(new Callback<PostDetails>() {
            @Override
            public void onResponse(Call<PostDetails> call, Response<PostDetails> response) {
                if (response.code() == 200) {
                    progressBar.setVisibility(View.INVISIBLE);

                    postTypeTv.setText(response.body().getType());
                    titleTV.setText(response.body().getTitle());
                    postDateTV.setText(response.body().getDate());
                    abstractTv.setText(response.body().getTextAbstract());
                    mainArticle = response.body().getPageUrl();
                    downloadArticle = response.body().getPdfUrl();

                    mainArticleBtn.setEnabled(true);
                    downloadBtn.setEnabled(true);

                } else {

                    progressBar.setVisibility(View.INVISIBLE);

                    Toast.makeText(requireContext(), "" + response.code(), Toast.LENGTH_SHORT).show();

                }
            }


            @Override
            public void onFailure(Call<PostDetails> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(requireContext(), "" + t, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void clickButtons() {

        mainArticleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainArticle.equals("html")||mainArticle.equals("https://www.nature.com/articles/s42979-020-00397-4"))
                {
                    Toast.makeText(requireContext(), "This file not exist", Toast.LENGTH_SHORT).show();

                }
                else {
                    webView.loadUrl(mainArticle);
                }

            }
        });
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (requireContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_STORAGE_CODE);
                    } else {
                        startDownloading();
                    }
                } else {
                    startDownloading();

                }


            }
        });
    }


    private void startDownloading() {


//        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadArticle));
//        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
//        request.setTitle("Download");
//        request.setDescription("Download file ..... " + i);
//        request.allowScanningByMediaScanner();
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "" + System.currentTimeMillis());
//        request.setDestinationInExternalFilesDir(requireContext() ,    Environment.DIRECTORY_DOWNLOADS , "Downloads");
//        DownloadManager manager = (DownloadManager) requireActivity().getSystemService(DOWNLOAD_SERVICE);
//        manager.enqueue(request);

    if (downloadArticle.equals("pdf") || downloadArticle.equals("https://www.nature.com/articles/s42979-020-00397-4.pdf")){
        Toast.makeText(requireContext(), "No pdf for this article", Toast.LENGTH_SHORT).show();


}
    else
{
    Intent i = new Intent(Intent.ACTION_VIEW);
    i.setData(Uri.parse(downloadArticle));
    startActivity(i);
}
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_STORAGE_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startDownloading();
                } else {
                    Toast.makeText(requireContext(), "Permission denied ....!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
