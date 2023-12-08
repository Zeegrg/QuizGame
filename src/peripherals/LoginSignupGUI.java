package peripherals;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.awt.Font;
import java.awt.Color;

public class LoginSignupGUI extends JFrame {
    private JTextField txuser = new JTextField(15);
    private JPasswordField pass = new JPasswordField(15);
    private JButton blogin = new JButton("Login");
    private JButton bsignup = new JButton("Signup");

    private Map<String, String> userDatabase = new HashMap<>();

    public Map<String, String> getUserDatabase() {
        return userDatabase;
    }

    public void setUserDatabase(Map<String, String> userDatabase) {
        this.userDatabase = userDatabase;
    }

    LoginSignupGUI() {
        super("Login and Signup");
        setSize(603, 381);
        setLocation(500, 280);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(95, 158, 160));
        panel.setLayout(null);
        txuser.setFont(new Font("Tahoma", Font.PLAIN, 18));

        txuser.setBounds(244, 72, 182, 28);
        pass.setFont(new Font("Tahoma", Font.PLAIN, 18));
        pass.setBounds(244, 122, 182, 28);
        blogin.setBackground(new Color(0, 0, 255));
        blogin.setFont(new Font("Tahoma", Font.BOLD, 18));
        blogin.setBounds(123, 193, 112, 39);
        bsignup.setBackground(new Color(128, 0, 0));
        bsignup.setFont(new Font("Tahoma", Font.BOLD, 18));
        bsignup.setBounds(314, 193, 112, 39);

        panel.add(blogin);
        panel.add(txuser);
        panel.add(pass);
        panel.add(bsignup);

        getContentPane().add(panel);
        
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblUsername.setBounds(123, 67, 112, 39);
        panel.add(lblUsername);
        
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblPassword.setBounds(123, 115, 112, 43);
        panel.add(lblPassword);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        blogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	
                String username = txuser.getText();
                String password = String.valueOf(pass.getPassword());
                
                try {
                    // Establishing database connection
                	Class.forName("org.postgresql.Driver");
                    Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tomatoDB?user=postgres&password=9845391320@abc");
            	
            		
                    String sql = "SELECT * FROM tomato WHERE username = ? AND password = ?";
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.setString(1, username);
                    statement.setString(2, password);

                    // Closing resources
                    statement.close();
                    con.close();

                    GameGUI theGame = new GameGUI();
                    theGame.setVisible(true);
                    dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Wrong Password / Username");
                }
            }  
           });
        bsignup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	dispose();
            	openSignup();
            	
            }
        });
    }

    private void openSignup() {
    	dispose();
    	new Signup();
    }
    
    private void openGameGUI(String username) {
        dispose(); // Closes the login/signup page
        new GameGUI(username); // Opens the game interface with the provided username
    }

    public static void main(String[] args) {
        new LoginSignupGUI();
    }
}
