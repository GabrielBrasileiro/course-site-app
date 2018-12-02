package br.com.universodoandroid.coursesiteapp.utils;

public interface RequestCallback<T> {
    void onSuccess(T result);
    void onError(String errorMessage);
}
