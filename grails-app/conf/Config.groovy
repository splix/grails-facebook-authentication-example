//
// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//
//
// Path to a file containing secret keys for your app (because we don't want to commit it into public GitHub repo)
// File should be like:
// grails.plugins.springsecurity.facebook.appId=<...>
// grails.plugins.springsecurity.facebook.secret=<...>
//
grails.config.locations = [ "classpath:local-config.properties",
                            "file:${userHome}/.grails/s2fb-config.properties"]


grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']


// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// enable query caching by default
grails.hibernate.cache.queries = true

grails.logging.jul.usebridge = true

grails.serverURL="http://plugin-test.dev:8080/${appName}"

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'

    debug   'com.the6hours', 'grails.app.taglib.com.the6hours'
    //debug   'org.springframework.security'
    debug   'grails.app.controllers', 'grails.app.domain', 'grails.app.services'

}

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.the6hours.example.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.the6hours.example.UserRole'
grails.plugin.springsecurity.authority.className = 'com.the6hours.example.Role'

grails.plugin.springsecurity.securityConfigType = "InterceptUrlMap"
grails.plugin.springsecurity.interceptUrlMap = [
        '/':                  ['permitAll'],
        '/**':                  ['permitAll'],
]

//
// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//
// Spring Security Facebook config
//
// Facebook keys are configured at $HOME/.grails/s2fb-config.properties (see header of this file)
//
//

grails.plugin.springsecurity.facebook.domain.classname='com.the6hours.example.FacebookUser'
//grails.plugins.springsecurity.facebook.host='plugin-test.dev'

//grails.plugins.springsecurity.facebook.filter.type='transparent,cookieDirect,json'

grails.plugin.springsecurity.facebook.filter.redirect.failureHandler='redirectFailureHandlerExample'

//uncomment to use this roles for newly created user
//by default plugins uses only 'ROLE_USER', 'ROLE_FACEBOOK' roles
//grails.plugins.springsecurity.facebook.autoCreate.roles=['ROLE_USER', 'ROLE_FACEBOOK', 'ROLE_EXAMPLE']

//uncomment to disable autocreation of new user from Facebook (disables authentication for such users)
//grails.plugins.springsecurity.facebook.autoCreate.enabled=false

//uncomment if you want to redirect users to registration page (works only with .autoCreate.enabled=false)
//grails.plugins.springsecurity.facebook.filter.redirect.failureHandler='redirectFailureHandler'

//configuration for json authentication
//grails.plugins.springsecurity.facebook.filter.json.type='jsonp' //'json' by default