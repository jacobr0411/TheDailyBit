import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DailyBitGUI extends JFrame{
    private JTextField Input;
    private JList<Object> HeadLines;
    private JButton searchConfirm;
    private JPanel rootPanel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;

    public DailyBitGUI() throws IOException {
        JSONParser parser = new JSONParser();
        InputStream stream = null;
        SourceSearch sourceSearch = new SourceSearch();

        sourceSearch.connectToAPI();
        stream = sourceSearch.pullInputStream();
        parser.getArticles(stream);

        parser.getTitleList();//test stream is not null

        Object[] data = parser.TitleList().toArray();


        add(rootPanel);

        HeadLines.setListData(data);
        HeadLines.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        HeadLines.setLayoutOrientation(JList.VERTICAL);
        HeadLines.setVisibleRowCount(-1);

//        JScrollPane listScroller = new JScrollPane(HeadLines);
//        listScroller.setPreferredSize(new Dimension(250, 80));


        setTitle("Daily Bit");
        setSize(700,700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        searchConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(rootPanel,"something");


            }
        });
    }
}

