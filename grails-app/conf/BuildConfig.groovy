grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.source.level = 1.6
grails.project.target.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits true // Whether to inherit repository definitions from plugins
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenCentral()

        // uncomment these to enable remote dependency resolution from public Maven repositories
        //mavenCentral()
        //mavenLocal()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        // runtime 'mysql:mysql-connector-java:5.1.16'

        //Following dependencies are optional. Used for examples how you can extend default plugin behaviour
        compile 'org.springframework.social:spring-social-core:1.0.1.RELEASE'
        compile 'org.springframework.social:spring-social-facebook:1.0.1.RELEASE'
    }

    plugins {
        compile ":hibernate:$grailsVersion"
        compile ":jquery:1.6.1.1"
        compile ":resources:1.1.6"
        compile ":webxml:1.4.1"
        compile "org.grails.plugins:spring-security-facebook:0.15"

        //build ":tomcat:$grailsVersion"
    }
}

//grails.plugin.location.'spring-security-facebook' = '/Users/splix/Projects/grails-dev/grails-spring-security-facebook'

