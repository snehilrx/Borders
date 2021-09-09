package com.miskaa.assignment.borders.base;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import com.miskaa.assignment.borders.backend.room.model.Country;

import java.util.List;
import java.util.concurrent.Executors;

/**
 * Copied from - https://stackoverflow.com/questions/44208618/how-to-handle-error-states-with-livedata
**/
public abstract class StateLiveData<T> extends MediatorLiveData<StateData<T>> {

    /**
     * Use this to put the Data on a LOADING Status
     */
    public void postLoading() {
        postValue(new StateData<T>().loading());
    }

    /**
     * Use this to put the Data on a ERROR DataStatus
     * @param throwable the error to be handled
     */
    public void postError(Throwable throwable) {
        postValue(new StateData<T>().error(throwable));
    }

    /**
     * Use this to put the Data on a SUCCESS DataStatus
     * @param data
     */
    public void postSuccess(T data) {
        postValue(new StateData<T>().success(data));
    }

    /**
     * Use this to put the Data on a COMPLETE DataStatus
     */
    public void postComplete() {
        postValue(new StateData<T>().complete());
    }

    public void attach(LiveData<T> listCountries) {
        this.addSource(listCountries, (x) -> {
            if(isEmpty(x)) fallback(this);
            else{
                postSuccess(x);
            }
        });
    }

    public abstract void fallback(StateLiveData<T> s);

    public abstract boolean isEmpty(T t);

}
