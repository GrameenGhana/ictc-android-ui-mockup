package org.grameenfoundation.smartex.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.grameenfoundation.smartex.R;

import java.util.ArrayList;
import java.util.List;


public class TabHomeFragment extends Fragment
{
    public static final String TAG = "tabhome";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_home, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        List<String> content = new ArrayList<>();
        content.add("Priority Item 1");
        content.add("Priority Item 2");
        content.add("Priority Item 3");
        content.add("Priority Item 4");

        ListView listView = (ListView) getActivity().findViewById(R.id.priorityListView);
        listView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, content));
    }

}