package com.dot.nbm.fragments;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;

import com.dot.nbm.R;
import com.dot.nbm.doers.GsonHandler;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class AboutFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.fragment_about, container, false);

        TextView aboutUsTextView = layout.findViewById(R.id.aboutUsTextView);

        boolean shutdownState = GsonHandler.getPauseShutdownState(getContext());

        Spanned aboutUsText_1 = HtmlCompat.fromHtml(getString(R.string.about_us_1), HtmlCompat.FROM_HTML_MODE_COMPACT);
        Spanned aboutUsText_2 = HtmlCompat.fromHtml(getString(R.string.about_us_2), HtmlCompat.FROM_HTML_MODE_COMPACT);
        Spanned aboutUsText_3 = HtmlCompat.fromHtml(getString(R.string.about_us_3), HtmlCompat.FROM_HTML_MODE_COMPACT);

        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(aboutUsText_1).append(" ", new ImageSpan(getActivity(), R.mipmap.thanks_32, DynamicDrawableSpan.ALIGN_BASELINE), 0)
                .append(aboutUsText_2).append(" ", new ImageSpan(getActivity(), R.mipmap.nbm_32, DynamicDrawableSpan.ALIGN_BASELINE), 0).append(aboutUsText_3);

        if (!shutdownState) {
            Spanned aboutUsText_4 = HtmlCompat.fromHtml(getString(R.string.about_us_4), HtmlCompat.FROM_HTML_MODE_COMPACT);
            Spanned aboutUsText_5 = HtmlCompat.fromHtml(getString(R.string.about_us_5), HtmlCompat.FROM_HTML_MODE_COMPACT);

            builder.append(" ", new ImageSpan(getActivity(), R.mipmap.database_32, DynamicDrawableSpan.ALIGN_BASELINE), 0)
                    .append(aboutUsText_4).append(" ", new ImageSpan(getActivity(), R.mipmap.surety_bond_32, DynamicDrawableSpan.ALIGN_BASELINE), 0)
                    .append(aboutUsText_5);
        } else {
            Spanned aboutUsText_6 = HtmlCompat.fromHtml(getString(R.string.about_us_6), HtmlCompat.FROM_HTML_MODE_COMPACT);

            builder.append(" ", new ImageSpan(getActivity(), R.mipmap.finish_icon_32, DynamicDrawableSpan.ALIGN_BASELINE), 0)
                    .append(aboutUsText_6);
        }

        aboutUsTextView.setText(builder);

        return layout;
    }
}