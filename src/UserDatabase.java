import java.util.HashMap;
import java.util.Map;

public class UserDatabase {
    private static final Map<String, User> users = new HashMap<>();

    static {
        users.put("alice", new User("alice", HashUtil.hashPassword("password123")));
        users.put("bob", new User("bob", HashUtil.hashPassword("siguria789")));
    }

    public static User getUser(String username) {
        return users.get(username);
    }
}