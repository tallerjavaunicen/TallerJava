package edu.isistan.chat.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

public class UserPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -5292840943808373776L;
    private JTextField sentMsg;
    private JTextArea maintxt;
    private String user;
    /**
     * Create the panel.
     */
    public UserPanel(String user) {
        this.user = user;
        setLayout(new BorderLayout(0, 0));
        
        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);
        
        maintxt = new JTextArea();
        maintxt.setEditable(false);
        scrollPane.setViewportView(maintxt);
        
        JPanel panel = new JPanel();
        add(panel, BorderLayout.SOUTH);
        panel.setLayout(new BorderLayout(0, 0));
        
        sentMsg = new JTextField();
        panel.add(sentMsg, BorderLayout.CENTER);
        sentMsg.setColumns(10);
        
        JButton btnNewButton = new JButton("Send");
        panel.add(btnNewButton, BorderLayout.EAST);
         
        btnNewButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!sentMsg.getText().trim().equals("")) {
                    MainWindows.getIChat().sendMsg(UserPanel.this.user, sentMsg.getText());
                    maintxt.setText(maintxt.getText()+"...: "+sentMsg.getText()+"\n");
                    sentMsg.setText("");
                }
            }
        });

    }
    public void addNewMessage(String text) {
        maintxt.setText(maintxt.getText()+this.user + ": " + text + "\n");   
    }

}
