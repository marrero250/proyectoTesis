package com.example.josephmarrero.cantv.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.example.josephmarrero.cantv.data.api.model.Affiliate;

/**
 * Created by marre on 15/3/2017.
 */

public class SessionPrefs {

    public static final String PREFS_NAME = "CANTVMOCK_PREFS";
    public static final String PREF_AFFILIATE_ID = "PREF_USER_ID";
    public static final String PREF_AFFILIATE_NAME = "PREF_AFFILIATE_NAME";
    public static final String PREF_AFFILIATE_ADDRESS = "PREF_AFFILIATE_ADDRESS";
    public static final String PREF_AFFILIATE_GENDER = "PREF_AFFILIATE_GENDER";
    public static final String PREF_AFFILAITE_TOKEN = "PREF_AFFILAITE_TOKEN";
    public static final String PREF_AFFILAITE_NUMID = "PREF_AFFILAITE_NUMBERID";
    public static final String PREF_AFFILAITE_NUMTELE = "PREF_AFFILAITE_NUMTELE";
    public static final String PREF_AFFILAITE_FIREWALL = "PREF_AFFILAITE_FIREWALL";
    public static final String PREF_AFFILAITE_IDENTIFICADORLLAMADA = "PREF_AFFILAITE_IDENTIFICADORLLAMADA";
    public static final String PREF_AFFILAITE_BLOQUEOCERO = "PREF_AFFILAITE_BLOQUEOCERO";
    public static final String PREF_AFFILAITE_SERVICIO_TELEFONO = "PREF_AFFILAITE_SERVICIO_TELEFONO";
    public static final String PREF_AFFILAITE_SERVICIO_INTERNET = "PREF_AFFILAITE_SERVICIO_INTERNET";
    public static final String PREF_AFFILAITE_SERVICIO_TVSATELITAL = "PREF_AFFILAITE_SERVICIO_TVSATELITAL";
    public static final String PREF_AFFILAITE_NUMCUENTA = "PREF_AFFILAITE_NUMCUENTA";
    public static final String PREF_AFFILAITE_ESTATUSAVERIA = "PREF_AFFILAITE_ESTATUSAVERIA";
    public static final String PREF_AFFILAITE_INFORMACIONAVERIA = "PREF_AFFILAITE_INFORMACIONAVERIA";
    public static final String PREF_AFFILAITE_ABA = "PREF_AFFILAITE_ABA";
    public static final String PREF_AFFILAITE_PLANLOCAL = "PREF_AFFILAITE_PLANLOCAL";
    public static final String PREF_AFFILAITE_BUZONMENSAJES = "PREF_AFFILAITE_BUZONMENSAJES";

    private final SharedPreferences mPrefs;

    private boolean mIsLoggedIn = false;

    private static SessionPrefs INSTANCE;

    public static SessionPrefs get(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SessionPrefs(context);
        }
        return INSTANCE;
    }

    private SessionPrefs(Context context) {

        mPrefs = context.getApplicationContext()
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        mIsLoggedIn = !TextUtils.isEmpty(mPrefs.getString(PREF_AFFILAITE_TOKEN, null));
    }

    public boolean isLoggedIn() {

        return mIsLoggedIn;
    }

    public void saveAffiliate(Affiliate affiliate) {
        if (affiliate != null) {
            Log.d("Prueba", "Prueba");
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putString(PREF_AFFILIATE_ID, affiliate.getId());
            editor.putString(PREF_AFFILIATE_NAME, affiliate.getName());
            editor.putString(PREF_AFFILIATE_ADDRESS, affiliate.getAddress());
            editor.putString(PREF_AFFILIATE_GENDER, affiliate.getGender());
            editor.putString(PREF_AFFILAITE_TOKEN, affiliate.getToken());
            editor.putString(PREF_AFFILAITE_NUMID, affiliate.getNumero_cuenta());
            editor.putString(PREF_AFFILAITE_NUMTELE, affiliate.getTelefono());
            editor.putString(PREF_AFFILAITE_NUMCUENTA, affiliate.getNumero_cuenta());
            editor.putBoolean(PREF_AFFILAITE_FIREWALL, affiliate.isFirewall());
            editor.putBoolean(PREF_AFFILAITE_IDENTIFICADORLLAMADA, affiliate.isIdentificadorllamada());
            editor.putBoolean(PREF_AFFILAITE_BLOQUEOCERO, affiliate.isBloqueocero());
            editor.putBoolean(PREF_AFFILAITE_SERVICIO_TELEFONO, affiliate.isStelefono());
            editor.putBoolean(PREF_AFFILAITE_SERVICIO_INTERNET, affiliate.isSinternet());
            editor.putBoolean(PREF_AFFILAITE_SERVICIO_TVSATELITAL, affiliate.isStvsatelital());
            editor.putString(PREF_AFFILAITE_ESTATUSAVERIA, affiliate.getStatusaveria());
            editor.putString(PREF_AFFILAITE_INFORMACIONAVERIA, affiliate.getStatusaveria());
            editor.putInt(PREF_AFFILAITE_ABA, affiliate.getAba());
            editor.putInt(PREF_AFFILAITE_PLANLOCAL, affiliate.getPlanlocal());
            editor.putBoolean(PREF_AFFILAITE_BUZONMENSAJES, affiliate.isBuzonmensajes());
            editor.apply();

            mIsLoggedIn = true;
        }
    }

    public void logOut(){
        mIsLoggedIn = false;
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(PREF_AFFILIATE_ID, null);
        editor.putString(PREF_AFFILIATE_NAME, null);
        editor.putString(PREF_AFFILIATE_ADDRESS, null);
        editor.putString(PREF_AFFILIATE_GENDER, null);
        editor.putString(PREF_AFFILAITE_TOKEN, null);
        editor.putString(PREF_AFFILAITE_NUMTELE, null);
        editor.putString(PREF_AFFILAITE_NUMID, null);
        editor.putString(PREF_AFFILAITE_NUMCUENTA, null);
        editor.putBoolean(PREF_AFFILAITE_FIREWALL, false);
        editor.putBoolean(PREF_AFFILAITE_IDENTIFICADORLLAMADA, false);
        editor.putBoolean(PREF_AFFILAITE_BLOQUEOCERO, false);
        editor.putBoolean(PREF_AFFILAITE_SERVICIO_TELEFONO, false);
        editor.putBoolean(PREF_AFFILAITE_SERVICIO_INTERNET, false);
        editor.putBoolean(PREF_AFFILAITE_SERVICIO_TVSATELITAL, false);
        editor.putString(PREF_AFFILAITE_ESTATUSAVERIA, null);
        editor.putString(PREF_AFFILAITE_INFORMACIONAVERIA, null);
        editor.putInt(PREF_AFFILAITE_ABA, 0);
        editor.putInt(PREF_AFFILAITE_PLANLOCAL, 0);
        editor.putBoolean(PREF_AFFILAITE_BUZONMENSAJES, false);
        editor.apply();
    }
}