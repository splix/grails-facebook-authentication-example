import grails.plugin.springsecurity.SpringSecurityUtils

class JsonController {

    def show() {
        def conf = SpringSecurityUtils.securityConfig
        render(view:  '/json', model: [
                processUrl: createLink(uri: conf.facebook.filter.json.processUrl),
                type: conf.facebook.filter.json.type ?: 'json'
        ])
    }

}