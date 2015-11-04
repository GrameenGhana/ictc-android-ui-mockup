package org.grameenfoundation.smartex.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.grameenfoundation.smartex.R;

import java.util.ArrayList;
import java.util.List;


public class TabFarmersFragment extends Fragment implements AdapterView.OnItemSelectedListener
{
    public static final String TAG = "tabfarmers";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_farmers, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<String> comms = new ArrayList<>();
        comms.add("All");
        comms.add("Community 1");
        comms.add("Community 2");
        comms.add("Community 3");

        Spinner sp = (Spinner) getActivity().findViewById(R.id.spinner);
        sp.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, comms));

        List<String> content = new ArrayList<>();
        content.add("Farmer 1");
        content.add("Farmer 2");
        content.add("Farmer 3");

        ListView listView = (ListView) getActivity().findViewById(R.id.farmersListView);
        listView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, content));
        listView.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), "Opening farmer details", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}