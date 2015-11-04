package org.grameenfoundation.smartex.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.grameenfoundation.smartex.R;

import java.util.ArrayList;
import java.util.List;


public class TabMeetingsFragment extends Fragment {
    public static final String TAG = "meetings";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_meetings, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<String> content = new ArrayList<>();
        content.add("Meeting 1");
        content.add("Meeting 2");
        content.add("Meeting 3");
        content.add("Meeting 4");
        content.add("Meeting 5");
        content.add("Meeting 6");
        content.add("Meeting 7");
        ListView listView = (ListView) getActivity().findViewById(R.id.meetingsListView);
        listView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, content));
    }
}