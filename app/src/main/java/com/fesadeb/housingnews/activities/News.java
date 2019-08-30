package com.fesadeb.housingnews.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import com.fesadeb.housingnews.R;
import com.fesadeb.housingnews.adapter.PostAdapter;
import com.fesadeb.housingnews.model.PostList;
import com.fesadeb.housingnews.model.WPPost;
import com.fesadeb.housingnews.utils.BloggerAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class News extends AppCompatActivity   {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<PostList> list;
    private PostAdapter adapter;
    private String baseURL = "http://www.housingnews.org.ng";
    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;



//    private String baseURL = "http://www.blueappsoftware.in";

    public static List<WPPost> mListPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


        recyclerView = (RecyclerView) findViewById(R.id.postListTrends);
        progressBar = findViewById(R.id.progressBarOne);
        mLayoutManager = new LinearLayoutManager(News.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

        list = new ArrayList<PostList>();
        /// call retrofill

        getData();

        adapter = new PostAdapter( list, News.this);

        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = mLayoutManager.getChildCount();
                totalItems = mLayoutManager.getItemCount();
                scrollOutItems = mLayoutManager.findFirstVisibleItemPosition();

                if(isScrolling && (currentItems + scrollOutItems == totalItems))
                {
                    isScrolling = false;
                    getData();
                }
            }
        });


    }


    private void getData()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BloggerAPI service = retrofit.create(BloggerAPI.class);
        Call <List<WPPost>> call = service.getPostList();

//        call.enqueue(new Callback<List<WPPost>>() {
//            @Override
//            public void onResponse(Call<List<WPPost>> call, Response<List<WPPost>> response) {
//                Log.d("tagmessage", "response" + response.body());
//               mListPost = response.body();
//                progressBar.setVisibility(View.GONE);
//
//
//
//                for (int i=0; i<response.body().size(); i++){
//
//                    Log.d("tag", "title" + response.body().get(i).getTitle().getRendered() + " "+ response.body().get(i).getId());
//
//                    String details = response.body().get(i).getExcerpt().getRendered().toString();
//                    details = details.replace("<p>","");
//                    details = details.replace("</p>","");
//                    details = details.replace("&#8230", "");
//
//                    list.add(new PostList(PostList.IMAGE_TYPE, response.body().get(i).getTitle().getRendered(), details,
//                            response.body().get(i).getLinks().getWpFeaturedmedia().get(0).getHref()));
//
//                }
//
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<List<WPPost>> call, Throwable t) {
//
//            }
//        });

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                Toast.makeText(BlogActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                progress.setVisibility(View.GONE);

        call.enqueue(new Callback<List<WPPost>>() {
            @Override
            public void onResponse(Call<List<WPPost>> call, Response<List<WPPost>> response) {
                Log.e("mainactivyt", " response "+ response.body());
                mListPost = response.body();
                progressBar.setVisibility(View.GONE);
                for (int i=0; i<response.body().size();i++){
                    Log.e("main ", " title "+ response.body().get(i).getTitle().getRendered() + " "+
                            response.body().get(i).getId());

                    String title = response.body().get(i).getTitle().getRendered();

                            title = title.replace("&#8216;","");
                            title = title.replace("&#8217;","");
                            title = title.replace("&#8212;","");

                    String tempdetails =  response.body().get(i).getExcerpt().getRendered().toString();
                    tempdetails = tempdetails.replace("<p>","");
                    tempdetails = tempdetails.replace("</p>","");
                    tempdetails = tempdetails.replace("&hellip;","");
                    tempdetails = tempdetails.replace("&#8216;","");
                    tempdetails = tempdetails.replace("&#8217;","");
                    tempdetails = tempdetails.replace("&#8212;","");

                    list.add( new PostList( PostList.IMAGE_TYPE,  title,
                            tempdetails,
                            response.body().get(i).getJetpackFeaturedMediaUrl()));

//                    esponse.body().get(i).getLinks().getWpFeaturedmedia().get(0).getHref())
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<WPPost>> call, Throwable t) {

            }
        });

    }

    public static List<WPPost> getList(){
        return  mListPost;
    }

}
