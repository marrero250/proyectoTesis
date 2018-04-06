package com.example.josephmarrero.cantv.Fragments;


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
import android.widget.Spinner;


import com.example.josephmarrero.cantv.R;
import com.example.josephmarrero.cantv.data.api.CantvApi;
import com.example.josephmarrero.cantv.data.api.model.Affiliate;
import com.example.josephmarrero.cantv.data.api.model.ApiError;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;
import static com.example.josephmarrero.cantv.data.prefs.SessionPrefs.PREFS_NAME;

/**
 * A simple {@link Fragment} subclass.
 */
public class PagosFragment extends Fragment {


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


    public PagosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pagos, container, false);



    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // Crear conexión al servicio REST
        mRestAdapter = new Retrofit.Builder()
                .baseUrl(CantvApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //...
        // Crear conexión a la API de CANTV
        mCantvApi = mRestAdapter.create(CantvApi.class);

        SharedPreferences sharedPref = getActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // SharedPreferences sharedPref1 = getActivity().getSharedPreferences(PREF_AFFILAITE_SERVICIO_TELEFONO, MODE_PRIVATE);

        final String userid = sharedPref.getString(PREF_AFFILIATE_ID, "");
        final String estatusaveria = sharedPref.getString(PREF_AFFILAITE_ESTATUSAVERIA, "");
        final String informacionaveria = sharedPref.getString(PREF_AFFILAITE_INFORMACIONAVERIA, "");
        final String username = sharedPref.getString(PREF_AFFILIATE_NAME, "");
        final int aba = sharedPref.getInt(PREF_AFFILAITE_ABA, 0);
        final int planlocal = sharedPref.getInt(PREF_AFFILAITE_PLANLOCAL, 0);
        final boolean buzondemensajes = sharedPref.getBoolean(PREF_AFFILAITE_BUZONMENSAJES, false);
        final String useraddress = sharedPref.getString(PREF_AFFILIATE_ADDRESS, "");
        final String gender = sharedPref.getString(PREF_AFFILIATE_GENDER, "");
        String numid = sharedPref.getString(PREF_AFFILAITE_NUMID, "");
        final String numtele = sharedPref.getString(PREF_AFFILAITE_NUMTELE, "");
        final String token = sharedPref.getString(PREF_AFFILAITE_TOKEN, "");
        final String numcuenta = sharedPref.getString(PREF_AFFILAITE_NUMCUENTA, "");
        final boolean stelefono = sharedPref.getBoolean(PREF_AFFILAITE_SERVICIO_TELEFONO, false);
        final boolean sinternet = sharedPref.getBoolean(PREF_AFFILAITE_SERVICIO_INTERNET, false );
        final boolean stvsatelital = sharedPref.getBoolean(PREF_AFFILAITE_SERVICIO_TVSATELITAL, false);
        final boolean switchfirewall = sharedPref.getBoolean(PREF_AFFILAITE_FIREWALL, false);
        final boolean switchidentificadorllamadas = sharedPref.getBoolean(PREF_AFFILAITE_IDENTIFICADORLLAMADA, false);
        final boolean switchbloqueocero = sharedPref.getBoolean(PREF_AFFILAITE_BLOQUEOCERO, false);

        affiliate = new Affiliate(userid, username,useraddress,gender,token,numtele,numcuenta,switchfirewall,switchidentificadorllamadas,switchbloqueocero,stelefono,sinternet,stvsatelital,
                estatusaveria, informacionaveria, aba, planlocal, buzondemensajes);


        Spinner spinnerPrepago = (Spinner) view.findViewById(R.id.spinnerRecargaPrepago);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.Recarga_Prepago, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerPrepago.setAdapter(adapter);

        spinnerPrepago.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parent.getItemAtPosition(position);
                //PENDIENTE POR CREAR LA VARIABLE Y recibiarla

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
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
