package com.roundarch.codetest.part1;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.roundarch.codetest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mdigiovanni on 8/15/13.
 */
public class Part1Fragment extends Fragment {
    // TODO - any member variables you need to store?
    private SeekBar seekBar;
    private SeekBar seekTwo;
    private Switch switch1;
    private int progress2;

    public int getProgress2() {
        return progress2;
    }

    public void setProgress2(int progress2) {
        this.progress2 = progress2;
    }

    public int getProgress1() {
        return progress1;
    }

    public void setProgress1(int progress1) {
        this.progress1 = progress1;
    }

    private int progress1;
    //FIXME: Improve something! Anything
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_part1, null);
        initializeVariables(view);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    seekBar.setProgress(0);
                    seekTwo.setProgress(0);
                }
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            Context context = getActivity().getApplicationContext();

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                if (switch1.isChecked()) {
                    seekTwo.setProgress(progresValue);
                }
                setProgress1(progresValue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {int result = 0;
                if (getProgress2() > getProgress1()) {
                    result = getProgress2() - getProgress1();
                } else if (getProgress1() > getProgress2()) {
                    result = getProgress1() - getProgress2();
                }
                Toast.makeText(context, "Difference: " + result , Toast.LENGTH_SHORT).show();
            }
        });

        seekTwo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            Context context = getActivity().getApplicationContext();
            @Override
            public void onProgressChanged(SeekBar seekBar1, int progresValue, boolean fromUser) {
                if (switch1.isChecked()) {
                    seekBar.setProgress(progresValue);
                }
                setProgress2(progresValue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int result = 0;
                if (getProgress2() > getProgress1()) {
                    result = getProgress2() - getProgress1();
                } else if (getProgress1() > getProgress2()) {
                    result = getProgress1() - getProgress2();
                }
                Toast.makeText(context, "Difference: " + result , Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    // A private method to help us initialize our variables.
    private void initializeVariables(View view) {
        seekBar = (SeekBar) view.findViewById(R.id.seekBar1);
        seekTwo = (SeekBar) view.findViewById(R.id.seekBar2);
        switch1 = (Switch) view.findViewById(R.id.switch1);
    }
}
