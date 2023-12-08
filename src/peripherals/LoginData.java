package peripherals;

// /**
//  * Basic class. To do: 
//  * link against external database.
//  * signup mechanism to create account. 
//  * Encryption
//  * Etc.
//  *
//  */
// public class LoginData {
// 	/**
// 	 * Returns true if passwd matches the username given.
// 	 * @param username
// 	 * @param passwd
// 	 * @return
// 	 */
// 	boolean checkPassword(String username, String passwd) { 
// 		if( username.equals("Ada") && passwd.equals("hello23")) return true; 
// 		return false; 
		
// 	}
// }
import java.util.HashMap;
import java.util.Map;

public class LoginData {
    private Map<String, String> userDatabase;

    public LoginData() {
        // Initialize an empty user database (for demonstration).
        userDatabase = new HashMap<>();
        // Predefined user (for demonstration).
        userDatabase.put("Ada", "hello23");
    }

    public boolean checkPassword(String username, String passwd) {
        String storedPassword = userDatabase.get(username);
        return storedPassword != null && storedPassword.equals(passwd);
    }

    public void registerUser(String username, String password) {
        userDatabase.put(username, password);
    }
}
