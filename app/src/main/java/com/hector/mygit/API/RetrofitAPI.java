package com.hector.mygit.API;

import com.hector.mygit.DataModels.RepoDataModel;
import com.hector.mygit.DataModels.UserDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitAPI {

    @GET("users/{user}")
    Call<UserDataModel> getUserDataModel(@Path("user") String user);

    @GET("users/{user}/repos")
    Call<List<RepoDataModel>> getRepoList(@Path("user") String user);
}
