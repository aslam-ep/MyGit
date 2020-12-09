package com.hector.mygit.DataModels;

public class UserDataModel {
    public String login = null, repos_url = null;

    public UserDataModel() {
    }

    public UserDataModel(String login, String repos_url) {
        this.login = login;
        this.repos_url = repos_url;
    }
}
