package com.example.kaoru.eventsequencelisttestrunner;

public class ExampleDataSource implements DataProvider {

    public interface TestCase {
        void surfaceCreated();
        void surfaceChanged();
        void surfaceDestroyed();
    }

    private static final int[][] DATA = {
            {0, 1, 1, 2},
            {0, 1, 2},
            {0, 2},
    };

    private static final String[] LABELS = {
            "surfaceCreated",
            "surfaceChanged",
            "surfaceDestroyed",
    };

    @Override
    public int[][] getData() {
        return DATA;
    }

    @Override
    public String[] getLabels() {
        return LABELS;
    }

    @Override
    public String getName(int index) {
        StringBuilder sb = new StringBuilder();
        for (int event : getData()[index]) {
            sb.append(getLabels()[event]);
            sb.append(" ");
        }

        return sb.toString().trim();
    }
}
