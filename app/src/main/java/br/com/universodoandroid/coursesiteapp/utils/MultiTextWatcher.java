package br.com.universodoandroid.coursesiteapp.utils;

import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.universodoandroid.coursesiteapp.R;

public class MultiTextWatcher implements TextWatcher {

    View v;
    EditText[] edList;

    public MultiTextWatcher(EditText[] edList, Button v) {
        this.v = v;
        this.edList = edList;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {
        for (EditText editText : edList) {
            if (editText.getText().toString().trim().length() <= 0) {
                v.setEnabled(false);
                v.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.grey));
                break;
            }
            else v.setEnabled(true);
            v.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.black));
        }
    }

}