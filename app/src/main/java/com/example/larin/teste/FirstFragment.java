package com.example.larin.teste;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.FragmentManager;

/**
 * Created by larin on 6/26/2017.
 */

public class FirstFragment extends Fragment {
    View myview;
    @Nullable
    @Override



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.calibrate, container, false);
        return myview;
    }
}
