package com.dot.nbm.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.DialogFragment;

import com.dot.nbm.R;
import com.dot.nbm.doers.MainActivityHelper;

public class NBMPermissionAgainDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.permission_alert_title_again).setMessage(R.string.permission_alert_msg)
                .setPositiveButton(R.string.permission_alert_ok_again, (dialog, id) -> {
                    requestPermissionLauncher.launch(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION});
                }).setNegativeButton(R.string.permission_alert_no_again, (dialog, id) -> {
                    getActivity().finish();
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    private final ActivityResultLauncher<String[]> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts
                            .RequestMultiplePermissions(), result -> {
                        Boolean fineLocationGranted = result.get(
                                android.Manifest.permission.ACCESS_FINE_LOCATION);
                        Boolean coarseLocationGranted = result.get(
                                android.Manifest.permission.ACCESS_COARSE_LOCATION);
                        if ((fineLocationGranted != null && fineLocationGranted) || (coarseLocationGranted != null && coarseLocationGranted)) {
                            MainActivityHelper.runScheduleWorker(getContext());
                        } else {
                            getActivity().finish();
                        }
                    }
            );
}
