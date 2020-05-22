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

    boolean check = false;

    public DetectorFrame() {
        setSize(600, 350);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Language Detector");
        setLayout(new BorderLayout());


        instr = new JLabel("Enter text of any language");
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
            language.setText("");
            reliability.setText("");
            confidence.setText("");
            textEntry = text.getText();
            checkWord(textEntry);
            if (check) {
                controller.requestData(textEntry, language, reliability, confidence);
            } else {
                language.setText("Invalid entry. Try again.");
            }
        });
    }

    private void checkWord(String textEntry) {

        if (textEntry == null || textEntry.equals("")) {
            check = false;
        } else {
            check = true;
            int len = textEntry.length();
            for (int i = 0; i < len; i++) {
                if (Character.isLetter(textEntry.charAt(i))) {
                    check = true;
                    break;
                } else {
                    check = false;
                }
            }
        }

    }

    public static void main(String[] args) {
        new DetectorFrame().setVisible(true);
    }

}