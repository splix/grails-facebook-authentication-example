package com.the6hours.example

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *
 * @author Igor Artamonov (http://igorartamonov.com)
 * @since 03.02.13
 */
class RedirectFailureToRegistration extends SimpleUrlAuthenticationFailureHandler {

    protected final Log logger = LogFactory.getLog(RedirectFailureToRegistration)

    String registrationUrl

    @Override
    void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
        throws IOException, ServletException {

        if (exception instanceof UsernameNotFoundException) {
            logger.debug("Redirecting to " + registrationUrl)
            redirectStrategy.sendRedirect(request, response, registrationUrl)
            return
        }
        super.onAuthenticationFailure(request, response, exception)
    }
}
