package aufrichtig.detector;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;

public class DetectorFrame extends JFrame {

    JLabel instr;
    JTextField text;
    String textEntry;
    JTextArea results;
    JButton detectLanguage;
    JPanel topPanel;
    JPanel bottomPanel;

    public DetectorFrame() {
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Language Detector");
        setLayout(new BorderLayout());


        instr = new JLabel("Enter text of any language in English characters");
        text = new JTextField();
        text.setPreferredSize(new Dimension(160, 200));
        textEntry = text.getText();
        results = new JTextArea();
        results.setPreferredSize(new Dimension(200, 200));
        detectLanguage = new JButton("Detect Language");
        topPanel = new JPanel();
        bottomPanel = new JPanel();

        topPanel.add(instr);
        topPanel.add(text);
        bottomPanel.add(detectLanguage);
        bottomPanel.add(results);

        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ws.detectlanguage.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DetectorService service = retrofit.create(DetectorService.class);

        detectLanguage.addActionListener(actionEvent -> {
            DetectorController controller = new DetectorController(service);
            controller.requestData(textEntry);
        });

    }

    public static void main(String[] args) {
        new DetectorFrame().setVisible(true);
    }

}