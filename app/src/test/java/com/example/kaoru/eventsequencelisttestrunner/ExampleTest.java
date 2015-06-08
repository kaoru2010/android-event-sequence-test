package com.example.kaoru.eventsequencelisttestrunner;

import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

@RunWith(EventSequenceListTestRunner.class)
@Config(emulateSdk = 18)
@EventSequenceListTestRunner.DataSource(ExampleDataSource.class)
public class ExampleTest extends EventSequenceListTestCase implements ExampleDataSource.TestCase {

    public ExampleTest(Integer index) throws Throwable {
        super(index);
    }

    @Override
    public void surfaceViewCreated() {

    }

    @Override
    public void surfaceViewChanged() {

    }

    @Override
    public void surfaceViewDestroyed() {

    }
}
