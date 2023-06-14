package com.dot.nbm.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;

import com.dot.nbm.R;

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
        aboutUsTextView.setText(HtmlCompat.fromHtml(getString(R.string.about_us), HtmlCompat.FROM_HTML_MODE_COMPACT));

        return layout;
    }
}