package com.example.pk.contacts.fragments;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pk.contacts.Contact;
import com.example.pk.contacts.R;
import com.example.pk.contacts.RecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactListFragment extends Fragment {
    @BindView(R.id.clf_recycler_view)
    RecyclerView recyclerView;

    public static ContactListFragment newInstance() {

        Bundle args = new Bundle();

        ContactListFragment fragment = new ContactListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_contact_list, container, false);

        ButterKnife.bind(this, root);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext());
        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Contact item, int position) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                if (getContext().getResources().getConfiguration().orientation
                        == Configuration.ORIENTATION_PORTRAIT) {
                    fragmentManager
                            .beginTransaction()
                            .remove(fragmentManager.findFragmentByTag(
                                    ContactListFragment.class.getSimpleName()))
                            .replace(R.id.ma_portrait_acf_and_dcf_container
                                    , DetailContactFragment.newInstance(item)
                                    , DetailContactFragment.class.getSimpleName())
                            .addToBackStack(null)
                            .commit();
                } else {
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.ma_landscape_detail_contact_fragment_container
                                    , DetailContactFragment.newInstance(item)
                                    , DetailContactFragment.class.getSimpleName())
                            .commit();
                }
            }
        });

        recyclerView.setAdapter(recyclerViewAdapter);

        return root;
    }

    public void updateAdapter() {
        recyclerView.setAdapter(new RecyclerViewAdapter(getContext()));
    }
}
