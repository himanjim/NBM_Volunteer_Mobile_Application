package com.dot.nbm.fragments;

import static android.text.Html.FROM_HTML_MODE_LEGACY;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Html;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.DialogFragment;

import com.dot.nbm.R;
import com.dot.nbm.doers.MainActivityHelper;

public class NBMPermissionDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.permission_alert_title).setMessage(Html.fromHtml(getString(R.string.permission_alert_msg), FROM_HTML_MODE_LEGACY))
                .setPositiveButton(R.string.permission_alert_ok, (dialog, id) -> {
                    requestPermissionLauncher.launch(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION});
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
                            new NBMPermissionAgainDialogFragment().show(getChildFragmentManager().beginTransaction(), getString(R.string.NBM_P_F_A_Tag));
                        }
                    }
            );
}