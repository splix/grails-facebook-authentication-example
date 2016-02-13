import com.the6hours.grails.springsecurity.facebook.FacebookAuthToken
import com.the6hours.example.FacebookUser
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import com.the6hours.example.User
import com.the6hours.example.UserRole
import com.the6hours.example.Role
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.social.facebook.api.Facebook
import org.springframework.social.facebook.api.impl.FacebookTemplate

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

    Map onJsonSuccess(Map input, FacebookAuthToken token) {
        input['demo'] = 'An message from FacebookAuthService'
        return input
    }

    Map onJsonFailure(Map input, AuthenticationException exception) {
        StackTraceElement stack = exception.stackTrace.last()
        input['stacktrace'] = [
                line: stack.lineNumber,
                classname: stack.className
        ]
        return input
    }

    /**
     * Called when we have a new facebook user, called on first login to create all required
     * data structures. Replaces .createAppUser and .createRoles methods.
     *
     * @param token facebook authentication token
     */
    FacebookUser create(FacebookAuthToken token) {
        log.info("Create domain for facebook user $token.uid")

        //Use Spring Social Facebook to load details for current user from Facebook API
        Facebook facebook = new FacebookTemplate(token.accessToken.accessToken)
        org.springframework.social.facebook.api.User fbProfile = facebook.userOperations().userProfile
        String email = fbProfile.email
        String username = fbProfile.id
        String name = fbProfile.name

        User person = new User(
                username: username,
                password: token.accessToken.accessToken, //not really necessary
                enabled: true,
                accountExpired:  false,
                accountLocked: false,
                passwordExpired: false,

                //fill with data loaded from Facebook API
                name: name,
                email: email
        )
        person.save()
        FacebookUser fbUser = new FacebookUser(
                uid: token.uid,
                accessToken: token.accessToken.accessToken,
                accessTokenExpires: token.accessToken.expireAt,
                user: person
        )
        fbUser.save()
        X_createRoles(fbUser)

        return fbUser
    }

    // ********************************************************************************************
    //
    // You can remove X_ prefix from following methods, if you need some logic specific for your app
    //
    // ********************************************************************************************

    /**
     * !!! remove X_ to use this method
     *
     * Called when facebook user is authenticated (on every request), must return existing
     * instance for specified facebook uid, if exits. If doesn't - return null
     *
     * @param uid facebook user id
     */
    FacebookUser X_findUser(Long uid) {
        log.info("Search for facebook user with id $uid")
        return FacebookUser.findByUid(uid)
    }

    /**
     * !!! remove X_ to use this method
     *
     * Called when we have a new facebook user, called on first login to create main app User domain (when
     * we store Facebook User details in own domain)
     *
     * @param token facebook authentication token
     */
    User X_createAppUser(FacebookUser user, FacebookAuthToken token) {
        log.info("Create app user for facebook user $token.uid")
        User person = new User(
                username: "test_$token.uid",
                password: '********',
                enabled: true,
                accountExpired:  false,
                accountLocked: false,
                passwordExpired: false
        )
        person.save(failOnError: true)
        return person
    }

    /**
     * !!! remove X_ to use this method
     *
     * Called when we have a new facebook user, called on first login to create roles list for new user
     *
     * @param user facebook user
     */
    void X_createRoles(FacebookUser user) {
        log.info("Create role for facebook user $user.uid")
        UserRole.create(user.user, Role.findByAuthority('ROLE_USER'))
        UserRole.create(user.user, Role.findByAuthority('ROLE_FACEBOOK'))
    }

    /**
     * !!! remove X_ to use this method
     *
     * Must returns object to store in security context for specified facebook user (can return itself)
     *
     * @param user facebook user
     */
    def X_getPrincipal(FacebookUser user) {
        log.info("Ger principal for facebook user #$user.id")
        return user.user
    }

    /**
     * !!! remove X_ to use this method
     *
     * Must return roles list for specified facebook user
     *
     * @param user facebook user
     */
    Collection<GrantedAuthority> X_getRoles(FacebookUser user) {
        log.info("Ger roles for facebook user #$user.id")
        return user.user.authorities.collect {
            new SimpleGrantedAuthority(it.authority)
        }
    }

    /**
     * !!! remove X_ to use this method
     *
     * Must return roles list for specified facebook user
     *
     * @param user facebook user
     */
    void X_prepopulateAppUser(User user, FacebookAuthToken token) {
        log.info("Prepopulate app user")
        user.password = '*******'
        user.username = "test_$token.uid"
        user.accountExpired = false
        user.accountLocked = false
        user.enabled = true
        user.passwordExpired = false
    }

}
