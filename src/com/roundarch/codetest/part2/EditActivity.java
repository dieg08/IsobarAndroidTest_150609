package com.roundarch.codetest.part2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.roundarch.codetest.R;

public class EditActivity extends FragmentActivity {
    private static String EXTRA_MODEL = "extra_model";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_edit);

        // TODO - you will need to obtain the model object provided to this activity and provide it to the EditFragment
        DataModel mModel =(DataModel) getIntent().getExtras().getSerializable(EXTRA_MODEL);
        EditFragment fragment = (EditFragment) getSupportFragmentManager().findFragmentById(R.id.edit_fragment);
        fragment.setModel(mModel);
    }
}
