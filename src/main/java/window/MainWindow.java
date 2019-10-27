package window;

import service.Nslookup;


import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 *
 */
public class MainWindow {

    JFrame mainWindow;
    JPanel jPanel;
    JTextField jTextField;
    JLabel jLabelForTextField;
    JButton jButton;


    Nslookup checkLink = new Nslookup();
    String URL = "";

    public MainWindow() {
        mainWindow = new JFrame("Tool for getting Akamai Staging IP");
        mainWindow.setPreferredSize(new Dimension(450, 280));



        jTextField = new JTextField(20);
        jTextField.setBackground(Color.LIGHT_GRAY);
        jLabelForTextField = new JLabel("Type URL:");
        jButton = new JButton("Get Akamai Staging IP ");


        jPanel = new JPanel();
        jPanel.add(jButton);
        jPanel.add(jLabelForTextField);
        jPanel.add(jTextField);
        jButton.addActionListener( e ->
            {
                if(e.getSource().equals(jButton)) {
                    try {
                        URL = checkLink.getAkamaiStaging(jTextField.getText());
                        System.out.println(URL);
                    } catch (Exception e1) {
                        System.out.println(e1.getMessage());
                        URL = e1.getMessage();
                    }
                    int input = JOptionPane.showOptionDialog(mainWindow, URL, "Redirect Link",JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Copy", "Close"}, "default");
                    UIManager.put("OptionPane.cancelButtonText", "Close");
                    UIManager.put("OptionPane.okButtonText", "Copy");
                    if(input == JOptionPane.OK_OPTION) {
                        StringSelection stringSelection = new StringSelection(URL);
                        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                        clpbrd.setContents(stringSelection, null);
                    }
                }

        });

        mainWindow.getContentPane().add(jPanel, BorderLayout.CENTER);

        mainWindow.setDefaultCloseOperation(mainWindow.EXIT_ON_CLOSE);

        mainWindow.pack();
        mainWindow.setResizable(false);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }
}