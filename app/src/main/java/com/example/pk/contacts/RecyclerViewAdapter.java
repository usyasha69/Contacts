package com.example.pk.contacts;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(Contact item, int position);
    }

    private Context context;
    private OnItemClickListener onItemClickListener;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.recycler_view_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(MainActivity.contacts.get(position).getName());
        holder.email.setText(MainActivity.contacts.get(position).getEmail());
        holder.image.setImageDrawable(ContextCompat
                .getDrawable(context, MainActivity.contacts.get(position).getImage()));
    }

    @Override
    public int getItemCount() {
        return MainActivity.contacts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.cv_name)
        TextView name;
        @BindView(R.id.cv_email)
        TextView email;
        @BindView(R.id.cv_contact_image)
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                int position = getAdapterPosition();
                onItemClickListener.onItemClick(MainActivity.contacts.get(position), position);
            } else {
                throw new RuntimeException("You must init OnItemClickListener" +
                        " by calling setListener() method");
            }
        }
    }
}
