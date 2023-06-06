package com.dot.nbm.doers;

import android.content.Context;
import android.util.Log;

import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.dot.nbm.R;
import com.dot.nbm.workers.NBMListenableWorker;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class MainActivityHelper {

    public static void runScheduleWorker(Context applicationContext) {
        NBMListenableWorker.fetchSaveLocationNetworkParams(null, applicationContext);


        if (isWorkScheduled(applicationContext.getString(R.string.worker_tag), applicationContext)) {
            Log.i("combinedSignalNetworkHardwareState", "NBM worker scheduled");
        } else {
            scheduleWorker(applicationContext);
            Log.i("combinedSignalNetworkHardwareState", "NBM worker not scheduled");
        }
    }

    public static void scheduleWorker(Context applicationContext) {

        Constraints constraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
        PeriodicWorkRequest periodicNBMWork =
                new PeriodicWorkRequest.Builder(NBMListenableWorker.class, 15, TimeUnit.MINUTES, 5, TimeUnit.MINUTES)
                        .addTag(applicationContext.getString(R.string.worker_tag))
                        .setConstraints(constraints)
                        // setting a backoff on case the work needs to retry
                        .setBackoffCriteria(BackoffPolicy.LINEAR, PeriodicWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
                        .build();

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
                applicationContext.getString(R.string.worker_task),
                ExistingPeriodicWorkPolicy.KEEP, //Existing Periodic Work policy
                periodicNBMWork //work request
        );
    }

    private static boolean isWorkScheduled(String tag, Context applicationContext) {
        WorkManager instance = WorkManager.getInstance(applicationContext);
        ListenableFuture<List<WorkInfo>> statuses = instance.getWorkInfosByTag(tag);
        try {
            boolean running = false;
            List<WorkInfo> workInfoList = statuses.get();
            for (WorkInfo workInfo : workInfoList) {
                WorkInfo.State state = workInfo.getState();
                Log.i("combinedSignalNetworkHardwareState", tag + "_" + workInfo);
                running = state == WorkInfo.State.RUNNING | state == WorkInfo.State.ENQUEUED;
            }
            return running;
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

}
