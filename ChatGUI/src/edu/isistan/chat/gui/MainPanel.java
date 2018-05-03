package edu.isistan.chat.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;

public class MainPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -5292840943808373776L;
    private JTextField sentMsg;
    JList<String> users;
    JTextArea maintxt;
    private List<String> listUsers = new LinkedList<>();
    /**
     * Create the panel.
     */
    public MainPanel() {
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
        
        JScrollPane scrollPane_1 = new JScrollPane();
        add(scrollPane_1, BorderLayout.EAST);
        
        users = new JList<>();
        scrollPane_1.setViewportView(users);
        users.addMouseListener(new MouseListener() {
            
            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int i = users.getSelectedIndex();
                if (i==-1) 
                    return;
                MainWindows.launchOrGet(null).chatWith(users.getModel().getElementAt(i));
            }
        });
        
        btnNewButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!sentMsg.getText().trim().equals("")) {
                    MainWindows.getIChat().sendMsg(sentMsg.getText());
                    maintxt.setText(maintxt.getText()+"...: "+sentMsg.getText()+"\n");
                    sentMsg.setText("");
                }
            }
        });

    }
    
    public void remevoUser(String user) {
        listUsers.remove(user);
        users.setListData(listUsers.toArray(new String[listUsers.size()]));
    }

    public void addUser(String user) {
        if (listUsers.contains(user))
            return;
        listUsers.add(user);
        users.setListData(listUsers.toArray(new String[listUsers.size()]));
    }

    public void addNewMessage(String from, String text) {
        maintxt.setText(maintxt.getText()+from + ": " + text + "\n");
    }
}
