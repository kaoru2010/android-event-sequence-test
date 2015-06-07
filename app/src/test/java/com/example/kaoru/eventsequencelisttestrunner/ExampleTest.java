package com.example.kaoru.eventsequencelisttestrunner;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(EventSequenceListTestRunner.class)
@EventSequenceListTestRunner.DataSource(ExampleDataSource.class)
public class ExampleTest implements DataProviderAcceptor {
    private final int mIndex;
    private final DataProvider mDataProvider;

    public ExampleTest(Integer index, DataProvider dataProvider) {
        mIndex = index;
        mDataProvider = dataProvider;
    }

    @Test
    public void test1() {

    }

    @Override
    public void acceptDataProvider(int index, DataProvider dataProvider) {

    }
}
