package com.example.josephmarrero.cantv.Fragments;


import android.os.Bundle;
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
public class SoporteTecFragment extends Fragment {

    private boolean isFirsTime2 = true;
    View view;

    public SoporteTecFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_soporte_tec, container, false);
        View view = inflater.inflate(R.layout.fragment_soporte_tec,container,false);

        Spinner spinnerAverias = (Spinner) view.findViewById(R.id.spinnerReporteAverias);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.
                Reporte_Averias, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerAverias.setAdapter(adapter);

        spinnerAverias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                if (isFirsTime2){
                    isFirsTime2 = false;
                }else {
                    Toast.makeText(adapterView.getContext(), "El promedio de respuesta de la averia " + (String) adapterView.getItemAtPosition(position) + " Podria ser de 48 Horas habiles", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }


}
