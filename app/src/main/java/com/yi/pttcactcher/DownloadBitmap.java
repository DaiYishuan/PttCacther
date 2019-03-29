package com.yi.pttcactcher;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;

public class DownloadBitmap extends AsyncTask<Void, Void, Bitmap> {
    private String imageUrl;
    private OnDownLoadCompleted listener;

    public DownloadBitmap(OnDownLoadCompleted listener){
        this.listener = listener;
//        this.imageUrl = member_titleTitleurlImgurl.getImgUrl();
//        this.title = member_titleTitleurlImgurl.getTitle();
//        this.titleUrl = member_titleTitleurlImgurl.getTitleUrl();

    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        Bitmap bitmap = null;
        try {
            InputStream in = new java.net.URL(imageUrl).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        listener.onDownloadCompleted(bitmap);

    }

    public interface OnDownLoadCompleted{
        void onDownloadCompleted (Bitmap bitmap);
    }
}
