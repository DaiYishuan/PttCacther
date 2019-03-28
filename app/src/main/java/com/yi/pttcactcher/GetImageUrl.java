package com.yi.pttcactcher;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetImageUrl extends AsyncTask<Void, Void, List<Member_titleTitleurlImgurl>> {

    private List<Member_titleTitleurlImgurl> list;
    private String url = "https://www.ptt.cc/bbs/Beauty/index.html";
    private String imageUrl_string;
    private List<Member_titleTitleurlImgurl> mlist = new ArrayList<>();
    public OnTaskCompleted listener;

    public GetImageUrl(OnTaskCompleted listener){
        this.listener = listener;
    }


    @Override
    protected List<Member_titleTitleurlImgurl> doInBackground(Void... voids) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements titles = document.select("div.title>a");

            for (Element title:titles){
                String title_string = title.text();
                String titleUrl_string = title.attr("href");

                Document imageDoc = Jsoup.connect("https://www.ptt.cc"+titleUrl_string).get();
                Log.d("titleUrlConnection","https://www.ptt.cc"+titleUrl_string);
                Element imageUrl = imageDoc.select("div[id=main-content]>a[href$=.jpg]").first();
//                Log.d("titleImageUrl",imageUrl.toString());
                if (imageUrl != null) {
                    imageUrl_string = imageUrl.attr("href");
                }else {
                    imageUrl_string = "";
                }
                Log.d("title",title_string);
//                Log.d("titleUrl",titleUrl_string);
                Log.d("titleImageUrl",imageUrl_string);
//                Log.d("titleUrlOrigin",title.toString());

                mlist.add(new Member_titleTitleurlImgurl(title_string,"https://www.ptt.cc"+titleUrl_string,imageUrl_string));
            }

//            String title_string = Jsoup.parse(title.toString()).text();
//            String titleUrl_string = title.get(2).attr("href");
//            Log.d("title",title_string);
//            Log.d("titleUrl",titleUrl_string);
//            Log.d("titleUrlOrigin",title.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mlist;
    }

    @Override
    protected void onPostExecute(List<Member_titleTitleurlImgurl> s) {
        listener.onTaskCompleted(s);
        for (Member_titleTitleurlImgurl member:s){
            Log.d("GetImg_articleList",member.getTitle());

        }
        Log.d("state","onPostExecute");

    }

    public interface OnTaskCompleted {
        void onTaskCompleted(List<Member_titleTitleurlImgurl> articleList);
    }
}
