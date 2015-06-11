package com.example.kaoru.eventsequencelisttestrunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kaoru on 15/06/12.
 */
public class ExampleInSituDataSource extends InSituDataSource<SurfaceViewEvents> {
    public ExampleInSituDataSource() {
        super(SurfaceViewEvents.values());
    }

    @Override
    protected List<List<List<SurfaceViewEvents>>> getRawData() {
        SurfaceViewEvents[][] events = {
                { SurfaceViewEvents.surfaceCreated, SurfaceViewEvents.surfaceChanged, SurfaceViewEvents.surfaceDestroyed },
        };
        return TestUtils.para(events);
    }
}
