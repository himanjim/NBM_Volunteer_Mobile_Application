package com.dot.nbm.workers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class NBMWorker extends Worker {
    public NBMWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.i("combinedSignalNetworkHardwareState", "started NBM worker");
        Context applicationContext = getApplicationContext();

        return NBMWorkerHelper.doAllWork(applicationContext);
    }
}
