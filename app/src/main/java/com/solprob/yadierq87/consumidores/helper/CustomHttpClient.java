package com.solprob.yadierq87.consumidores.helper;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.ArrayList;

/**
 * Created by alfredo on 25/09/2016.
 */
public class CustomHttpClient {

    public static final int HTTP_TIMEOUT=30*1000;
    private static HttpClient mHttpClient;

    private static HttpClient getmHttpClient() {
        if (mHttpClient ==null)
        mHttpClient=new DefaultHttpClient();
        return mHttpClient;
    }

    public static void executeHttpPost(String url, ArrayList postvalues) throws Exception{
    HttpClient client=getmHttpClient();
    HttpPost   post=new HttpPost(url);
    UrlEncodedFormEntity formEntity=new UrlEncodedFormEntity(postvalues);
    post.setEntity(formEntity);
    HttpResponse response=client.execute(post);
    }




}
