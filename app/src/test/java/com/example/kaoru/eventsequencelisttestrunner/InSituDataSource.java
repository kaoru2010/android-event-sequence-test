package com.example.kaoru.eventsequencelisttestrunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kaoru on 15/06/12.
 */
public abstract class InSituDataSource<T extends Enum<?>> implements DataProvider {
    private int[][] mData = null;
    private final String[] mLabels;
    private String[] mNames = null;

    public InSituDataSource(T[] values) {
        mLabels = new String[values.length];
        for (int i = 0; i < mLabels.length; i++) {
            mLabels[i] = values[i].name();
        }
    }

    protected abstract List<List<List<T>>> getRawData();

    @Override
    public int[][] getData() {
        if (mData == null) {
            setUp();
        }

        return mData;
    }

    @Override
    public String[] getLabels() {
        return mLabels;
    }

    @Override
    public String getName(int index) {
        if (mNames == null) {
            setUp();
        }
        return mNames[index];
    }

    private void setUp() {
        List<List<List<T>>> data = getRawData();
        mData = new int[data.size()][];
        for (int index = 0; index < mData.length; index++) {
            List<T> row = data.get(index);
            mData[index] = new int[row.size()];
            for (int column = 0; column < mData[index].length; column++) {
                mData[index][column] = row.get(column).ordinal();
            }
        }

        mNames = new String[data.size()];
        for (int i = 0; i < mNames.length; i++) {
            mNames[i] = data.get(i).toString();
        }
    }
}
