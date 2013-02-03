import com.the6hours.example.RedirectFailureToRegistration
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler

beans = {

    redirectFailureHandlerExample(SimpleUrlAuthenticationFailureHandler) {
        defaultFailureUrl = '/failed'
    }

    redirectFailureHandler(RedirectFailureToRegistration) {
        defaultFailureUrl = '/failed'
        registrationUrl = '/register'
    }

}