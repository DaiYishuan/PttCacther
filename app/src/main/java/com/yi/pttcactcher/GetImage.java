package com.yi.pttcactcher;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class GetImage extends AsyncTask<Void, Void, List<Member_titleTitleurlImg>> {

    private final OnTaskCompleted listener;
    private String url = "https://www.ptt.cc/bbs/Beauty/index.html";
    private String imageUrl_string;
    private List<Member_titleTitleurlImg> mlist;
    private DownloadBitmap.OnDownLoadCompleted bitmapListener = new DownloadBitmap.OnDownLoadCompleted() {
        @Override
        public void onDownloadCompleted(Bitmap bitmap) {
            mlist.add(new Member_titleTitleurlImg(title_string,"https://www.ptt.cc"+titleUrl_string,bitmap));
        }
    };
    private String title_string;
    private String titleUrl_string;

    public GetImage(OnTaskCompleted listener){
        this.listener = listener;

    }
    @Override
    protected List<Member_titleTitleurlImg> doInBackground(Void... voids) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements titles = document.select("div.title>a");

            for (Element title:titles){
                title_string = title.text();
                titleUrl_string = title.attr("href");

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

//                Bitmap bitmap = null;
//                try {
//                    InputStream in = new java.net.URL(imageUrl_string).openStream();
//                    bitmap = BitmapFactory.decodeStream(in);
//                } catch (Exception e) {
//                    Log.e("Error", e.getMessage());
//                    e.printStackTrace();
//                }
                new DownloadBitmap(bitmapListener);


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
    protected void onPostExecute(List<Member_titleTitleurlImg> imgList) {
        this.listener.onTaskCompleted(imgList);
    }

    public interface OnTaskCompleted{
        void onTaskCompleted(List<Member_titleTitleurlImg> imgList);
    }
}
