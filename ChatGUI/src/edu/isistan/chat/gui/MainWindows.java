package edu.isistan.chat.gui;

import java.awt.EventQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JFrame;

import edu.isistan.chat.ChatGUI;
import edu.isistan.chat.IChat;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

public class MainWindows implements ChatGUI {
    private static IChat CHAT;
    private static AtomicBoolean LAUNCH = new AtomicBoolean(false);
    private static ChatGUI COURRENT;
    private static String MAIN = "Main";

    private JFrame frame;
    private JTabbedPane tabbedPane;

    public static ChatGUI launchOrGet(IChat model) {
        if(LAUNCH.compareAndSet(false, true)) {
            CHAT = model;
            COURRENT = new MainWindows();
        }
        return COURRENT;
    }


    public static IChat getIChat() {
        return CHAT;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        new MainWindows();
    }

    /**
     * Create the application.
     */
    private MainWindows() {
        initialize();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame("Supa Duppa Chat!!");
        frame.setBounds(100, 100, 800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        tabbedPane.add(MAIN, new MainPanel());
    }


    @Override
    public void addNewGeneralMsg(String from, String text) {
        EventQueue.invokeLater(()->{
            ((MainPanel)tabbedPane.getComponent(0)).addNewMessage(from, text); 
        });
    }


    @Override
    public void addNewMsg(String from, String text) {
        EventQueue.invokeLater(()->{
            int tab = -1;
            for(int i = 0; i < tabbedPane.getTabCount(); i++)
                if (tabbedPane.getTitleAt(i).equals(from)) {
                    tab = i;
                    break;
                }
            if (tab == -1) {
                tabbedPane.addTab(from, new UserPanel(from));
                tab = tabbedPane.getTabCount() - 1;
            }
            ((UserPanel)tabbedPane.getComponent(tab)).addNewMessage(text); 
        });
    }


    @Override
    public void addUser(String user) {
        EventQueue.invokeLater(()->{
            if (user.equals(MAIN)) {
                System.err.println("No se puere agregar un user Main");
                return;
            }
            ((MainPanel)tabbedPane.getComponent(0)).addUser(user);
        });
    }


    @Override
    public void removeUser(String user) {
        EventQueue.invokeLater(()->{
            if (user.equals(MAIN)) {
                System.err.println("No se puere remover la ventana Main");
                return;
            }
            for(int i = 0; i < tabbedPane.getTabCount(); i++)
                if (tabbedPane.getTitleAt(i).equals(user)) {
                    tabbedPane.remove(i);
                    break;
                }
            ((MainPanel)tabbedPane.getComponent(0)).remevoUser(user);
        });
    }


    @Override
    public void chatWith(String user) {
        EventQueue.invokeLater(()->{
            for(int i = 0; i < tabbedPane.getTabCount(); i++)
                if (tabbedPane.getTitleAt(i).equals(user))
                    return;
            tabbedPane.addTab(user, new UserPanel(user));
        });
    }

}
