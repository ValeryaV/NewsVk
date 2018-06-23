package com.example.valeryaa.newsvk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.methods.VKApiGroups;
import com.vk.sdk.api.methods.VKApiWall;
import com.vk.sdk.api.model.VKList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class News extends Activity {
    final String LOG_DAO = "myLogs";

    ListView listView;

    JSONObject jsonObject = null;
    JSONArray jsonArray = null;
    JSONObject post = null;

    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        VKSdk.login(this);

        listView = (ListView) findViewById(R.id.post);
    }

    @Override
    protected void onActivityResult(int requestCode, int resCode, Intent data) {
        if (VKSdk.onActivityResult(requestCode, resCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                final VKRequest vkRequest = new VKApiGroups().getById(VKParameters.from("group_ids", "gstunews"));
                vkRequest.executeWithListener(new VKRequest.VKRequestListener() {
                    @Override
                    public void onComplete(VKResponse response) {
                        super.onComplete(response);

                        VKList vkList = (VKList) response.parsedModel;

                        try {
                            VKRequest vkRequest1 = new VKApiWall().get(VKParameters.from(VKApiConst.OWNER_ID, "-" + vkList.get(0).fields.getInt("id"), VKApiConst.COUNT, 5));

                            vkRequest1.executeWithListener(new VKRequest.VKRequestListener() {
                                @Override
                                public void onComplete(VKResponse response) {
                                    super.onComplete(response);
                                    //System.out.println(response.responseString);

                                    try {
                                        jsonObject = (JSONObject) response.json.get("response");
                                        //System.out.println(jsonObject.getString("items"));

                                        jsonArray = (JSONArray) jsonObject.get("items");
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            post = (JSONObject) jsonArray.get(i);

                                            //System.out.println(post.getJSONObject("attachments").getJSONObject("link").getString("title"));
                                            System.out.println(post.getString("attachments"));
                                            arrayList.add(post.getString("text"));
                                            arrayList.add("Likes = "+post.getJSONObject("likes").getString("count")+"\t"+"Reposts = "+post.getJSONObject("reposts").getString("count"));
                                            arrayAdapter = new ArrayAdapter<String>(News.this, android.R.layout.simple_list_item_1, arrayList);
                                            listView.setAdapter(arrayAdapter);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void onError(VKError error) {

            }
        })) {
            super.onActivityResult(requestCode, resCode, data);
        }
    }
}
