package com.example.josephmarrero.cantv.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.josephmarrero.cantv.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SolicitudesFragment extends Fragment {

    String planesList[] = {"2Mbps", "3Mbps", "6Mbps", "10Mbps"};
    View view;

    public SolicitudesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      // return inflater.inflate(R.layout.fragment_solicitudes, container, false);

        View view = inflater.inflate(R.layout.fragment_solicitudes,container,false);
        // Initialize the spinner
        Spinner spSimpleSpinner = (Spinner) view.findViewById(R.id.spinner);
        // Adapter will get the values from array and then show in the spinner
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,planesList);
        spSimpleSpinner.setAdapter(adapter);

        spSimpleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                Toast.makeText(adapterView.getContext(), "Su plan a sido cambiado con exito a " + (String) adapterView.getItemAtPosition(pos), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {    }
        });

        return view;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }




}


