package com.dot.nbm.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.WorkManager;

import com.dot.nbm.R;
import com.dot.nbm.doers.GsonHandler;
import com.dot.nbm.doers.MainActivityHelper;
import com.dot.nbm.model.MainActivityViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    MainActivityViewModel mainActivityViewModel;
    CheckBox pauseCheckBox;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        Context applicationContext = getContext();

        mainActivityViewModel.setNoOfContributions(GsonHandler.getContributionCount(applicationContext));


        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.fragment_settings, container, false);

        TextView contributionsTextView = layout.findViewById(R.id.contributionsTextView);
        contributionsTextView.setText(Integer.toString(mainActivityViewModel.getNoOfContributions()));

        pauseCheckBox = layout.findViewById(R.id.pauseContriCheckBox);

        if (GsonHandler.getPauseBackgroundTaskState(applicationContext))
            pauseCheckBox.setChecked(true);

        pauseCheckBox.setOnClickListener(v -> {
            if (pauseCheckBox.isChecked()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(applicationContext);
                builder.setMessage(R.string.pause_collection_alert_msg)
                        .setTitle(R.string.pause_collection_alert_title);
// Add the buttons
                builder.setPositiveButton(R.string.pause_collection_alert_no_confirm, (dialog, which) -> {
                    pauseCheckBox.setChecked(false);
                });

                builder.setNegativeButton(R.string.pause_collection_alert_confirm, (dialog, which) -> {
                    WorkManager.getInstance(applicationContext).cancelAllWorkByTag(applicationContext.getString(R.string.worker_tag));
                    GsonHandler.savePauseBackgroundTaskState(applicationContext, true);
                    Toast.makeText(applicationContext, R.string.worker_paused, Toast.LENGTH_LONG).show();
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            } else {
                MainActivityHelper.scheduleWorker(applicationContext);
                Toast.makeText(applicationContext, R.string.worker_resumed, Toast.LENGTH_LONG).show();
            }
        });

        return layout;
    }
}