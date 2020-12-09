package com.hector.mygit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hector.mygit.DataModels.RepoDataModel;

public class RepoDetails extends AppCompatActivity {

    RepoDataModel repoDataModel;

    TextView id, name, fullName, size;
    Button openLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        repoDataModel = (RepoDataModel) getIntent().getSerializableExtra("repoDetails");
        setTitle(repoDataModel.name);

        id = findViewById(R.id.idTextView);
        name = findViewById(R.id.NameTextView);
        fullName = findViewById(R.id.fullNameTextView);
        size = findViewById(R.id.sizeTextView);
        openLink = findViewById(R.id.urlOpenButton);

        id.setText("ID: "+repoDataModel.id);
        name.setText("Name: "+repoDataModel.name);
        fullName.setText("Full Name: "+repoDataModel.full_name);
        size.setText("Size: "+repoDataModel.size);

        openLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(repoDataModel.html_url));
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}