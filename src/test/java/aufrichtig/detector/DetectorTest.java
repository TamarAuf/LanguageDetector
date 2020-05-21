package aufrichtig.detector;

import aufrichtig.detector.DetectorFeed.Result;
import aufrichtig.detector.DetectorFeed.Data;
import org.junit.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class DetectorTest {

    @Test
    public void detectLanguage() throws IOException {
        // given
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ws.detectlanguage.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DetectorService service = retrofit.create(DetectorService.class);

        // when
        DetectorFeed feed = service.getLanguage("Hakutakuwa na ndizi leo").execute().body();

        // then
        assertNotNull(feed);

        Result result = feed.data.detections.get(0);

        assertNotNull(result);

        System.out.println("Language: " + result.language);
        System.out.println("Is reliable: " + result.isReliable);
        System.out.println("Confidence: " + result.confidence);

    }
}