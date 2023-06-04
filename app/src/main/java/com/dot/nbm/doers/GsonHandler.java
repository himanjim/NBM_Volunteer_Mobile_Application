package com.dot.nbm.doers;

import android.content.Context;

import com.dot.nbm.R;
import com.dot.nbm.model.CombinedSignalNetworkHardwareState;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class GsonHandler {

    public static void incrementContributionCount(Context context, Integer contributions){
        String contributionsFileName = context.getString(R.string.contri_FName);
        Gson gson = new Gson();

        File file = new File(context.getFilesDir(), contributionsFileName);
        try {

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] arr = gson.toJson(contributions).getBytes();
            fileOutputStream.write(arr);
            fileOutputStream.close();

//            gson.toJson(testState, new FileWriter(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static Integer getContributionCount(Context context){
        String contributionsFileName = context.getString(R.string.contri_FName);

        Gson gson = new Gson();

        Integer contributions;
        try {
            File file = new File(context.getFilesDir(), contributionsFileName);
            if (!file.exists()){
                file.createNewFile();
                return null;
            }

            if (new BufferedReader(new FileReader(file)).readLine() == null)
                return null;

            JsonReader reader = new JsonReader(new FileReader(file));
            contributions = gson.fromJson(reader, Integer.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return contributions;

    }

    public static void saveCombinedSignalNetworkHardwareStates(Context context, List<CombinedSignalNetworkHardwareState> combinedSignalNetworkHardwareStates) {
        String combinedSignalNetworkHardwareStateFileName = context.getString(R.string.combSNHState);
        Gson gson = new Gson();

        try {
            File file = new File(context.getFilesDir(), combinedSignalNetworkHardwareStateFileName);

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] arr = gson.toJson(combinedSignalNetworkHardwareStates).getBytes();
            fileOutputStream.write(arr);
            fileOutputStream.close();

//            gson.toJson(testState, new FileWriter(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static List<CombinedSignalNetworkHardwareState> getCombinedSignalNetworkHardwareStates(Context context) {
        String combinedSignalNetworkHardwareStateFileName = context.getString(R.string.combSNHState);

        Gson gson = new Gson();

        List<CombinedSignalNetworkHardwareState> combinedSignalNetworkHardwareStates;
        try {
            File file = new File(context.getFilesDir(), combinedSignalNetworkHardwareStateFileName);

            if (!file.exists()){
                file.createNewFile();
                return null;
            }

            if (new BufferedReader(new FileReader(file)).readLine() == null)
                return null;

            JsonReader reader = new JsonReader(new FileReader(file));
            Type collectionType = new TypeToken<List<CombinedSignalNetworkHardwareState>>(){}.getType();
            combinedSignalNetworkHardwareStates = gson.fromJson(reader, collectionType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return combinedSignalNetworkHardwareStates;
    }
}