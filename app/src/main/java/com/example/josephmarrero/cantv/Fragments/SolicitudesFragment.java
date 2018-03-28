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
private boolean isFirsTime = true;
    private boolean isFirsTime1 = true;

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

       // Spinner spSimpleSpinner1 = (Spinner) view.findViewById(R.id.spinnerPlanesLocales);

        Spinner spinnerListPlan = (Spinner) view.findViewById(R.id.spinnerPlanesLocales);


        // Adapter will get the values from array and then show in the spinner
       //ArrayAdapter adapterList = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,planesLocales);
       // spSimpleSpinner1.setAdapter(adapterList);

     //  ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,planesList);
      // spSimpleSpinner.setAdapter(adapter);

       ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.Planes_locales, android.R.layout.simple_spinner_item);
       ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this.getContext(),R.array.Planes_Aba, android.R.layout.simple_spinner_item);

       adapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
       spSimpleSpinner.setAdapter(adapter1);
       adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerListPlan.setAdapter(adapter);

        spinnerListPlan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                if (isFirsTime1){
                    isFirsTime1 = false;
                }else {
                    Toast.makeText(adapterView.getContext(), "Su plan a sido cambiado con exito a " + (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

      spSimpleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {

                if (isFirsTime){
                    isFirsTime = false;
                }else {
                    Toast.makeText(adapterView.getContext(), "Su plan a sido cambiado con exito a " + (String) adapterView.getItemAtPosition(pos), Toast.LENGTH_SHORT).show();
                }

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


