package br.com.universodoandroid.coursesiteapp.modules.registercourse;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import br.com.universodoandroid.coursesiteapp.R;
import br.com.universodoandroid.coursesiteapp.databinding.ActivityNewCourseBinding;
import br.com.universodoandroid.coursesiteapp.services.FirebaseDatabaseCourseProvider;
import br.com.universodoandroid.coursesiteapp.utils.MultiTextWatcher;

public class NewCourseActivity extends AppCompatActivity implements NewCourseContract.View {

    private ActivityNewCourseBinding mActivityNewCourseBinding;
    private ProgressDialog mProgressDialog;
    private NewCourseContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new NewCoursePresenter(this, new FirebaseDatabaseCourseProvider(this));

        mActivityNewCourseBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_course);
        mActivityNewCourseBinding.setHandler(this);

        mActivityNewCourseBinding.backButton.setOnClickListener(v -> finish());

        configureRegisterClick();

        checkTextViews(mActivityNewCourseBinding.titleEditText, mActivityNewCourseBinding.descriptionEditText);
    }

    private void configureRegisterClick() {
        mActivityNewCourseBinding.registerButton.setOnClickListener(v -> {
            String name = mActivityNewCourseBinding.titleEditText.getText().toString().trim();
            String value = mActivityNewCourseBinding.descriptionEditText.getText().toString().trim();

            mPresenter.registerCourse(name, value, null, null, null);
        });

        mActivityNewCourseBinding.registerButton.setEnabled(false);
        mActivityNewCourseBinding.registerButton.setBackgroundColor(ContextCompat.getColor(this, R.color.grey));
    }

    private void checkTextViews(EditText... editTexts) {
        MultiTextWatcher multiTextWatcher = new MultiTextWatcher(
                editTexts, mActivityNewCourseBinding.registerButton);

        for (EditText editText : editTexts) {
            editText.addTextChangedListener(multiTextWatcher);
        }
    }

    @Override
    public void setPresenter(NewCourseContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgressBar() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Aguarde...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    @Override
    public void dismissProgressBar() {
        mProgressDialog.dismiss();
    }

    @Override
    public void onFinishPost() {
        finish();
    }

}
