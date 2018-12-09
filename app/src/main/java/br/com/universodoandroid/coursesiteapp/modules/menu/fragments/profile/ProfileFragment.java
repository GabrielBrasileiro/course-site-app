package br.com.universodoandroid.coursesiteapp.modules.menu.fragments.profile;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import br.com.universodoandroid.coursesiteapp.R;
import br.com.universodoandroid.coursesiteapp.databinding.FragmentProfileBinding;
import br.com.universodoandroid.coursesiteapp.domain.User;
import br.com.universodoandroid.coursesiteapp.modules.menu.MenuActivity;
import br.com.universodoandroid.coursesiteapp.services.FirebaseDatabaseProvider;

import static com.annimon.stream.Optional.ofNullable;

public class ProfileFragment extends Fragment implements ProfileContract.View {

    private FragmentProfileBinding mFragmentProfileBinding;
    private ProfileContract.Presenter mPresenter;

    public static Fragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ofNullable(((MenuActivity) getActivity())).ifPresent(activity -> activity.updateToolbarTitle("Profile"));

        new ProfilePresenter(this, new FirebaseDatabaseProvider(getActivity()));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentProfileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        mFragmentProfileBinding.setHandler(this);
        setInformationVisibility(View.GONE);

        mPresenter.getUserInformation();

        return mFragmentProfileBinding.getRoot();
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgressBar() {
        mFragmentProfileBinding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgressBar() {
        mFragmentProfileBinding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUserInformation(User user) {
        Picasso.with(getContext()).load(user.getPhotoUrl()).into(mFragmentProfileBinding.userImage);
        mFragmentProfileBinding.userNameTextView.setText(user.getName());
        mFragmentProfileBinding.userEmailTextView.setText(user.getEmail());
        mFragmentProfileBinding.userNumberCoursesTextView.setText("0");
        setInformationVisibility(View.VISIBLE);
    }

    private void setInformationVisibility(int visibility) {
        mFragmentProfileBinding.userNameTextView.setVisibility(visibility);
        mFragmentProfileBinding.userEmailTextView.setVisibility(visibility);
        mFragmentProfileBinding.userNumberCoursesTextView.setText(visibility);
    }

}
