package br.com.universodoandroid.coursesiteapp.modules;

public interface BaseView<T> {
    void setPresenter(T presenter);
    void showProgressBar();
    void dismissProgressBar();
}
