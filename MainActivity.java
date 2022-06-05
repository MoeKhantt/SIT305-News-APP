package com.example.a51newsapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView HorizontalView,VerticalView;
    private ArrayList<com.example.a51newsapp.newsArticles> verticalNews;
    private ArrayList<com.example.a51newsapp.newsArticles> horizontalNews;
    verticalAdapter verticalAdapter;
    horizontalAdapter horizontalAdapter;
    ArrayList<com.example.a51newsapp.newsArticles> articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HorizontalView=findViewById(R.id.horizontalRecycler);
        VerticalView=findViewById(R.id.verticalRecycler);
        verticalNews=new ArrayList<>();
        horizontalNews=new ArrayList<>();


        verticalAdapter=new verticalAdapter(verticalNews,this);
        horizontalAdapter=new horizontalAdapter(horizontalNews,this);

        VerticalView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        VerticalView.setAdapter(verticalAdapter);
        HorizontalView.setAdapter(horizontalAdapter);

        getNews();
    }

    private void getNews(){
        String url="https://newsapi.org/v2/everything?q=apple&from=2022-06-04&to=2022-06-04&sortBy=popularity&apiKey=66095c908bcc45368df1da0dfe095225";
        String BASE_URL="https://newsapi.org/";
        String topStoriesURL="https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=66095c908bcc45368df1da0dfe095225";
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitApi retrofitApi=retrofit.create(RetrofitApi.class);
        Call<NewsModel> call;
        call= retrofitApi.getAllNews(url);
        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {

                NewsModel newsModel=response.body();
                articles=newsModel.getNewsArticlesArrayList();

                if(true){
                    for (int i = 0; i < 10 ; i++) {
                        verticalNews.add(new com.example.a51newsapp.newsArticles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToImage(),articles.get(i).getUrl(),articles.get(i).getContent()));
                    }
                    verticalAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(MainActivity.this, "nothing there", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed ", Toast.LENGTH_SHORT).show();
            }
        });
        Retrofit retrofits=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitApi retrofitApis=retrofits.create(RetrofitApi.class);
        Call<NewsModel> calls;
        calls= retrofitApis.getAllNews(topStoriesURL);
        calls.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {

                NewsModel newsModel=response.body();
                articles=newsModel.getNewsArticlesArrayList();

                if(true){
                    for (int i = 0; i < 10 ; i++) {
                        horizontalNews.add(new com.example.a51newsapp.newsArticles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToImage(),articles.get(i).getUrl(),articles.get(i).getContent()));
                    }
                    horizontalAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(MainActivity.this, "nothing there", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}