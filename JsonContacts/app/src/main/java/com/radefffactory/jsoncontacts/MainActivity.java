package com.radefffactory.jsoncontacts;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<ContactItem> contactItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new DownloadContacts().execute();
    }

    private class DownloadContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            loadAllContacts();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            contactItems = new ArrayList<>();
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            if (contactItems.size() > 0) {
                ContactsAdapter contactsAdapter = new ContactsAdapter(MainActivity.this, R.layout.item_contact, contactItems);
                ListView listView = findViewById(R.id.listView);
                listView.setAdapter(contactsAdapter);
            } else {
                Toast.makeText(MainActivity.this, "No data!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void loadAllContacts() {
        String networkJson = NetworkUtils.getJsonFromNetwork("https://radefffactory.com/Documents/contacts.json");

        try {
            JSONObject jsonObject = new JSONObject(networkJson);
            JSONArray contacts = jsonObject.getJSONArray("contacts");
            for (int i = 0; i < contacts.length(); i++) {
                JSONObject contact = contacts.getJSONObject(i);

                String name = contact.getString("name");
                String phone = contact.getString("phone");
                String address = contact.getString("address");
                String email = contact.getString("email");

                contactItems.add(new ContactItem(name, phone, address, email));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}