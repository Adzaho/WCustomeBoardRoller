package planetRolls;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.table.DefaultTableModel;

public class planetRolls {
    private JTable planetTab;
    private JPanel panelPlGen;
    private JTextField planetTextF;
    private JTextField planetQtyIn;
    private JButton buttonGenPl;
    private JButton buttonSavePl;
    private JScrollPane planetTabScrl;
    private String pSize;
    private ArrayList<String> arrayPSize = new ArrayList<>();
    private PropertyChangeSupport propChangeSupport = new PropertyChangeSupport(this);
    private int colQty;
    DefaultTableModel tableModel = new DefaultTableModel(colQty, 1);

    public planetRolls() {
        buttonGenPl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pQty = planetQtyIn.getText();
                int intPQty =0;
                try {
                    intPQty = Integer.parseInt(pQty);
                    System.out.println(intPQty);
                    colQty = intPQty;
                } catch (NumberFormatException t) {
                    System.err.println("Invalid string format! Enter only integers");
                }

                Random rand = new Random();
                while (0 < intPQty) {
                    int randomNum = rand.nextInt(100);
                    if (randomNum == 100) {
                        pSize = "Double 100";
                    } else if (randomNum >= 91) {
                        pSize = "Huge 91-99";
                    } else if (randomNum >= 67) {
                        pSize = "Large 67-90";
                    } else if (randomNum >= 36) {
                        pSize = "Medium 36-66";
                    } else if (randomNum >= 16) {
                        pSize = "Small 16-35";
                    } else {
                        pSize = "Tiny 1-15";
                    }
                    arrayPSize.add(pSize);
                    intPQty--;
                }

                //DefaultTableModel tableModel = new DefaultTableModel(colQty, 1);

                tableModel.addColumn(arrayPSize);

                //planetTab.add(arrayPSize);
                //propChangeSupport
                System.out.println(arrayPSize);

                arrayPSize.clear();
            }
        });
        planetTab.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                propChangeSupport.firePropertyChange(evt);
                JTable planetTab = new JTable(tableModel);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Warhammer GiF roller");
        frame.setContentPane(new planetRolls().panelPlGen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


//    public void toExcel(JTable table, File file){
//        try{
//            TableModel model = table.getModel();
//            FileWriter excel = new FileWriter(file);
//
//            for(int i = 0; i < model.getColumnCount(); i++){
//                excel.write(model.getColumnName(i) + "\t");
//            }
//
//            excel.write("\n");
//
//            for(int i=0; i< model.getRowCount(); i++) {
//                for(int j=0; j < model.getColumnCount(); j++) {
//                    excel.write(model.getValueAt(i,j).toString()+"\t");
//                }
//                excel.write("\n");
//            }
//
//            excel.close();
//
//        }catch(IOException e){ System.out.println(e); }
//    }
}
