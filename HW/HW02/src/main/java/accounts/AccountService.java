package accounts;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private final Map<String, Profile> profiles;
    private final Map<String, Profile> profilesSession;

    public AccountService() {
        profiles = new HashMap<>();
        profilesSession = new HashMap<>();
    }

    public boolean addUser(Profile user) {
        if (!profiles.containsKey(user.getLogin())) {
            profiles.put(user.getLogin(), user);
            return true;
        }
        return false;
    }

    public boolean addSession(Profile user, String sessionId) {
        if (profiles.containsKey(user.getLogin())) {
            profilesSession.put(sessionId, user);
            return true;
        }
        return false;
    }

    public Profile getUserByLogin(String login) {
        return profiles.get(login);
    }

    public Profile getUserBySession(String session) {
        return profilesSession.get(session);
    }
}
