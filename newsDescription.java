package com.example.a51newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class newsDescription extends AppCompatActivity {

    TextView titletext,descriptiontext;
    ImageView imgView;
    String title,Content,imageToUrl,description;
    RecyclerView recyclerView;
    com.example.a51newsapp.verticalAdapter vr;
    ArrayList<com.example.a51newsapp.newsArticles> newsArticles;    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_description);
        Intent getint=getIntent();
        title=getint.getStringExtra("title");
        Content=getint.getStringExtra("con");
        description=getint.getStringExtra("description");
        imageToUrl=getint.getStringExtra("img");
        recyclerView=findViewById(R.id.recycler1);
        Bundle data=getIntent().getExtras();

        newsArticles=(ArrayList<com.example.a51newsapp.newsArticles>) data.getSerializable("articles");
        vr= new com.example.a51newsapp.verticalAdapter(newsArticles,this);

        titletext=findViewById(R.id.textView);
        descriptiontext=findViewById(R.id.textView2);
        imgView=findViewById(R.id.imageView);

        titletext.setText(title);
        descriptiontext.setText(description);
        Picasso.get().load(imageToUrl).into(imgView);
        recyclerView.setAdapter(vr);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}