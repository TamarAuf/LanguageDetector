package aufrichtig.detector;

import java.util.List;

public class DetectorFeed {

    Data data;

    class Data {
        List<Language> detections;
    }

    class Language {
        String language;
        Boolean isReliable;
        double confidence;
    }
}
