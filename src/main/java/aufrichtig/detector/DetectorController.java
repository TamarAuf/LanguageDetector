package aufrichtig.detector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.*;


public class DetectorController {
        private DetectorService service;

        public DetectorController(DetectorService service) {
            this.service = service;
        }

        public void requestData(String textEntry, JLabel language, JLabel reliability, JLabel confidence)  {
            service.getLanguage(textEntry).enqueue(new Callback<DetectorFeed>() {
                @Override
                public void onResponse(Call<DetectorFeed> call, Response<DetectorFeed> response) {
                    if (response.body() != null) {
                        DetectorFeed.Result result = response.body().data.detections.get(0);
                        language.setText("Language: " + result.language);
                        reliability.setText("Is Reliable: " + result.isReliable);
                        confidence.setText("Confidence Score: " + result.confidence);
                    }
                    else language.setText("You have not entered text.");

                }

                @Override
                public void onFailure(Call<DetectorFeed> call, Throwable t) {
                    language.setText("There was an error.");
                    t.printStackTrace();
                }
            });
        }

}