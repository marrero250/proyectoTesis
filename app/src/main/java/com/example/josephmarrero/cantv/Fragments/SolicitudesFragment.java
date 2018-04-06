package com.example.josephmarrero.cantv.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.josephmarrero.cantv.R;
import com.example.josephmarrero.cantv.data.api.CantvApi;
import com.example.josephmarrero.cantv.data.api.model.Affiliate;
import com.example.josephmarrero.cantv.data.api.model.ApiError;
import com.example.josephmarrero.cantv.data.api.model.LoginBody;
import com.example.josephmarrero.cantv.data.prefs.SessionPrefs;
import com.example.josephmarrero.cantv.ui.LoginActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;
import static com.example.josephmarrero.cantv.data.prefs.SessionPrefs.PREFS_NAME;
import static com.example.josephmarrero.cantv.data.prefs.SessionPrefs.PREF_AFFILAITE_SERVICIO_TELEFONO;

/**
 * A simple {@link Fragment} subclass.
 */
public class SolicitudesFragment extends Fragment {

    private static class StringWithTag {
        public String string;
        public Object tag;

        public StringWithTag(String string, Object tag) {
            this.string = string;
            this.tag = tag;
        }

        @Override
        public String toString() {
            return string;
        }
    }


    private Retrofit mRestAdapter;
    private CantvApi mCantvApi;
    private Affiliate affiliate;

    String PREF_AFFILIATE_ID = "PREF_USER_ID";
    String PREF_AFFILAITE_SERVICIO_TELEFONO = "PREF_AFFILAITE_SERVICIO_TELEFONO";
    String PREF_AFFILAITE_SERVICIO_INTERNET = "PREF_AFFILAITE_SERVICIO_INTERNET";
    String PREF_AFFILAITE_SERVICIO_TVSATELITAL = "PREF_AFFILAITE_SERVICIO_TVSATELITAL";
    String PREF_AFFILAITE_FIREWALL = "PREF_AFFILAITE_FIREWALL";
    String PREF_AFFILAITE_IDENTIFICADORLLAMADA = "PREF_AFFILAITE_IDENTIFICADORLLAMADA";
    String PREF_AFFILAITE_BLOQUEOCERO = "PREF_AFFILAITE_BLOQUEOCERO";
    String PREF_AFFILIATE_NAME = "PREF_AFFILIATE_NAME";
    String PREF_AFFILIATE_ADDRESS = "PREF_AFFILIATE_ADDRESS";
    String PREF_AFFILIATE_GENDER = "PREF_AFFILIATE_GENDER";
    String PREF_AFFILAITE_NUMID = "PREF_AFFILAITE_NUMBERID";
    String PREF_AFFILAITE_NUMCUENTA = "PREF_AFFILAITE_NUMCUENTA";
    String PREF_AFFILAITE_NUMTELE = "PREF_AFFILAITE_NUMTELE";
    String PREF_AFFILAITE_TOKEN = "PREF_AFFILAITE_TOKEN";
    String PREF_AFFILAITE_ESTATUSAVERIA = "PREF_AFFILAITE_ESTATUSAVERIA";
    String PREF_AFFILAITE_INFORMACIONAVERIA = "PREF_AFFILAITE_INFORMACIONAVERIA";
    String PREF_AFFILAITE_ABA = "PREF_AFFILAITE_ABA";
    String PREF_AFFILAITE_PLANLOCAL = "PREF_AFFILAITE_PLANLOCAL";
    String PREF_AFFILAITE_BUZONMENSAJES = "PREF_AFFILAITE_BUZONMENSAJES";


    View view;
    private boolean isFirsTime = true;
    private boolean isFirsTime1 = true;

    public SolicitudesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Crear conexión al servicio REST
        mRestAdapter = new Retrofit.Builder()
                .baseUrl(CantvApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //...
        // Crear conexión a la API de CANTV
        mCantvApi = mRestAdapter.create(CantvApi.class);





        // Inflate the layout for this fragment
      // return inflater.inflate(R.layout.fragment_solicitudes, container, false);

        View view = inflater.inflate(R.layout.fragment_solicitudes,container,false);
        // Initialize the spinner


        return view;

    }





    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final HashMap<Integer, String> planaba = new HashMap<Integer, String>() {{
            put(0, "2Mbps");
            put(1, "4Mbps");
            put(2, "6Mbps");
            put(3, "10Mbps");
        }};


        final Switch switch_telefono = (Switch) view.findViewById(R.id.switchTelefono);
        Switch switch_tvsatelital = (Switch) view.findViewById(R.id.switchTV);
        Switch switch_internet = (Switch) view.findViewById(R.id.switchInternet);
        Switch switch_firewall = (Switch) view.findViewById(R.id.switchFirewall1);
        Switch switch_identificadorcall = (Switch) view.findViewById(R.id.switchIdentiLlamadas);
        Switch switch_bloqeocero = (Switch) view.findViewById(R.id.switchBloqueoNumero);
        Switch switch_buzondemensaje = (Switch) view.findViewById(R.id.switchBuzonDeMensajes);



        SharedPreferences sharedPref = getActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

       // SharedPreferences sharedPref1 = getActivity().getSharedPreferences(PREF_AFFILAITE_SERVICIO_TELEFONO, MODE_PRIVATE);

        final String userid = sharedPref.getString(PREF_AFFILIATE_ID, "");
        final String estatusaveria = sharedPref.getString(PREF_AFFILAITE_ESTATUSAVERIA, "");
        final String informacionaveria = sharedPref.getString(PREF_AFFILAITE_INFORMACIONAVERIA, "");
        final String username = sharedPref.getString(PREF_AFFILIATE_NAME, "");
        final int aba = sharedPref.getInt(PREF_AFFILAITE_ABA, 0);
        final int planlocal = sharedPref.getInt(PREF_AFFILAITE_PLANLOCAL, 0);
        final boolean buzondemensajes = sharedPref.getBoolean(PREF_AFFILAITE_BUZONMENSAJES, false);
        switch_buzondemensaje.setChecked(buzondemensajes);
        final String useraddress = sharedPref.getString(PREF_AFFILIATE_ADDRESS, "");
        final String gender = sharedPref.getString(PREF_AFFILIATE_GENDER, "");
        String numid = sharedPref.getString(PREF_AFFILAITE_NUMID, "");
        final String numtele = sharedPref.getString(PREF_AFFILAITE_NUMTELE, "");
        final String token = sharedPref.getString(PREF_AFFILAITE_TOKEN, "");
        final String numcuenta = sharedPref.getString(PREF_AFFILAITE_NUMCUENTA, "");
        final boolean stelefono = sharedPref.getBoolean(PREF_AFFILAITE_SERVICIO_TELEFONO, false);
        switch_telefono.setChecked(stelefono);
        final boolean sinternet = sharedPref.getBoolean(PREF_AFFILAITE_SERVICIO_INTERNET, false );
        switch_internet.setChecked(sinternet);
        final boolean stvsatelital = sharedPref.getBoolean(PREF_AFFILAITE_SERVICIO_TVSATELITAL, false);
        switch_tvsatelital.setChecked(stvsatelital);
        final boolean switchfirewall = sharedPref.getBoolean(PREF_AFFILAITE_FIREWALL, false);
        switch_firewall.setChecked(switchfirewall);
        final boolean switchidentificadorllamadas = sharedPref.getBoolean(PREF_AFFILAITE_IDENTIFICADORLLAMADA, false);
        switch_identificadorcall.setChecked(switchidentificadorllamadas);
        final boolean switchbloqueocero = sharedPref.getBoolean(PREF_AFFILAITE_BLOQUEOCERO, false);
        switch_bloqeocero.setChecked(switchbloqueocero);

        affiliate = new Affiliate(userid, username,useraddress,gender,token,numtele,numcuenta,switchfirewall,switchidentificadorllamadas,switchbloqueocero,stelefono,sinternet,stvsatelital,
                estatusaveria, informacionaveria, aba, planlocal, buzondemensajes);



     //   Toast.makeText(this.getContext(), "ACTIVO -->"+ stelefono, Toast.LENGTH_SHORT).show();




        switch_identificadorcall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                affiliate.setIdentificadorllamada(isChecked);
                SessionPrefs.get(getActivity()).saveAffiliate(affiliate);
                updateAPI(affiliate);
            }
        });

        switch_bloqeocero.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                affiliate.setBloqueocero(isChecked);
                SessionPrefs.get(getActivity()).saveAffiliate(affiliate);
                updateAPI(affiliate);
            }
        });

        switch_buzondemensaje.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                affiliate.setBuzonmensajes(isChecked);
                SessionPrefs.get(getActivity()).saveAffiliate(affiliate);
                updateAPI(affiliate);
            }
        });

        switch_firewall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(SolicitudesFragment.this.getContext(), "ACTIVADO", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SolicitudesFragment.this.getContext(), "DESACTVIADO", Toast.LENGTH_SHORT).show();
                }
                affiliate.setFirewall(isChecked);
                SessionPrefs.get(getActivity()).saveAffiliate(affiliate);
                updateAPI(affiliate);
            }
        });

        switch_telefono.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                   Toast.makeText(SolicitudesFragment.this.getContext(), "ACTIVADO", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SolicitudesFragment.this.getContext(), "DESACTVIADO", Toast.LENGTH_SHORT).show();
                }
                affiliate.setStelefono(isChecked);
                SessionPrefs.get(getActivity()).saveAffiliate(affiliate);
                updateAPI(affiliate);
            }
        });


        switch_internet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(SolicitudesFragment.this.getContext(), "ACTIVADO", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SolicitudesFragment.this.getContext(), "DESACTVIADO", Toast.LENGTH_SHORT).show();
                }
                affiliate.setSinternet(isChecked);
                SessionPrefs.get(getActivity()).saveAffiliate(affiliate);
                updateAPI(affiliate);
            }
        });

        switch_tvsatelital.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(SolicitudesFragment.this.getContext(), "ACTIVADO", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SolicitudesFragment.this.getContext(), "DESACTVIADO", Toast.LENGTH_SHORT).show();
                }
                affiliate.setStvsatelital(isChecked);
                SessionPrefs.get(getActivity()).saveAffiliate(affiliate);
                updateAPI(affiliate);
            }
        });

        Spinner spinnerPlaneslocales = (Spinner) view.findViewById(R.id.spinnerPlanesLocales);
        Spinner spinnerPlanesAba = (Spinner) view.findViewById(R.id.spinnerPlanesAba);


        List<StringWithTag> itemList = new ArrayList<StringWithTag>();

        /* Iterate through your original collection, in this case defined with an Integer key and String value. */
        for (Map.Entry<Integer, String> entry : planaba.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();

            /* Build the StringWithTag List using these keys and values. */
            itemList.add(new StringWithTag(value, key));
        }



        /* Set your ArrayAdapter with the StringWithTag, and when each entry is shown in the Spinner, .toString() is called. */
        ArrayAdapter<StringWithTag> spinnerAdapter = new ArrayAdapter<StringWithTag>(getActivity(), android.R.layout.simple_spinner_item, itemList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerPlanesAba.setAdapter(spinnerAdapter);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.Planes_locales, android.R.layout.simple_spinner_item);
        //    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this.getContext(),R.array.Planes_Aba, android.R.layout.simple_spinner_item);

        //adapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        // spSimpleSpinner.setAdapter(adapter1);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerPlaneslocales.setAdapter(adapter);

      //  Log.e("Prueba", "PRUEBA DE VALOR SETSELECTIO  " + affiliate.getAba());
        StringWithTag swt = spinnerAdapter.getItem(affiliate.getAba());
        Integer key = (Integer) swt.tag;
        spinnerPlanesAba.setSelection(key);
       // Log.e("Prueba", "PRUEBA DE VALOR SETSELECTIO DESPUES   "+ affiliate.getAba());

        Log.e("Prueba", "PRUEBA DE VALOR SETSELECTIO DESPUES   "+ affiliate.getPlanlocal());
        adapter.getItem(affiliate.getPlanlocal());
        spinnerPlaneslocales.setSelection(planlocal);
        Log.e("Prueba", "PRUEBA DE VALOR SETSELECTIO DESPUES   "+ affiliate.getPlanlocal());


        spinnerPlaneslocales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            adapterView.getItemAtPosition(position);
                affiliate.setPlanlocal(position);
                SessionPrefs.get(getActivity()).saveAffiliate(affiliate);
                updateAPI(affiliate);

                if (isFirsTime1){
                    isFirsTime1 = false;
                }else {
                    Toast.makeText(adapterView.getContext(), "Su plan a sido cambiado con exito a "+ position + (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerPlanesAba.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {   StringWithTag swt = (StringWithTag) parent.getItemAtPosition(pos);
                Integer key = (Integer) swt.tag;

            Log.e("pos", String.valueOf(pos));
            Log.e("key", String.valueOf(key));

            /*    if (isFirsTime){
                    isFirsTime = false;
                }else {
                    Toast.makeText(parent.getContext(), "  "  + key, Toast.LENGTH_SHORT).show();
                   // Toast.makeText(parent.getContext(), "Su plan a sido cambiado con exito a " +
                     //       (String) parent.getItemAtPosition(pos), Toast.LENGTH_SHORT).show();
                }
                    */

                affiliate.setAba(key);
                SessionPrefs.get(getActivity()).saveAffiliate(affiliate);
                updateAPI(affiliate);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {    }
        });



    }

    private void updateAPI(Affiliate affiliate) {
        Log.w("updateLlamado", "LLAMADO DE UPDATE");
        Log.d("Prueba2", Integer.toString(affiliate.getAba()));
        Call<Affiliate> loginCall = mCantvApi.update(affiliate);
        loginCall.enqueue(new Callback<Affiliate>() {

            @Override
            public void onResponse(Call<Affiliate> call, Response<Affiliate> response) {
                // Procesar errores
                if (!response.isSuccessful()) {
                    String error = "Ha ocurrido un error. Contacte al administrador";
                    if (response.errorBody()
                            .contentType()
                            .subtype()
                            .equals("json")) {
                        ApiError apiError = ApiError.fromResponseBody(response.errorBody());

                        error = apiError.getMessage();
                        Log.d("SolicitudesFragment", apiError.getDeveloperMessage());
                    } else {
                        try {
                            // Reportar causas de error no relacionado con la API
                            Log.d("SolicitudesFragment", response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<Affiliate> call, Throwable t) {

            }
        });
    }


}


