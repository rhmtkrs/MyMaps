package com.app.mymaps.ui.about;
//10120150 - Rahmat - IF4

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.app.mymaps.R;

public class FragmentAboutPage extends Fragment {

    public FragmentAboutPage() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_page, container, false);
    }
}