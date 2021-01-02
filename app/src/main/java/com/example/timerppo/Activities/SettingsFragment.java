package com.example.timerppo.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.Preference;
import androidx.room.Room;

import com.example.timerppo.DB.DBHelper;
import com.example.timerppo.R;


public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        Preference preference_theme = findPreference("theme");
        assert preference_theme != null;
        preference_theme.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if ((boolean) newValue) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                return true;
            }
        });

        Preference preference_clear = findPreference("clear_all");
        assert preference_clear != null;
        preference_clear.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                DBHelper db = Room.databaseBuilder(getContext(),
                        DBHelper.class, "database-name").allowMainThreadQueries().build();
                db.workoutDao().deleteAll();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                return true;
            }
        });
    }
}
