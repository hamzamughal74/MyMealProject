package com.example.mymealproject;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.felipecsl.gifimageview.library.GifImageView;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageView extends AppCompatActivity{
    GifImageView mGifImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview);
//        mGifImageView = findViewById(R.id.imageView);
////        https://media0.giphy.com/media/7i3GpkfProePu/giphy.gif?cid=790b76115d2529e45374382f3605f651&rid=giphy.gif
////http://freegifmaker.me/img/gifim300x226/1448234200.gif
//        new RetrieveByteArray().execute("https://media.giphy.com/media/ToMjGplHPoYqSg7oACc/giphy.gif");
//        mGifImageView.startAnimation();

    }
//    class RetrieveByteArray extends AsyncTask<String,Void,byte[]>{
//
//        @Override
//        protected byte[] doInBackground(String... strings) {
//            try{
//                URL url = new URL(strings[0]);
//                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
//                if (urlConnection.getResponseCode()== 200){
//                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//                    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//                    int nRead;
//                    byte[] data = new byte[10240];
//                    while ((nRead = in.read(data,0,data.length))!= -1){
//                        buffer.write(data,0,nRead);
//
//                    }
//                    buffer.flush();
//                    return buffer.toByteArray();
//                }
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(byte[] bytes) {
//            super.onPostExecute(bytes);
//            mGifImageView.setBytes(bytes);
//        }
//    }
}
