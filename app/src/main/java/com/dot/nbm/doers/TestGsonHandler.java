package com.dot.nbm.doers;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class TestGsonHandler {


    public static void incrementContributionCount(Context context, Integer contributions) {
        String contributionsFileName = "TestWorker";
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

    public static Integer getContributionCount(Context context) {
        String contributionsFileName = "TestWorker";

        Gson gson = new Gson();

        Integer contributions;
        try {
            File file = new File(context.getFilesDir(), contributionsFileName);
            if (!file.exists()) {
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

}
