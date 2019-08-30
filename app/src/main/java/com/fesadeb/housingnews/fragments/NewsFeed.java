//package com.fesadeb.housingnews.fragments;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.housingnews.R;
//import com.fesadeb.housingnews.adapter.PostAdapter;
//import com.fesadeb.housingnews.model.PostList;
//import com.fesadeb.housingnews.model.WPPost;
//import com.fesadeb.housingnews.utils.BloggerAPI;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//
//public class NewsFeed extends Fragment {
//
//    RecyclerView recyclerView;
//    LinearLayoutManager manager;
//    PostAdapter adapter;
//    ArrayList<PostList> list ;
//    Boolean isScrolling = false;
//    int currentItems, totalItems, scrollOutItems;
//    String token = "";
//    View view;
//
//    public NewsFeed() {
//        // Required empty public constructor
//    }
//
//
//
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        view = inflater.inflate(R.layout.fragment_news_feed, container, false);
//
//        // Inflate the layout for this fragment
//
//        recyclerView = (RecyclerView) view.findViewById(R.id.postListTrends);
//        manager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(manager);
//
//        list = new ArrayList<PostList>();
//        adapter = new PostAdapter( this.getActivity(),list);
//
//
//        recyclerView.setAdapter(adapter);
//        return view;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//
////        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
////            @Override
////            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
////                super.onScrollStateChanged(recyclerView, newState);
////                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
////                {
////                    isScrolling = true;
////                }
////            }
//
////            @Override
////            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
////                super.onScrolled(recyclerView, dx, dy);
////                currentItems = manager.getChildCount();
////                totalItems = manager.getItemCount();
////                scrollOutItems = manager.findFirstVisibleItemPosition();
//
////                if(isScrolling && (currentItems + scrollOutItems == totalItems))
////                {
////                    isScrolling = false;
////                    getData();
////                }
////            }
////        });
//       getData();
//    }
//
//
//
//    private void getData()
//    {
//        String url = BloggerAPI.url;
////        if(token != ""){
////            url = url+ "&pageToken="+ token;
////        }
////        if(token == null){
////            return;
////        }
////        progress.setVisibility(View.VISIBLE);
//         final  Call <List<WPPost>> call = BloggerAPI.getService().getPostList(url);
//
//         call.enqueue(new Callback<List<WPPost>>() {
//             @Override
//             public void onResponse(Call<List<WPPost>> call, Response<List<WPPost>> response) {
//                 Log.d("tagmessage", response.body().toString());
//                 Log.d("success", "sucessful");
//
//                 for (int i = 0; i < response.body().size(); i++){
//
//                     Log.d("tag", "title" + response.body().get(i).getTitle().getRendered() + " "+ response.body().get(i).getId());
//
//                     String details = response.body().get(i).getExcerpt().getRendered().toString();
//                        details = details.replace("<p>","");
//                        details = details.replace("</p>","");
//                        details = details.replace("&#8230", ",");
//
//                    list.add(new PostList(PostList.IMAGE_TYPE, response.body().get(i).getTitle().getRendered(), details,
//                            response.body().get(i).getLinks().getWpFeaturedmedia().get(0).getHref()));
//
//                             }
//
//                             adapter.notifyDataSetChanged();
//             }
//
//             @Override
//             public void onFailure(Call<List<WPPost>> call, Throwable t) {
//
//             }
//         });
//
//
//
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
////                Toast.makeText(BlogActivity.this, "Success", Toast.LENGTH_SHORT).show();
////                progress.setVisibility(View.GONE);
//
//
//
//        }
//
//
//}
