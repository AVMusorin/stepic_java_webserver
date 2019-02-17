package accounts;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Profile {
    private String login;
    private String password;
}
