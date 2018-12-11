package br.com.universodoandroid.coursesiteapp.modules.menu.fragments.courses.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.universodoandroid.coursesiteapp.R;
import br.com.universodoandroid.coursesiteapp.databinding.CourseItemBinding;
import br.com.universodoandroid.coursesiteapp.domain.Course;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ItemViewHolder> {

    private List<Course> mCourses;

    public CourseAdapter() {
        mCourses = new ArrayList<>(0);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.course_item,
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder viewHolder, int position) {
        Course course = mCourses.get(position);

        viewHolder.updateView(course);
    }

    private void setExpansionStatus(Button button, TextView textView) {
        boolean buttonIsVisible = button.getVisibility() == VISIBLE;
        boolean textViewIsVisible = textView.getVisibility() == VISIBLE;

        if (buttonIsVisible && textViewIsVisible) {
            button.setVisibility(GONE);
            textView.setVisibility(GONE);
        } else {
            button.animate();
            button.setVisibility(VISIBLE);
            textView.setVisibility(VISIBLE);
        }
    }

    private void add(Course schedule) {
        mCourses.add(schedule);
        notifyItemInserted(mCourses.size());
    }

    public void addAll(List<Course> schedules) {
        for (Course schedule : schedules) {
            add(schedule);
        }
    }

    public void clearData() {
        mCourses = new ArrayList<>();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private CourseItemBinding mScheduleItemBinding;

        ItemViewHolder(CourseItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.mScheduleItemBinding = itemBinding;
        }

        void updateView(Course schedule) {
            this.mScheduleItemBinding.setCourse(schedule);
            this.mScheduleItemBinding.executePendingBindings();
        }
    }

}
