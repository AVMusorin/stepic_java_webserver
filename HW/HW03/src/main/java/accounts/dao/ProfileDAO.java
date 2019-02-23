package accounts.dao;

import accounts.Profile;
import org.hibernate.Session;

public class ProfileDAO {
    private Session session;

    public ProfileDAO(Session session) {
        this.session = session;
    }

    public String insertProfile(Profile profile) {
        return (String) session.save(profile);
    }

    public Profile get(String login) {
        return (Profile) session.get(Profile.class, login);
    }
}
