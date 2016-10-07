package com.example.pk.contacts;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ContactListAdapter extends BaseAdapter {

    private Context context;

    public ContactListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return MainActivity.contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return MainActivity.contacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return MainActivity.contacts.get(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = view;
        ViewHolder viewHolder;

        if (rowView == null) {
            LayoutInflater layoutInflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            rowView = layoutInflater.inflate(R.layout.list_view_item, viewGroup, false);

            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) rowView.findViewById(R.id.cv_name);
            viewHolder.email = (TextView) rowView.findViewById(R.id.cv_email);
            viewHolder.image = (ImageView) rowView.findViewById(R.id.cv_contact_image);

            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

        viewHolder.name.setText(MainActivity.contacts.get(i).getName());
        viewHolder.email.setText(MainActivity.contacts.get(i).getEmail());
        viewHolder.image.setImageDrawable(
                ContextCompat.getDrawable(context, MainActivity.contacts.get(i).getImage()));

        return rowView;
    }

    private static class ViewHolder {
        public TextView name;
        public TextView email;
        public ImageView image;
    }
}
