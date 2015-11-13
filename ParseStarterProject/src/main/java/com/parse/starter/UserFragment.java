package com.parse.starter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by floyd on 11/10/15.
 */
public class UserFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_user, container, false);

        return rootView;
    }
    public static UserFragment newInstance() {

        UserFragment f = new UserFragment();


        return f;
    }
}
