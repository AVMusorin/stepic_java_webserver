package accounts;

import accounts.dao.ProfileDAO;
import dbService.DBException;
import dbService.DBService;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private final Map<String, Profile> profiles;
    private final Map<String, Profile> profilesSession;

    private final DBService dbService;


    public AccountService(DBService dbService) {
        profiles = new HashMap<>();
        profilesSession = new HashMap<>();
        this.dbService = dbService;
    }

    public boolean addUser(Profile user) throws DBException {
        if (!profiles.containsKey(user.getLogin())) {
            profiles.put(user.getLogin(), user);
            dbService.insertProfile(user);
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

    public Profile getUserByLogin(String login) throws DBException {
        if (login != null) {
            return dbService.getProfileByLogin(login);
        }
        return null;
    }

    public Profile getUserBySession(String session) {
        return profilesSession.get(session);
    }
}
