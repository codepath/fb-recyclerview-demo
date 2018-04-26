package com.demo.recyclerviewdemo;

import android.content.Context;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.nesquena.recyclerviewdemo.R;

import java.util.ArrayList;

import java.util.List;

public class ContactsAdapter extends ListAdapter<Contact, ContactsAdapter.ViewHolder> {

    private List<Contact> mContacts = new ArrayList<>();

    public ContactsAdapter() {
        super(DIFF_CALLBACK);
    }

    public void addMoreContacts(List<Contact> newContacts) {
        mContacts.addAll(newContacts);
        submitList(mContacts); // DiffUtil takes care of the chekc
    }

    public static final DiffUtil.ItemCallback<Contact> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Contact>() {
                @Override
                public boolean areItemsTheSame(Contact oldItem, Contact newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(Contact oldItem, Contact newItem) {
                    return (oldItem.getName() == newItem.getName() && oldItem.isOnline() == newItem.isOnline());
                }
            };

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_contact, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Contact contact = getItem(position);

        TextView textView = viewHolder.nameTextView;
        textView.setText(contact.getName());

        Button button = viewHolder.messageButton;

        if (contact.isOnline()) {
            button.setText("Message");

            // OMIT to show that rows are recycled.
            //
            // Scrolling past the midpoint of the list (when contacts are listed as offline)
            // and scrolling back up should result in some buttons being inadvertently disabled.
            button.setEnabled(true);
        }
        else {
            button.setText("Offline");
            button.setEnabled(false);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public Button messageButton;

        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
            messageButton = (Button) itemView.findViewById(R.id.message_button);
        }
    }
}
