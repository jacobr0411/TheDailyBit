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

    DailyBitGUI() throws IOException {
        SourceSearch sourceSearch = new SourceSearch();
        JSONParser parser = new JSONParser();
        InputStream stream;
        Object[] data;

        sourceSearch.connectToAPI();
        stream = sourceSearch.pullInputStream();
        parser.printArticles(stream);
        parser.printTitleList();

        data = parser.TitleList().toArray();

        add(rootPanel);

        HeadLines.setListData(data);
        HeadLines.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        HeadLines.setLayoutOrientation(JList.VERTICAL);
        HeadLines.setVisibleRowCount(-1);

//        JScrollPane listScroller = new JScrollPane(HeadLines);
//        listScroller.setPreferredSize(new Dimension(250, 80));

        setTitle("The Daily Bit");
        setSize(700,700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        searchConfirm.addActionListener(e -> JOptionPane.showMessageDialog(rootPanel,"something"));
    }
}

