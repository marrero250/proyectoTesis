package com.example.josephmarrero.cantv.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.josephmarrero.cantv.R;
import com.example.josephmarrero.cantv.data.api.model.Affiliate;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {


    public class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /** Show a toast from the web page */
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
    }


    TextView t;
    View view;


    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WebView myWebView = (WebView) view.findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        myWebView.loadUrl("https://mobile.twitter.com/ContactoCantv");
        webSettings.setJavaScriptEnabled(true);



        String PREFS_NAME = "CANTVMOCK_PREFS";
        String PREF_AFFILIATE_ID = "PREF_USER_ID";
        String PREF_AFFILIATE_NAME = "PREF_AFFILIATE_NAME";
        String PREF_AFFILIATE_ADDRESS = "PREF_AFFILIATE_ADDRESS";
        String PREF_AFFILIATE_GENDER = "PREF_AFFILIATE_GENDER";
        String PREF_AFFILAITE_NUMID = "PREF_AFFILAITE_NUMBERID";
        String PREF_AFFILAITE_NUMTELE = "PREF_AFFILAITE_NUMTELE";
        String PREF_AFFILAITE_TOKEN = "PREF_AFFILAITE_TOKEN";
        String PREF_AFFILAITE_SERVICIO_TELEFONO = "PREF_AFFILAITE_SERVICIO_TELEFONO";
        String PREF_AFFILAITE_SERVICIO_INTERNET = "PREF_AFFILAITE_SERVICIO_INTERNET";
        String PREF_AFFILAITE_SERVICIO_TVSATELITAL = "PREF_AFFILAITE_SERVICIO_TVSATELITAL";
        String PREF_AFFILAITE_ABA = "PREF_AFFILAITE_ABA";
        String PREF_AFFILAITE_PLANLOCAL = "PREF_AFFILAITE_PLANLOCAL";

        SharedPreferences sharedPref = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String id = sharedPref.getString(PREF_AFFILIATE_ID, "" );
        String name = sharedPref.getString(PREF_AFFILIATE_NAME, "" );
        String address= sharedPref.getString(PREF_AFFILIATE_ADDRESS, "" );
        String numtelefono = sharedPref.getString(PREF_AFFILAITE_NUMTELE, "" );
        boolean stelefono = sharedPref.getBoolean(PREF_AFFILAITE_SERVICIO_TELEFONO, false);
        boolean sinternet = sharedPref.getBoolean(PREF_AFFILAITE_SERVICIO_INTERNET, false );
        boolean stvsatelital = sharedPref.getBoolean(PREF_AFFILAITE_SERVICIO_TVSATELITAL, false);
        final int planlocal = sharedPref.getInt(PREF_AFFILAITE_PLANLOCAL, 0);
        final int aba = sharedPref.getInt(PREF_AFFILAITE_ABA, 0);

       // Toast.makeText(this.getContext(), "Nombre" + name + id, Toast.LENGTH_SHORT).show();

        TextView nombre = (TextView) view.findViewById(R.id.nameid);
        nombre.setText(name);
        TextView direccion = (TextView) view.findViewById(R.id.Adress);
        direccion.setText(address);
        TextView numerotelefono = (TextView) view.findViewById(R.id.numeroTelefono);
        numerotelefono.setText(numtelefono);
        TextView cedula = (TextView) view.findViewById(R.id.cedulaid);
        cedula.setText(id);
        TextView textoplanaba = (TextView)view.findViewById(R.id.planDeInternet);
        TextView textoplanLocal = (TextView)view.findViewById(R.id.textPlanLocal);

        switch (aba) {
            case 0: {
                textoplanaba.setText("2Mbps");
                break;
            }
            case 1: {
                textoplanaba.setText("4Mbps");
                break;
            }
            case 2: {
                textoplanaba.setText("6Mbps");
                break;
            }
            case 3: {
                textoplanaba.setText("10Mbps");
                break;
            }
            default: {
                textoplanaba.setText("Mbps");
            }
        } // END


        switch (planlocal) {
            case 0: {
                textoplanLocal.setText("Plan limitado");
                break;
            }
            case 1: {
                textoplanLocal.setText("Plan tarifa plana residencial");
                break;
            }
            case 2: {
                textoplanLocal.setText("Plan clásico");
                break;
            }
            case 3: {
                textoplanLocal.setText("Plan Básico (Sólo para Prepago)");
                break;
            }
            case 4: {
                textoplanLocal.setText("Plan habla más por menos");
                break;
            }
            case 5: {
                textoplanLocal.setText("Plan habla por llamadas");
                break;
            }
            case 6: {
                textoplanLocal.setText("Plan más minutos para todos");
                break;
            }
            case 7: {
                textoplanLocal.setText("Súper más minutos para todos");
                break;
            }
            default: {
                textoplanLocal.setText("Plan local disponible");
            }
        } // END


        if (stelefono){
            TextView serviciotelefono = (TextView) view.findViewById(R.id.statusTelefono);
            serviciotelefono.setText("ACTIVO");
            Toast.makeText(this.getContext(), "ACTIVO"+ stelefono, Toast.LENGTH_SHORT).show();
        }else {
            TextView serviciotelefono = (TextView) view.findViewById(R.id.statusTelefono);
            serviciotelefono.setText("DESACTIVADO");
            Toast.makeText(this.getContext(), "DESACTVIADO" + stelefono, Toast.LENGTH_SHORT).show();
        }


        if (sinternet){
            TextView serviciointernet = (TextView) view.findViewById(R.id.statusInternet);
            serviciointernet.setText("ACTIVO");
            Toast.makeText(this.getContext(), "ACTIVO"+ sinternet, Toast.LENGTH_SHORT).show();
        }else {
            TextView serviciointernet = (TextView) view.findViewById(R.id.statusInternet);
            serviciointernet.setText("DESACTIVADO");
            Toast.makeText(this.getContext(), "DESACTVIADO" + sinternet, Toast.LENGTH_SHORT).show();
        }

        if (stvsatelital){
            TextView serviciotvsatelital = (TextView) view.findViewById(R.id.statustvsatelital);
            serviciotvsatelital.setText("ACTIVO");
            Toast.makeText(this.getContext(), "ACTIVO"+ stvsatelital, Toast.LENGTH_SHORT).show();
        }else {
            TextView serviciotvsatelital = (TextView) view.findViewById(R.id.statustvsatelital);
            serviciotvsatelital.setText("DESACTIVADO");
            Toast.makeText(this.getContext(), "DESACTVIADO" + stvsatelital, Toast.LENGTH_SHORT).show();
        }


    }
}