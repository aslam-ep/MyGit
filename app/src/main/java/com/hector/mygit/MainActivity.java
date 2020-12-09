package com.hector.mygit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.hector.mygit.API.RetrofitAPI;
import com.hector.mygit.API.RetrofitClient;
import com.hector.mygit.DataModels.UserDataModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    //View item declarations
    TextInputEditText nickNameEditText;
    Button findButton;
    ProgressBar progressBar;

    //API call requirement declaration
    RetrofitAPI retrofitAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        //View item linking
        nickNameEditText = findViewById(R.id.nickName);
        findButton = findViewById(R.id.findButton);
        progressBar = findViewById(R.id.progressBar1);

        //API call requirement initialization
        retrofitAPI = RetrofitClient.getInstance().create(RetrofitAPI.class);

        //FindButton onclick action listener
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                findButton.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                fetchData(nickNameEditText.getText().toString());
            }
        });
    }

    private void fetchData(String nickName) {
        Call<UserDataModel> call = retrofitAPI.getUserDataModel(nickName);
        call.enqueue(new Callback<UserDataModel>() {
            @Override
            public void onResponse(Call<UserDataModel> call, Response<UserDataModel> response) {
                if (response.body() != null) {
                    Snackbar.make(findViewById(android.R.id.content), "Welcome to " + response.body().login + " repo list", Snackbar.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, RepoList.class);
                    i.putExtra("login_id", response.body().login);
                    startActivity(i);
                    progressBar.setVisibility(View.INVISIBLE);
                    findButton.setVisibility(View.VISIBLE);
                }
                else {
                    Snackbar.make(findViewById(android.R.id.content), "Not a Valid Nick-Name", Snackbar.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    findButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<UserDataModel> call, Throwable t) {
                Snackbar.make(findViewById(android.R.id.content), "Check Your Connection", Snackbar.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                findButton.setVisibility(View.VISIBLE);
            }
        });
    }
}