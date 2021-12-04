package com.khr.justquitit;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.khr.justquitit.SavingHistory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PrefConfig {

    private static final String LIST_KEY = "list_key100";

    public static void writeListInPref(Context context, ArrayList<SavingHistory> savingArray) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(savingArray);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(LIST_KEY, jsonString);
        editor.apply();
    }

    public static ArrayList<SavingHistory> readListFromPref(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString = pref.getString(LIST_KEY, "");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<SavingHistory>>() {}.getType();
        ArrayList<SavingHistory> savingArray = gson.fromJson(jsonString, type);
        return savingArray;
    }

}
