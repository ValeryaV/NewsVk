package com.example.valeryaa.newsvk;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

@SuppressLint("ValidFragment")
public class News extends Fragment {
    View view;

    ListView listView;

    JSONObject jsonObject = null;
    JSONArray jsonArray = null;
    JSONObject post = null;

    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    String name;

    @SuppressLint("ValidFragment")
    public News(String name){
        this.name = name;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news, container, false);

        VKSdk.login(this);

        listView = (ListView) view.findViewById(R.id.post);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resCode, Intent data) {
        if (VKSdk.onActivityResult(requestCode, resCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                final VKRequest vkRequest = new VKApiGroups().getById(VKParameters.from("group_ids", name));
                vkRequest.executeWithListener(new VKRequest.VKRequestListener() {
                    @Override
                    public void onComplete(VKResponse response) {
                        super.onComplete(response);

                        VKList vkList = (VKList) response.parsedModel;

                        try {
                            VKRequest vkRequest1 = new VKApiWall().get(VKParameters.from(VKApiConst.OWNER_ID, "-" + vkList.get(0).fields.getInt("id"), VKApiConst.COUNT, 20));

                            vkRequest1.executeWithListener(new VKRequest.VKRequestListener() {
                                @RequiresApi(api = Build.VERSION_CODES.M)
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
                                            arrayList.add(post.getString("text"));
                                            arrayList.add("Likes = "+post.getJSONObject("likes").getString("count")+"\t"+"Reposts = "+post.getJSONObject("reposts").getString("count"));
                                            arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, arrayList);
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
