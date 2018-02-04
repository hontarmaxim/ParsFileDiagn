package diagn;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import javax.swing.event.PopupMenuListener;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.io.FilenameFilter;
import java.nio.file.*;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by root on 1/21/18.
 */
public class pars {
    private JButton nameMain;
    private JProgressBar progressBar1;
    private JPanel panelMain;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTabbedPane tabbedPane1;

    public static boolean checkWithRegExp(String userNameString, String RegName){
        Pattern p = Pattern.compile(RegName);
        Matcher m = p.matcher(userNameString);
        return m.matches();
    }


    public pars() {

        File[] roots = File.listRoots();
        for(File root: roots) {
            if (root.isDirectory())
            comboBox1.addItem(root.getAbsolutePath());
        }

        nameMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,roots);
            }


        });

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBox2.removeAllItems();
                File dir = new File(comboBox1.getSelectedItem()+"");
                File[] children = dir.listFiles();
                for (File file : children) {
                    if(checkWithRegExp(file.getAbsolutePath(),".*_20400.*")) {
                        comboBox2.addItem(file.getAbsolutePath());
                    }
                }
            }
        });


    }

    public static void main(String[] args)
    {

       JFrame frame = new JFrame("pars");
       frame.setContentPane(new pars().panelMain);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.pack();
       frame.setVisible(true);



    }

}
