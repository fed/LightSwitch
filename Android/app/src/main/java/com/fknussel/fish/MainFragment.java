package com.fknussel.fish;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.fknussel.fish.networking.RaspiClient;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainFragment extends Fragment {

    Button toggle_lights;
    ImageView lightbulb;
    iMainActivity mActivity;
    Boolean on = true;
    RaspiClient.Raspi raspi = RaspiClient.getRaspiInterface();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mActivity = (iMainActivity) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement iMainActivity");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        toggle_lights = (Button) rootView.findViewById(R.id.toggle_lights);

        lightbulb = (ImageView) rootView.findViewById(R.id.lightbulb);

        toggle_lights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                raspi.toggleLights(new Callback<RaspiClient.Lights>() {
                    @Override
                    public void success(RaspiClient.Lights lights, Response response) {
                        setStatus(lights.on());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //Algo exploto (server not found, no pudo parsear la respuesta, se corto internet etc etc)
                    }
                });

            }
        });

        raspi.getStatus(new Callback<RaspiClient.Lights>() {
            @Override
            public void success(RaspiClient.Lights status, Response response) {
                setStatus(status.on());
                Log.e("1st time SUCCESS", status.on().toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("1st time ERROR", error.toString());
            }
        });

        return rootView;
    }


    private void setStatus(Boolean status) {
        if (status) {
            toggle_lights.setText("Turn Lights OFF");
            toggle_lights.setBackgroundColor(getResources().getColor(R.color.red));
            lightbulb.setAlpha(1.0f);
            on = true;
        } else {
            toggle_lights.setText("Turn Lights ON");
            toggle_lights.setBackgroundColor(getResources().getColor(R.color.green));
            lightbulb.setAlpha(0.4f);
            on = false;
        }
    }
}