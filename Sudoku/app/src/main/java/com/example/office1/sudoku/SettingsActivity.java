package com.example.office1.sudoku;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by OFFICE1 on 12/06/2560.
 */

public class SettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedTnstanceState){
        super.onCreate(savedTnstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

    public static boolean getOptionMusic(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("music",true);
    }

    public static boolean getOptionHints(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("hints",true);
    }
}
