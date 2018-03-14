package com.example.josephmarrero.cantv.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.josephmarrero.cantv.R;
import com.example.josephmarrero.cantv.data.api.model.Affiliate;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {

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

        String PREFS_NAME = "CANTVMOCK_PREFS";
        String PREF_AFFILIATE_ID = "PREF_USER_ID";
        String PREF_AFFILIATE_NAME = "PREF_AFFILIATE_NAME";
        String PREF_AFFILIATE_ADDRESS = "PREF_AFFILIATE_ADDRESS";
        String PREF_AFFILIATE_GENDER = "PREF_AFFILIATE_GENDER";
        String PREF_AFFILAITE_NUMID = "PREF_AFFILAITE_NUMBERID";
        String PREF_AFFILAITE_NUMTELE = "PREF_AFFILAITE_NUMTELE";
        String PREF_AFFILAITE_TOKEN = "PREF_AFFILAITE_TOKEN";

        SharedPreferences sharedPref = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String id = sharedPref.getString(PREF_AFFILIATE_ID, "" );
        String name = sharedPref.getString(PREF_AFFILIATE_NAME, "" );
        String address= sharedPref.getString(PREF_AFFILIATE_ADDRESS, "" );
        String gender = sharedPref.getString(PREF_AFFILIATE_GENDER, "" );

        Toast.makeText(this.getContext(), "Nombre" + name + id, Toast.LENGTH_SHORT).show();

        TextView nombre = (TextView) view.findViewById(R.id.nameid);
        nombre.setText(name);

        TextView direccion = (TextView) view.findViewById(R.id.Adress);
        direccion.setText(address);




    }
}
