package aufrichtig.detector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

public class DetectorController {
        private DetectorService service;

        public DetectorController(DetectorService service) {
            this.service = service;
        }

        public void requestData(String textEntry)  {
            service.getLanguage(textEntry).enqueue(new Callback<DetectorFeed>() {
                @Override
                public void onResponse(Call<DetectorFeed> call, Response<DetectorFeed> response) {
                    DetectorFeed.Language result = response.body().data.detections.get(0);
                }

                @Override
                public void onFailure(Call<DetectorFeed> call, Throwable t) {

                }
            });
        }

}