package com.demo.recyclerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.nesquena.recyclerviewdemo.R;

public class MainActivity extends Activity {
    ContactsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();
    }

    void setupViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // Setup RecyclerView, associated adapter, and layout manager.
        adapter = new ContactsAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Populate contact list.
        adapter.addMoreContacts(Contact.createContactsList(20));

        // Setup button to append additional contacts.
        Button addMoreButton = (Button) findViewById(R.id.add_more_contacts);
        addMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addMoreContacts(Contact.createContactsList(5));
            }
        });
    }
}
