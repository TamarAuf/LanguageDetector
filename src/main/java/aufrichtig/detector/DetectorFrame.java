package aufrichtig.detector;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;

public class DetectorFrame extends JFrame {

    JLabel instr;
    JTextField text;
    String textEntry;
    JLabel language;
    JLabel reliability;
    JLabel confidence;
    JButton detectLanguage;
    JPanel topPanel;
    JPanel midPanel;
    JPanel bottomPanel;

    public DetectorFrame() {
        setSize(600, 350);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Language Detector");
        setLayout(new BorderLayout());


        instr = new JLabel("Enter text of any language in English characters");
        text = new JTextField();
        text.setPreferredSize(new Dimension(160, 200));
        textEntry = text.getText();
        language = new JLabel();
        reliability = new JLabel();
        confidence = new JLabel();
        detectLanguage = new JButton("Detect Language");
        topPanel = new JPanel();
        midPanel = new JPanel();
        bottomPanel = new JPanel();

        topPanel.add(instr);
        midPanel.add(text);
        bottomPanel.add(detectLanguage);
        bottomPanel.add(language);
        bottomPanel.add(reliability);
        bottomPanel.add(confidence);

        add(topPanel, BorderLayout.NORTH);
        add(midPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ws.detectlanguage.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DetectorService service = retrofit.create(DetectorService.class);

        DetectorController controller = new DetectorController(service);

        detectLanguage.addActionListener(actionEvent -> {
            textEntry = text.getText();
            if(textEntry != ""){
                controller.requestData(textEntry, language, reliability, confidence);
            }
            else{
                language.setText("Invalid entry. Try again");
            }
        });

    }

    public static void main(String[] args) {
        new DetectorFrame().setVisible(true);
    }

}