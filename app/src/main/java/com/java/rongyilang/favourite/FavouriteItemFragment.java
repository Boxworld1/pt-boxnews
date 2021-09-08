package com.java.rongyilang.favourite;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.java.rongyilang.R;
import com.java.rongyilang.database.DataBase;
import com.java.rongyilang.favourite.placeholder.FavouritePlaceholderContent;
import com.java.rongyilang.history.HistoryItemAdapter;
import com.java.rongyilang.history.placeholder.HistoryPlaceholderContent;

/**
 * A fragment representing a list of Items.
 */
public class FavouriteItemFragment extends Fragment {

    private static final String ARG_TYPE = "type";
    private Activity mActivity;
    private String mType;
    public View viewRoot;
    public DataBase dataBase;
    public FavouritePlaceholderContent mFavouritePlaceholderContent;

    public FavouriteItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mType = getArguments().getString(ARG_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_favourite_list, container, false);
        dataBase = DataBase.getInstance(viewRoot.getContext());
        mActivity = getActivity();
        // Set the adapter
        if (viewRoot instanceof RecyclerView) {
            update();
        }
        return viewRoot;
    }

    public void update() {
        if (viewRoot instanceof RecyclerView) {
            Context context = viewRoot.getContext();
            RecyclerView recyclerView = (RecyclerView) viewRoot;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            mFavouritePlaceholderContent = new FavouritePlaceholderContent(dataBase);

            mFavouritePlaceholderContent.updateData(news -> {
                mActivity.runOnUiThread(()->{
                    recyclerView.setAdapter(new FavouriteItemAdapter(news));
                });
            });
        }
    }

}