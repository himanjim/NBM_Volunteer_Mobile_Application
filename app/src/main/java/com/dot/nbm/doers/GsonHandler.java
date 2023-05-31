package com.dot.nbm.doers;

import android.content.Context;

import com.dot.nbm.R;
import com.dot.nbm.model.CombinedSignalNetworkHardwareState;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class GsonHandler {

    public static void saveCombinedSignalNetworkHardwareStates(Context context, List<CombinedSignalNetworkHardwareState> combinedSignalNetworkHardwareStates) {
        String combinedSignalNetworkHardwareStateFileName = context.getString(R.string.combSNHState);
        Gson gson = new Gson();

        File file = new File(context.getFilesDir(), combinedSignalNetworkHardwareStateFileName);
        try {
            if (!file.exists())
                file.createNewFile();

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
        File file = new File(context.getFilesDir(), combinedSignalNetworkHardwareStateFileName);
        List<CombinedSignalNetworkHardwareState> combinedSignalNetworkHardwareStates = null;
        try {
            JsonReader reader = new JsonReader(new FileReader(file));
            combinedSignalNetworkHardwareStates = gson.fromJson(reader, CombinedSignalNetworkHardwareState.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return combinedSignalNetworkHardwareStates;
    }
}
