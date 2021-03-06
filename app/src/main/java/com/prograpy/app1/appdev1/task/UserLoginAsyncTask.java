package com.prograpy.app1.appdev1.task;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prograpy.app1.appdev1.network.HttpRequest;
import com.prograpy.app1.appdev1.network.response.LoginResult;
import com.prograpy.app1.appdev1.network.response.LoginResult;

import java.util.HashMap;
import java.util.Map;
import com.prograpy.app1.appdev1.utils.D;

public class UserLoginAsyncTask extends AsyncTask<String, String, LoginResult> {

    private UserLoginResultHandler handler;


    public interface UserLoginResultHandler{
        public void onSuccessAppAsyncTask(LoginResult result);
        public void onFailAppAsysncask();
        public void onCancelAppAsyncTask();
    }


    public UserLoginAsyncTask(UserLoginResultHandler handler){
        this.handler = handler;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected LoginResult doInBackground(String... strings) {

        String path = strings[0];
        String id = strings[1];
        String pw = strings[2];

        LoginResult result  = null;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userid", id);
        params.put("password", pw);

        HttpRequest request = new HttpRequest();

        try {
            String str = request.callRequestServer(path,  "POST", params);

            D.log("http", "str > " + str);


            Gson gson = new GsonBuilder().create();
            result = gson.fromJson(str, LoginResult.class);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }


    @Override
    protected void onPostExecute(LoginResult loginResult) {
        super.onPostExecute(loginResult);

        if(loginResult != null){
            handler.onSuccessAppAsyncTask(loginResult);

        }else{
            handler.onFailAppAsysncask();
        }
    }
}
