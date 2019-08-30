package com.fesadeb.housingnews.utils;

import com.fesadeb.housingnews.model.WPPost;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;


public interface BloggerAPI {

    @GET("wp-json/wp/v2/posts/")
    Call<List<WPPost>> getPostList();




}
