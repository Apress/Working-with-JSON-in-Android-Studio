package com.radefffactory.jsoncontacts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContactsAdapter extends ArrayAdapter<ContactItem> {

    private final Context context;

    public ContactsAdapter(@NonNull Context context, int resource, List<ContactItem> items) {
        super(context, resource, items);
        this.context = context;
    }

    private static class ViewHolder {
        TextView name, phone, address, email;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        ContactItem item = getItem(position);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_contact, null);
            holder = new ViewHolder();

            holder.name = convertView.findViewById(R.id.name);
            holder.phone = convertView.findViewById(R.id.phone);
            holder.address = convertView.findViewById(R.id.address);
            holder.email = convertView.findViewById(R.id.email);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(item.getName());
        holder.phone.setText(item.getPhone());
        holder.address.setText(item.getAddress());
        holder.email.setText(item.getEmail());

        return convertView;
    }
}
