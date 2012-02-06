import com.the6hours.grails.springsecurity.facebook.FacebookAuthToken
import com.the6hours.example.FacebookUser
/**
 * 
 * @author Igor Artamonov (http://igorartamonov.com)
 * @since 15.01.12
 */
class FacebookAuthService {

    void onCreate(FacebookUser user, FacebookAuthToken token) {
        log.info("Creating user: $user for fb user: $token.uid")
    }

    void afterCreate(FacebookUser user, FacebookAuthToken token) {
        log.info("User created: $user for fb user: $token.uid")
    }
}
