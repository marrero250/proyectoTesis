package com.example.josephmarrero.cantv.Fragments;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.josephmarrero.cantv.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SoporteTecFragment extends Fragment {

    private boolean isFirsTime2 = true;
    private boolean isFirsTime3 = true;
    private Button btnSubmit;
    private Button btnConsult;
    int contador = 000100;


    private TextView textViewnumeroAveria;
    private TextView textviewestatusAveria;
    private TextView textViewInformacionAveria;
    public int getPuesto(int puesto) {
        return this.puesto;
    }

    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }

    private int puesto;
    private TextInputLayout etDescription;
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

        btnSubmit = (Button) view.findViewById(R.id.button);
        btnConsult = (Button) view.findViewById(R.id.buttonConsultar);
        textViewnumeroAveria = (TextView) view.findViewById(R.id.numeroAveria);
        textviewestatusAveria = (TextView) view.findViewById(R.id.estatusAveria);
        textViewInformacionAveria = (TextView) view.findViewById(R.id.textviewInformacionaveria);

//        etDescription = (TextInputLayout) view.findViewById(R.id.etDescription);

        final Spinner spinnerAverias = (Spinner) view.findViewById(R.id.spinnerReporteAverias);
        final Spinner spinnerconsultaaverias = (Spinner) view.findViewById(R.id.spinnerConsultaAverias);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.
                Reporte_Averias, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerAverias.setAdapter(adapter);

        final ArrayList<String> consultaaverias = new ArrayList<String>();
        final ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, consultaaverias);



        spinnerconsultaaverias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, View view, final int position, long id) {
                if (isFirsTime3){
                    isFirsTime3 = false;
                }else {
                    Toast.makeText(parent.getContext(), "El promedio de respuesta de la averia " + (String) parent.getItemAtPosition(position) + " Podria ser de 48 Horas habiles", Toast.LENGTH_SHORT).show();

                    btnConsult.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                      String test = (String) parent.getItemAtPosition(position);

                            Toast.makeText(parent.getContext(), "Prueba" + position + puesto , Toast.LENGTH_SHORT).show();


                            switch (test){
                                case "Seleccione":
                                    Toast.makeText(parent.getContext(), "Por favor seleccionar una opcion" + position + puesto , Toast.LENGTH_SHORT).show();
                                    break;
                                case "No tengo tono telefonico":
                                    textviewestatusAveria.setText("POR ASIGNAR");
                                    textViewInformacionAveria.setText("El usuario envio un reporte de : \n   No tengo tono telefonico ");
                                    textViewnumeroAveria.setText("10000100");
                                    break;
                                case "Mi modem no enciende":
                                    textviewestatusAveria.setText("POR ASIGNAR");
                                    textViewInformacionAveria.setText("El usuario envio un reporte de : \n    Mi modem no enciende");
                                    textViewnumeroAveria.setText("10000101");
                                    break;
                                case "No tengo servicio de internet":
                                    textviewestatusAveria.setText("POR ASIGNAR");
                                    textViewInformacionAveria.setText("El usuario envio un reporte de : \n  No tengo servicio de internet ");
                                    textViewnumeroAveria.setText("10000102");
                                    break;
                                case "Otro(especificar)":
                                    textviewestatusAveria.setText("El usuario envio un reporte de : \n   Otro(especificar) ");
                                    textViewnumeroAveria.setText("10000103");
                                    break;
                            }



                        }
                    });


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        spinnerAverias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> adapterView, View view, final int position, long id) {

                if (isFirsTime2){
                    isFirsTime2 = false;
                }else {
                    consultaaverias.add(adapterView.getItemAtPosition(position).toString());
                    adapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

                    Toast.makeText(adapterView.getContext(), "El promedio de respuesta de la averia " + (String) adapterView.getItemAtPosition(position) + " Podria ser de 48 Horas habiles", Toast.LENGTH_SHORT).show();


                    btnSubmit.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            spinnerconsultaaverias.setAdapter(adapter1);
                            setPuesto(position);


                            Toast.makeText(adapterView.getContext(),
                                    "Selecciono : " +
                                            "\n Reporte de averias : "+ String.valueOf(spinnerAverias.getSelectedItem()),
                                    Toast.LENGTH_SHORT).show();

                        }


                    });



                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });



        return view;




    }



}
