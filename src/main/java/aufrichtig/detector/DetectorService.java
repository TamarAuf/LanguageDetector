package aufrichtig.detector;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DetectorService {

    @GET("/0.2/detect?key=92a58a7615c9eea04ec738b522287000")
    Call<DetectorFeed> getLanguage(@Query("q") String textEntry);

}
