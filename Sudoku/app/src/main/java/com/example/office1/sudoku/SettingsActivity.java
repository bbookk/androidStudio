package com.example.office1.sudoku;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by OFFICE1 on 12/06/2560.
 */

public class SettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedTnstanceState){
        super.onCreate(savedTnstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
