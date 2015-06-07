package com.example.kaoru.eventsequencelisttestrunner;

/**
 * Created by kaoru on 15/06/08.
 */
public class ExampleDataSource implements DataProvider {

    private static final int[][] DATA = {
            {0, 1, 2},
            {0, 2},
    };
    private static final String[] LABELS = {
            "surfaceViewCreated",
            "surfaceViewChanged",
            "surfaceViewDestroyed",
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
