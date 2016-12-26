package com.example.lin.androidreviewapp_week3;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.HttpAuthHandler;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Json extends AppCompatActivity {
    private String TAG = "Json";
    private ListView lv;

    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        contactList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);

        new GetContacts().execute();
    }
    private class GetContacts extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ListAdapter adapter = new SimpleAdapter(
                    Json.this,
                    contactList,
                    R.layout.list_item,
                    new String[]{"email","mobile"},
                    new int []{R.id.email, R.id.mobile});
            lv.setAdapter(adapter);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(Json.this, "Json Data is downling", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            HttpHandler httpHandler = new HttpHandler();
            //Makinga a request to url and getting response
            String url = "http://api.androidhive.info/contacts/";
            String jsonStr = httpHandler.makeServiceCall(url);

            Log.e(TAG, "Response from url: "+jsonStr);
            if(jsonStr != null){
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    // Getting json array node
                    JSONArray contacts = jsonObject.getJSONArray("contacts");
                    //looping through all Contacts
                    for(int i=0; i<contacts.length(); i++){
                        JSONObject c = contacts.getJSONObject(i);
                        String id = c.getString("id");
                        String name = c.getString("name");
                        String email = c.getString("email");
                        String address = c.getString("address");
                        String gender = c.getString("gender");
                        // Phone node is Json object
                        JSONObject phone = c.getJSONObject("phone");
                        String mobile = phone.getString("mobile");
                        String home = phone.getString("home");
                        String office = phone.getString("office");
                        //tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();
                        //adding each child node to Hashmap key => value
                        contact.put("id",id);
                        contact.put("name",name);
                        contact.put("email", email);
                        contact.put("mobile", mobile);
                        //adding contact to contact list
                        contactList.add(contact);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
