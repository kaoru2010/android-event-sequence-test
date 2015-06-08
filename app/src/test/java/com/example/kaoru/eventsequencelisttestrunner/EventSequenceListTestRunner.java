package com.example.kaoru.eventsequencelisttestrunner;

import org.junit.runner.Runner;
import org.junit.runners.Suite;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kaoru on 15/06/08.
 */
public class EventSequenceListTestRunner extends Suite {
    private final List<Runner> mRunners = new ArrayList<>();

    public EventSequenceListTestRunner(Class<?> klass) throws Throwable {
        super(klass, Collections.<Runner>emptyList());

        DataSource dataSource = klass.getAnnotation(DataSource.class);
        Class<? extends DataProvider> dataClass = dataSource.value();
        DataProvider dataProvider = dataClass.newInstance();

        for (int index = 0; index < dataProvider.getData().length; index++) {
            mRunners.add(new EventSequenceTestRunner(klass, index, dataProvider));
        }
    }

    @Override
    protected List<Runner> getChildren() {
        return mRunners;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface DataSource {
        Class<? extends DataProvider> value();
    }

    private static class EventSequenceTestRunner extends RobolectricTestRunner {

        private final Integer mIndex;
        private final DataProvider mDataProvider;
        private final Class<?> mTestClass;

        public EventSequenceTestRunner(Class<?> testClass, int index, DataProvider dataProvider) throws InitializationError {
            super(testClass);
            mIndex = index;
            mDataProvider = dataProvider;
            mTestClass = testClass;
        }

        @Override
        protected String testName(FrameworkMethod method) {
            return method.getName() + " #" + mIndex + " " + getName();
        }

        @Override
        protected String getName() {
            return mDataProvider.getName(mIndex);
        }

        @Override
        protected void validateConstructor(List<Throwable> errors) {
        }

        @Override
        protected HelperTestRunner getHelperTestRunner(final Class bootstrappedTestClass) {
            try {
                return new HelperTestRunner(bootstrappedTestClass) {
                    @Override
                    protected Object createTest() throws Exception {
                        Constructor constructor = bootstrappedTestClass.getConstructor(Integer.class);
                        return constructor.newInstance(mIndex);
                    }

                    @Override
                    protected void validateConstructor(List<Throwable> errors) {
                    }
                };
            } catch (InitializationError initializationError) {
                initializationError.printStackTrace();
                throw new RuntimeException(initializationError);
            }
        }
    }
}
