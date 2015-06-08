package com.example.kaoru.eventsequencelisttestrunner;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by kaoru on 15/06/09.
 */
public abstract class EventSequenceListTestCase {
    private final int mIndex;
    private final DataProvider mDataProvider;
    private final Method[] mMethods;

    public EventSequenceListTestCase(Integer index) throws Throwable {
        mIndex = index;

        Class<? extends DataProvider> clazz = getClass().getAnnotation(EventSequenceListTestRunner.DataSource.class).value();
        mDataProvider = clazz.newInstance();

        String[] labels = mDataProvider.getLabels();
        int count = labels.length;
        mMethods = new Method[count];
        for (int i = 0; i < count; i++) {
            mMethods[i] = getClass().getMethod(labels[i]);
        }
    }

    public int getIndex() {
        return mIndex;
    }

    public DataProvider getDataProvider() {
        return mDataProvider;
    }

    @Test
    public void runSequence() throws Throwable {
        int[][] data = getDataProvider().getData();
        for (int eventId : data[getIndex()]) {
            mMethods[eventId].invoke(this);
        }
    }
}
