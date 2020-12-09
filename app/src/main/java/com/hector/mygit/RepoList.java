package com.hector.mygit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;
import com.hector.mygit.API.RetrofitAPI;
import com.hector.mygit.API.RetrofitClient;
import com.hector.mygit.DataModels.RepoDataModel;
import com.hector.mygit.RecyclerViewAdapters.ViewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoList extends AppCompatActivity {
    String loginID;

    RetrofitAPI retrofitAPI;

    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loginID = getIntent().getStringExtra("login_id");
        setTitle(loginID);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar2);

        retrofitAPI = RetrofitClient.getInstance().create(RetrofitAPI.class);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchData(loginID);
    }

    private void fetchData(String loginID) {
        Call<List<RepoDataModel>> call = retrofitAPI.getRepoList(loginID);
        call.enqueue(new Callback<List<RepoDataModel>>() {
            @Override
            public void onResponse(Call<List<RepoDataModel>> call, Response<List<RepoDataModel>> response) {
                displayData(response.body());
            }

            @Override
            public void onFailure(Call<List<RepoDataModel>> call, Throwable t) {
                Snackbar.make(findViewById(android.R.id.content), "Check Your Connection", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void displayData(List<RepoDataModel> repoDataModelList) {
        if (repoDataModelList.size() == 0)
            Snackbar.make(findViewById(android.R.id.content), "No Repository", Snackbar.LENGTH_SHORT).show();

        ViewAdapter adapter = new ViewAdapter(this, repoDataModelList);
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}