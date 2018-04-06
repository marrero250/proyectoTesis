package com.example.josephmarrero.cantv.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.josephmarrero.cantv.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReclamosFragment extends Fragment {

    private boolean isFirsTime = true;

    public ReclamosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_reclamos, container, false);
        View view = inflater.inflate(R.layout.fragment_reclamos,container,false);

        Spinner spinnerReclamos = (Spinner) view.findViewById(R.id.spinnerReclamos);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),R.array.Reporte_Averias, android.R.layout.simple_spinner_item);


        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerReclamos.setAdapter(adapter);



        spinnerReclamos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                if (isFirsTime){
                    isFirsTime = false;
                }else {
                    Toast.makeText(adapterView.getContext(), "Su plan a sido cambiado con exito a " + (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




    }
}
