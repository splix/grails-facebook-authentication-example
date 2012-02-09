import com.the6hours.grails.springsecurity.facebook.FacebookAuthToken
import com.the6hours.example.FacebookUser
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.springframework.security.core.GrantedAuthority
import com.the6hours.example.User
import com.the6hours.example.UserRole
import com.the6hours.example.Role
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
     * Called when we have a new facebook user, called on first login to create all required
     * data structures
     *
     * @param token facebook authentication token
     */
    FacebookUser X_create(FacebookAuthToken token) {
        log.info("Create domain for facebook user $token.uid")
        User person = new User(
                username: "test_$token.uid",
                password: '********',
                enabled: true,
                accountExpired:  false,
                accountLocked: false,
                passwordExpired: false
        )
        person.save()
        UserRole.create(person, Authority.findByAuthority('ROLE_USER'))
        UserRole.create(person, Authority.findByAuthority('ROLE_FACEBOOK'))
        FacebookUser fbUser = new FacebookUser(
                uid: token.uid,
                accessToken: token.accessToken,
                user: person
        )
        fbUser.save()
        return fbUser
    }

    /**
     * !!! remove X_ to use this method
     *
     * Called when we have a new facebook user, called on first login to create main app User domain (when
     * we store Facebook User details in different domain)
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
            new GrantedAuthorityImpl(it.authority)
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
