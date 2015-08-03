package com.example.yary.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by YARY on 02/08/2015.
 */
public class StartFragment extends Fragment {

    private String[] arrayDatas;
    private ListView list;

    public StartFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.start_fragment, container, false);
        list = (ListView) fragmentView.findViewById(R.id.listView);

        //Get datas from file datas.xml
        arrayDatas = getResources().getStringArray(R.array.list_datas);

        //Draw datas in a native android list.
        ArrayAdapter<String> objAdapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,arrayDatas);
        list.setAdapter(objAdapter);

        return fragmentView;

    }


}