package com.dot.nbm.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;
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
        Spanned contributionsCountText = HtmlCompat.fromHtml(getString(R.string.contri_textView_Str), HtmlCompat.FROM_HTML_MODE_COMPACT);
        @SuppressLint("StringFormatMatches") Spanned contributionsCount = HtmlCompat.fromHtml(String.format(getString(R.string.contri_count), mainActivityViewModel.getNoOfContributions()), HtmlCompat.FROM_HTML_MODE_COMPACT);

        SpannableStringBuilder spanBuilder = new SpannableStringBuilder();
        spanBuilder.append(" ", new ImageSpan(getActivity(), R.mipmap.contribution_icon_32, DynamicDrawableSpan.ALIGN_BASELINE), 0)
                .append(contributionsCountText).append("  ", new ImageSpan(getActivity(), R.mipmap.count_32, DynamicDrawableSpan.ALIGN_BASELINE), 0).append(contributionsCount).append("  ", new ImageSpan(getActivity(), R.mipmap.count_32, DynamicDrawableSpan.ALIGN_BASELINE), 0);

        TextView contributionsTextView = layout.findViewById(R.id.contributionsView);
        contributionsTextView.setText(spanBuilder);

        pauseCheckBox = layout.findViewById(R.id.pauseContriCheckBox);

        boolean shutdownState = GsonHandler.getPauseShutdownState(applicationContext);

        if (GsonHandler.getPauseBackgroundTaskState(applicationContext) || shutdownState)
            pauseCheckBox.setChecked(true);

        if (shutdownState)
            pauseCheckBox.setEnabled(false);

        pauseCheckBox.setOnClickListener(v -> {
            if (pauseCheckBox.isChecked()) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(applicationContext);
                alertBuilder.setMessage(R.string.pause_collection_alert_msg)
                        .setTitle(R.string.pause_collection_alert_title);
// Add the buttons
                alertBuilder.setPositiveButton(R.string.pause_collection_alert_no_confirm, (dialog, which) -> pauseCheckBox.setChecked(false));

                alertBuilder.setNegativeButton(R.string.pause_collection_alert_confirm, (dialog, which) -> {
                    WorkManager.getInstance(applicationContext).cancelAllWorkByTag(applicationContext.getString(R.string.worker_tag));
                    GsonHandler.savePauseBackgroundTaskState(applicationContext, true);
                    Toast.makeText(applicationContext, R.string.worker_paused, Toast.LENGTH_LONG).show();
                });
                AlertDialog dialog = alertBuilder.create();
                dialog.show();

            } else {
                MainActivityHelper.scheduleWorker(applicationContext);
                Toast.makeText(applicationContext, R.string.worker_resumed, Toast.LENGTH_LONG).show();
            }
        });

        return layout;
    }
}