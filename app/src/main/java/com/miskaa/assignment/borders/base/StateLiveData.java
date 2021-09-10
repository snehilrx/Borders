package com.miskaa.assignment.borders.base;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import java.util.concurrent.atomic.AtomicReference;

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
     *
     * @param throwable the error to be handled
     */
    public void postError(Throwable throwable) {
        postValue(new StateData<T>().error(throwable));
    }

    /**
     * Use this to put the Data on a SUCCESS DataStatus
     *
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


    public abstract void fallback(StateLiveData<T> s);

    public abstract boolean isEmpty(T t);

}
