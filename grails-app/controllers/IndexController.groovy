import grails.plugins.springsecurity.SpringSecurityService

class IndexController {

    SpringSecurityService springSecurityService

    def show() {
        def model = [:]
        if (springSecurityService.isLoggedIn()) {
            model.user = springSecurityService.currentUser
        }
        render view: '/index', model: model
    }
}
