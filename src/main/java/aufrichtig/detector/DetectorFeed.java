package aufrichtig.detector;

import java.util.List;

public class DetectorFeed {

    Data data;
    Result result;

    class Data {
        List<Result> detections;
    }

    class Result {
        String language;
        Boolean isReliable;
        double confidence;
    }
}
