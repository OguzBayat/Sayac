package com.oguzbayat.sayac;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by oguzbayat on 09/02/17.
 */

public class Ayarlar extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.ayarlar);

    }
}
