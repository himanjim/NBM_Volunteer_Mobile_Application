package com.dot.nbm.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.graphics.text.LineBreaker;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dot.nbm.R;
import com.dot.nbm.doers.SignalStateFetcher;
import com.dot.nbm.doers.SignalStrengthLevelIndicator;
import com.dot.nbm.model.MainActivityViewModel;
import com.dot.nbm.model.SignalState;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class QoSFragment extends Fragment {

    MainActivityViewModel mainActivityViewModel;

    @SuppressLint("StringFormatMatches")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        Log.d("signalStrength", getSignalStrength(getContext()));

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.fragment_qo_s, container, false);

//        TextView operatorNameTextView = layout.findViewById(R.id.operatorNameTextView);
//        TextView technologyTextView = layout.findViewById(R.id.technologyTextView);
//        TextView signalStrengthTextView = layout.findViewById(R.id.signalStrengthTextView);
//        signalStrengthTextView.setText("Hello");
//        Log.d("signalStrengthTextView", (String) operatorNameTextView.getText());

        Log.d("Phone_Model", String.join("-", Build.MANUFACTURER, Build.PRODUCT, Build.BRAND, Build.MODEL, Build.HOST, Build.HARDWARE));

        fetchAndShowSignals(layout);

        FloatingActionButton fab = layout.findViewById(R.id.refresh_signals);
        fab.setOnClickListener(view -> {

//            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction().detach(QoSFragment.this);
//            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            fetchAndShowSignals(layout);
//            fragmentTransaction.attach(QoSFragment.this).commit();

            Snackbar.make(view, "Signals refreshed", Snackbar.LENGTH_LONG).show();
        });


        if (ContextCompat.checkSelfPermission(
                getActivity(), Manifest.permission.ACCESS_BACKGROUND_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(R.string.bg_permission_alert_title);
// Add the buttons
            builder.setPositiveButton(R.string.bg_permission_alert_ok, (dialog, id) -> requestPermissionLauncher2.launch(new String[]{android.Manifest.permission.ACCESS_BACKGROUND_LOCATION}));

            LayoutInflater dialog_inflater = getLayoutInflater();

            builder.setView(dialog_inflater.inflate(R.layout.bg_location_alert_image, null));
            builder.show();

//            AlertDialog dialog = builder.create();
//            dialog.show();
        }

//        ImageButton imgButton = layout.findViewById(R.id.contact_operator);
//        imgButton.setOnClickListener(v -> {
//            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//            builder.setMessage(R.string.permission_alert_msg_again)
//                    .setTitle(R.string.permission_alert_title_again);
//// Add the buttons
//            builder.setPositiveButton(R.string.permission_alert_ok_again, new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int id) {
//                }
//            });
//            builder.setNegativeButton(R.string.permission_alert_no_again, (dialog, id) -> {
//            });
//            AlertDialog dialog = builder.create();
//            dialog.show();
//
//        });


        return layout;
    }

    private void fetchAndShowSignals(ViewGroup layout) {
        if (ContextCompat.checkSelfPermission(
                getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {

            List<SignalState> signalStates = SignalStateFetcher.getSignalState(getContext());

            Set<SignalState> s = new HashSet<>();
            if (signalStates != null) {
                s.addAll(signalStates);
            }
            List<SignalState> uniqueSignalStates = new ArrayList<>(s);

            Collections.sort(uniqueSignalStates);
            mainActivityViewModel.setSignals(new ArrayList<>());

//            SignalState firstSignalState = signalStates.get(0);

//            int signal_count = 1;
            if (uniqueSignalStates .size() > 0) {

                for (SignalState signalState : uniqueSignalStates) {
                    //                String dynamicText = String.format(getString(R.string.signal_strength_text), ordinal(signal_count), signalState.getOperaterName(), signalState.getTechnology(), signalState.getSignalStrength(), "ok");

                    Spanned operatorStyleText = HtmlCompat.fromHtml(String.format(getString(R.string.signal_operator), signalState.getOperatorName().toUpperCase()), HtmlCompat.FROM_HTML_MODE_COMPACT);
                    Spanned technologyStyleText = HtmlCompat.fromHtml(String.format(getString(R.string.signal_technology), signalState.getGeneration()), HtmlCompat.FROM_HTML_MODE_COMPACT);
                    @SuppressLint("StringFormatMatches") Spanned strengthStyleText = HtmlCompat.fromHtml(String.format(getString(R.string.signal_strength), signalState.getSignalStrength()), HtmlCompat.FROM_HTML_MODE_COMPACT);
                    Spanned dbmText = HtmlCompat.fromHtml(getString(R.string.dbm), HtmlCompat.FROM_HTML_MODE_COMPACT);

                    //                Spanned dynamicStyledText =  HtmlCompat.fromHtml(dynamicText, HtmlCompat.FROM_HTML_MODE_COMPACT);
                    //                mainActivityViewModel.getSignals().add(String.format(getString(R.string.signal_strength_text), ordinal(signal_count), signalState.getOperaterName(), signalState.getTechnology(), signalState.getSignalStrength(), "ok"));

                    SignalStrengthLevelIndicator.SignalLevel signalLevel = SignalStrengthLevelIndicator.getSignalStrengthLevel(signalState);

                    SpannableStringBuilder builder = new SpannableStringBuilder();
                    builder.append("|", new ImageSpan(getActivity(), R.mipmap.operator_32, DynamicDrawableSpan.ALIGN_BASELINE), 0)
                            .append(operatorStyleText).append("| ", new ImageSpan(getActivity(), R.mipmap.technology_32, DynamicDrawableSpan.ALIGN_BASELINE), 0)
                            .append(technologyStyleText).append(" ", new ImageSpan(getActivity(), R.mipmap.strength_32, DynamicDrawableSpan.ALIGN_BASELINE), 0)
                            .append(strengthStyleText).append(dbmText).append(" ", new ImageSpan(getActivity(), R.mipmap.quality_32, DynamicDrawableSpan.ALIGN_BASELINE), 0);

                    Spanned qualityStyleText;
                    if (signalLevel == SignalStrengthLevelIndicator.SignalLevel.GREAT) {
                        qualityStyleText = HtmlCompat.fromHtml(getString(R.string.signal_quality_great), HtmlCompat.FROM_HTML_MODE_COMPACT);
                        builder.append(qualityStyleText);

                    } else if (signalLevel == SignalStrengthLevelIndicator.SignalLevel.GOOD) {
                        qualityStyleText = HtmlCompat.fromHtml(getString(R.string.signal_quality_good), HtmlCompat.FROM_HTML_MODE_COMPACT);
                        builder.append(qualityStyleText);

                    } else if (signalLevel == SignalStrengthLevelIndicator.SignalLevel.MODERATE) {
                        qualityStyleText = HtmlCompat.fromHtml(getString(R.string.signal_quality_moderate), HtmlCompat.FROM_HTML_MODE_COMPACT);
                        builder.append(qualityStyleText);

                    } else if (signalLevel == SignalStrengthLevelIndicator.SignalLevel.POOR) {
                        qualityStyleText = HtmlCompat.fromHtml(getString(R.string.signal_quality_poor), HtmlCompat.FROM_HTML_MODE_COMPACT);
                        builder.append(qualityStyleText);

                        setClickSpanOnBuilder(builder);
                    } else if (signalLevel == SignalStrengthLevelIndicator.SignalLevel.UNKNOWN) {
                        qualityStyleText = HtmlCompat.fromHtml(getString(R.string.signal_quality_unknown), HtmlCompat.FROM_HTML_MODE_COMPACT);
                        builder.append(qualityStyleText);

                        setClickSpanOnBuilder(builder);
                    }
                    mainActivityViewModel.getSignals().add(builder);
                    //                signal_count++;
                }
            }

            if (mainActivityViewModel.getSignals().size() > 0) {
                TextView signalsAvailableTextView = layout.findViewById(R.id.signalAvailable);
                signalsAvailableTextView.setText(new SpannableStringBuilder().append("|", new ImageSpan(getActivity(), R.mipmap.signals_available_icon_32, DynamicDrawableSpan.ALIGN_BASELINE), 0)
                        .append(HtmlCompat.fromHtml(getString(R.string.signal_available), HtmlCompat.FROM_HTML_MODE_COMPACT)));

                TextView signal1TextView = layout.findViewById(R.id.signal1TextView);
                signal1TextView.setText(mainActivityViewModel.getSignals().get(0));
                signal1TextView.setVisibility(View.VISIBLE);
                signal1TextView.setMovementMethod(LinkMovementMethod.getInstance());

                if (mainActivityViewModel.getSignals().size() > 1) {
                    TextView signal2TextView = layout.findViewById(R.id.pauseCollectionTextView);
                    signal2TextView.setText(mainActivityViewModel.getSignals().get(1));
                    signal2TextView.setVisibility(View.VISIBLE);
                    signal2TextView.setMovementMethod(LinkMovementMethod.getInstance());

                    if (mainActivityViewModel.getSignals().size() > 2) {
                        TextView signal3TextView = layout.findViewById(R.id.signal3TextView);
                        signal3TextView.setText(mainActivityViewModel.getSignals().get(2));
                        signal3TextView.setVisibility(View.VISIBLE);
                        signal3TextView.setMovementMethod(LinkMovementMethod.getInstance());

                        if (mainActivityViewModel.getSignals().size() > 3) {
                            TextView signal4TextView = layout.findViewById(R.id.signal4TextView);
                            signal4TextView.setText(mainActivityViewModel.getSignals().get(3));
                            signal4TextView.setVisibility(View.VISIBLE);
                            signal4TextView.setMovementMethod(LinkMovementMethod.getInstance());

                            if (mainActivityViewModel.getSignals().size() > 4) {
                                TextView signal5TextView = layout.findViewById(R.id.signal5TextView);
                                signal5TextView.setText(mainActivityViewModel.getSignals().get(4));
                                signal5TextView.setVisibility(View.VISIBLE);
                                signal5TextView.setMovementMethod(LinkMovementMethod.getInstance());

                                if (mainActivityViewModel.getSignals().size() > 5) {
                                    TextView signal6TextView = layout.findViewById(R.id.signal6TextView);
                                    signal6TextView.setText(mainActivityViewModel.getSignals().get(5));
                                    signal6TextView.setVisibility(View.VISIBLE);
                                    signal6TextView.setMovementMethod(LinkMovementMethod.getInstance());
                                }
                            }
                        }
                    }
                }
            }


//            mainActivityViewModel.setTechnology(firstSignalState.getTechnology());
//            mainActivityViewModel.setOperatorName(firstSignalState.getOperaterName());
//            mainActivityViewModel.setSignalStrength(firstSignalState.getSignalStrength());
//
//            operatorNameTextView.setText(mainActivityViewModel.getOperatorName());
//            technologyTextView.setText(mainActivityViewModel.getTechnology());
//            signalStrengthTextView.setText(String.valueOf(mainActivityViewModel.getSignalStrength()));

//            Log.d("signalStrengthCaptured", String.valueOf(signalViewModel.getSignalStrength()));
//            Log.d("locationCaptured", fusedLocationClient.getCurrentLocation(PRIORITY_HIGH_ACCURACY, null));
        }
    }

    private void setClickSpanOnBuilder(SpannableStringBuilder builder) {
        final ImageSpan imageSpan = new ImageSpan(getActivity(), R.mipmap.about_us_tab, DynamicDrawableSpan.ALIGN_BASELINE);

        ClickableSpan clickSpan = new ClickableSpan() {
            @Override
            public void onClick(View clicked) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(R.string.contact_tsp_title);

                TextView msg = new TextView(getContext());
                msg.setText(R.string.contact_tsp_msg);
                msg.setPadding(20, 20, 20, 20);
                msg.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
                builder.setView(msg);
// Add the buttons
                builder.setPositiveButton(R.string.contact_tsp_ok, (dialog, id) -> {
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        };

        builder.setSpan(imageSpan, builder.length() - 1, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(clickSpan, builder.length() - 1, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    }


    private final ActivityResultLauncher<String[]> requestPermissionLauncher2 =
            registerForActivityResult(new ActivityResultContracts
                            .RequestMultiplePermissions(), result -> {
                        Boolean backgroundLocationGranted = result.get(
                                Manifest.permission.ACCESS_BACKGROUND_LOCATION);
//                        Log.i("combinedSignalNetworkHardwareState", "inside permission launcher");
                        if ((backgroundLocationGranted != null && backgroundLocationGranted)) {
//                            Log.i("combinedSignalNetworkHardwareState", "Got bg permission");
                        }
                    }
            );

}