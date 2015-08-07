package com.roundarch.codetest.part2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import com.roundarch.codetest.ProgressDialogFragment;
import com.roundarch.codetest.R;

public class EditFragment extends Fragment {
    public static final int RESULT_SAVE = 1;
    public static final String EXTRA_MODEL = "extra_model";

    private DataModel mModel; // TODO - needs to be provided from original Activity/Fragment
    private EditText edit1;
    private EditText edit2;
    private EditText edit3;

    // TODO - This fragment should allow you to edit the fields of DataModel
    // TODO - Then when you click the save button, it should get the DataModel back to the prior activity
    // TODO - so it's up to date
    @Override
    public View
    onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container);
        view.findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick_save();
            }
        });

        edit1 = (EditText) view.findViewById(R.id.editText1);
        edit2 = (EditText) view.findViewById(R.id.editText2);
        edit3 = (EditText) view.findViewById(R.id.editText3);

        setRetainInstance(true);

        return view;
    }

    private void modifyModelOperation(final DataModel model) {
        showLoadingDialog();
        refreshModelFromViews();

        // TODO - you need to implement swapText
        swapText(model);

        // TODO - the BlackBox simulates a slow operation, so you will need to update
        // TODO - this code to prevent it from blocking the main thread
        new LongOperation().execute();

        // TODO - once the model has been updated, you need to find a good way to
        // TODO - to provide it back to Part2Fragment in the MainActivity
        Part2Fragment fragment = (Part2Fragment) getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:pager:" +1);
        fragment.setModel(mModel);
        getActivity().finish();
    }

    private void showLoadingDialog() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        ProgressDialogFragment editNameDialog = new ProgressDialogFragment();
        editNameDialog.show(fm, "progress_dialog_fragment");
    }

    private void refreshModelFromViews() {
        // TODO - update our model from the views in our layout
    }

    // Modifies the data model to swap the values in text1 and text2
    private void swapText(DataModel model) {
        // TODO - swap the text1 and text2 fields on the data model
        String text1 = edit1.getText().toString();
        String text2 = edit2.getText().toString();

        model.setText1(text2);
        model.setText2(text1);
    }

    public void onClick_save() {
        modifyModelOperation(mModel);
    }

    // TODO - use this method from the Activity to set the model and update
    // TODO - the views in the layout.  You will need to implement the refreshViewsFromModel()
    // TODO - method as well
    public void setModel(DataModel model) {
        mModel = model;
        refreshViewsFromModel();
    }

    private void refreshViewsFromModel() {
        // TODO - update our views based on the model's state
        edit1.setText(mModel.getText1());
        edit2.setText(mModel.getText2());
        edit3.setText(String.valueOf(mModel.getText3()));
    }

    private class LongOperation extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            mModel.setText3(BlackBox.doMagic(mModel.getText3()));
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
        }

        @Override
        protected void onPreExecute() {
        }

    }
}
