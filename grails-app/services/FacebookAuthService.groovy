import com.test.MyFacebookUser
import com.the6hours.grails.springsecurity.facebook.FacebookAuthToken
/**
 * 
 * @author Igor Artamonov (http://igorartamonov.com)
 * @since 15.01.12
 */
class FacebookAuthService {

    void onCreate(MyFacebookUser user, FacebookAuthToken token) {
        log.info("Creating user: $user for fb user: $token.uid")
    }

    void afterCreate(MyFacebookUser user, FacebookAuthToken token) {
        log.info("User created: $user for fb user: $token.uid")
    }
}
