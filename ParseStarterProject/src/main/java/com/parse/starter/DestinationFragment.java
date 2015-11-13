package com.parse.starter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Created by floyd on 11/10/15.
 */
public class DestinationFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_destination, container, false);

        return rootView;
    }
    public static DestinationFragment newInstance() {

        DestinationFragment f = new DestinationFragment();

        return f;
    }
}
