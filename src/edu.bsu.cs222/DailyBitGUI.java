import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DailyBitGUI extends JFrame {
    private JTextField Input;
    private JList<Object> HeadLines;
    private JButton searchConfirm;
    private JPanel rootPanel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private String newline = "\n";
    //private JSONParser parser = new JSONParser();


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

        ListSelectionModel listSelectionModel;
        listSelectionModel = HeadLines.getSelectionModel();
        listSelectionModel.addListSelectionListener(
                new SharedListSelectionHandler());

//        JScrollPane listScroller = new JScrollPane(HeadLines);
//        listScroller.setPreferredSize(new Dimension(250, 80));


        setTitle("Daily Bit");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



        searchConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(rootPanel, "something");


            }
        });


    }

    private class SharedListSelectionHandler implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            JSONParser parser = new JSONParser();
            InputStream stream = null;
            SourceSearch sourceSearch = new SourceSearch();

            try {
                sourceSearch.connectToAPI();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                stream = sourceSearch.pullInputStream();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            parser.getArticles(stream);
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();

            int firstIndex = e.getFirstIndex();
            int lastIndex = e.getLastIndex();
            boolean isAdjusting = e.getValueIsAdjusting();
            try {
//                output.append(parser.getURLContent(firstIndex));
                JOptionPane.showMessageDialog(rootPanel, parser.getURLContent(firstIndex));

            } catch (Exception ex) {
                ex.printStackTrace();
            }
//            output.append("Event for indexes "
//                    + firstIndex + " - " + lastIndex
//                    + "; isAdjusting is " + isAdjusting
//                    + "; selected indexes:");
//
//            if (lsm.isSelectionEmpty()) {
//                output.append(" <none>");
//            }
//            else {
//                int minIndex = lsm.getMinSelectionIndex();
//                int maxIndex = lsm.getMaxSelectionIndex();
//                for (int i = minIndex; i <= maxIndex; i++) {
//                    if (lsm.isSelectedIndex(i)) {
//                        output.append(" " + i);
//                    }
//                }
//            }
//            output.append(newline);
        }
    }
}

