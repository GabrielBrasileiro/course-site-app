package br.com.universodoandroid.coursesiteapp.modules.menu.fragments.dashboard;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.universodoandroid.coursesiteapp.R;
import br.com.universodoandroid.coursesiteapp.databinding.FragmentDashboardBinding;
import br.com.universodoandroid.coursesiteapp.modules.menu.MenuActivity;

import static com.annimon.stream.Optional.ofNullable;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding mFragmentDashboardBinding;

    public static Fragment newInstance() {
        return new DashboardFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ofNullable(((MenuActivity) getActivity())).ifPresent(activity -> activity.updateToolbarTitle("Dashboard"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentDashboardBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false);
        mFragmentDashboardBinding.setHandler(this);

        return mFragmentDashboardBinding.getRoot();
    }

}
