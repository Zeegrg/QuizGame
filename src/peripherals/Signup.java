package peripherals;



import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;


public class Signup extends JFrame{
	private JTextField txuser = new JTextField(15);
    private JPasswordField pass = new JPasswordField(15);
    private JButton bsignup = new JButton("Signup");
    
    Connection con;
    PreparedStatement ps;
    
    private Map<String, String> userDatabase = new HashMap<>();
    
    Signup() {
    	
    	 super();
	        setSize(550, 378);
	        setLocation(500, 280);

	        JPanel panel = new JPanel();
	        panel.setBackground(new Color(100, 149, 237));
	        panel.setLayout(null);
	        txuser.setFont(new Font("Tahoma", Font.PLAIN, 18));

	        txuser.setBounds(244, 72, 182, 28);
	        pass.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        pass.setBounds(244, 122, 182, 28);
	        bsignup.setBackground(new Color(34, 139, 34));
	        bsignup.setFont(new Font("Tahoma", Font.BOLD, 18));
	        bsignup.setBounds(124, 197, 302, 39);

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
	        
	        bsignup.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent ae) {
	                String username = txuser.getText();
	                String password = String.valueOf(pass.getPassword());
	            		try {
	            		Class.forName("org.postgresql.Driver");
	            		con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tomatoDB?user=postgres&password=9845391320@abc");
	            		
	            		String sql = "INSERT INTO tomato(username, password) VALUES(?,?)";
	            		PreparedStatement statement = con.prepareStatement(sql);
	            		statement.setString(1,username);
	            		statement.setString(2,password);
	            		statement.executeUpdate();
	            		JOptionPane.showMessageDialog(null, "Signup successfull");
	            		txuser.setText("");
	            		pass.setText("");
	            		
	            	}
	            	catch(Exception e1) {
	            		
	            		System.out.println(e1.toString());
	            	}
	            		dispose();
	            		new LoginSignupGUI();
	    }
	        });
    	
    	
    }
    

	    // public static void main(String[] args) {
	    //     new Signup();
	    // }
	
}

