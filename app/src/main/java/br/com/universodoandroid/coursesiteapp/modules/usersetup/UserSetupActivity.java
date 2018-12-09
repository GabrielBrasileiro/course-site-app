package br.com.universodoandroid.coursesiteapp.modules.usersetup;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import br.com.universodoandroid.coursesiteapp.R;
import br.com.universodoandroid.coursesiteapp.databinding.ActivityUserSetupBinding;
import br.com.universodoandroid.coursesiteapp.modules.menu.MenuActivity;
import br.com.universodoandroid.coursesiteapp.modules.usersetup.UserSetupContract.Presenter;
import br.com.universodoandroid.coursesiteapp.services.FirebaseStorageProvider;

import static br.com.universodoandroid.coursesiteapp.constants.Constants.GALLERY_REQUEST;

public class UserSetupActivity extends AppCompatActivity implements UserSetupContract.View {

    private ActivityUserSetupBinding mActivityUserSetupBinding;

    private ProgressDialog mProgress;
    private Uri mImageUri = null;
    private FirebaseAuth mAuth;
    private Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new UserSetupPresenter(this, new FirebaseStorageProvider(this));

        mActivityUserSetupBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_setup);
        mActivityUserSetupBinding.setHandler(this);

        mAuth = FirebaseAuth.getInstance();

        configureSubmitButton();
        configureGalleryButton();
    }

    private void configureGalleryButton() {
        mActivityUserSetupBinding.userImageButton.setOnClickListener(v -> {
            Intent galleryIntent = new Intent();
            galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
            galleryIntent.setType("image/*");
            startActivityForResult(galleryIntent, GALLERY_REQUEST);
        });
    }

    private void configureSubmitButton() {
        mActivityUserSetupBinding.sendUserInformationButton.setOnClickListener(view -> {
            final String name = mActivityUserSetupBinding.userNameEditText.getText().toString().trim();
            final String userId = mAuth.getCurrentUser().getUid();

            if (mImageUri == null) {
                Toast.makeText(getApplicationContext(), "Adicione uma imagem a postagem!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(name)) {
                Toast.makeText(getApplicationContext(), "Adicione o seu nome para continuar!", Toast.LENGTH_SHORT).show();
                return;
            }

            mPresenter.insertUserSettings(userId, mImageUri, name);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST && resultCode == Activity.RESULT_OK) {
            Uri imageUri = data.getData();

            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .start(this);
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                mImageUri = result.getUri();
                mActivityUserSetupBinding.userImageButton.setImageURI(mImageUri);
            }
        }
    }

    @Override
    public void setPresenter(Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgressBar() {
        mProgress = new ProgressDialog(this);
        mProgress.setMessage("Enviando configuração final...");
        mProgress.show();
    }

    @Override
    public void dismissProgressBar() {
        mProgress.dismiss();
    }

    @Override
    public void onInsertSuccess() {
        Intent setupIntent = new Intent(this, MenuActivity.class);
        setupIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(setupIntent);
        finish();
    }

    @Override
    public void onInsertFailure(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

}
