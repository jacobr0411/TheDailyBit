import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class DailyBitGUI extends JFrame{
    private JButton Source;
    private JButton Country;
    private JTextField Input;
    private JList HeadLines;
    private JButton searchConfirm;
    private JPanel rootPanel;

    public DailyBitGUI() throws IOException {
        JSONParser parser = new JSONParser();
        InputStream stream = null;
        SourceSearch sourceSearch = new SourceSearch();

        stream = sourceSearch.pullInputStream();
        add(rootPanel);

        setTitle("Daily Bit");
        setSize(400,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        searchConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(rootPanel,"something");


            }
        });
    }
}

