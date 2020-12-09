package com.hector.mygit.DataModels;

import java.io.Serializable;

public class RepoDataModel implements Serializable {
    public String name, full_name, html_url, id, size;

    public RepoDataModel(){}

    public RepoDataModel(String name, String full_name, String html_url, String id, String size) {
        this.name = name;
        this.full_name = full_name;
        this.html_url = html_url;
        this.id = id;
        this.size = size;
    }
}
