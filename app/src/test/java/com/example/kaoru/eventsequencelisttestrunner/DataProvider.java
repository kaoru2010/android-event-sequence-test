package com.example.kaoru.eventsequencelisttestrunner;

/**
 * Created by kaoru on 15/06/08.
 */
public interface DataProvider {
    int[][] getData();

    String[] getLabels();

    String getName(int index);
}
