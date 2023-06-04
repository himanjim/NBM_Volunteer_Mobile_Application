package com.dot.nbm.workers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.dot.nbm.doers.TestGsonHandler;

public class TestWorker extends Worker {
    public TestWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.i("combinedSignalNetworkHardwareState", "started test worker");
        Integer contributions = TestGsonHandler.getContributionCount(getApplicationContext());

        if (contributions == null)
            contributions = 0;

        Log.i("combinedSignalNetworkLocation", "contributions" + contributions);

        TestGsonHandler.incrementContributionCount(getApplicationContext(), contributions + 1);

        return Result.success();
    }
}
