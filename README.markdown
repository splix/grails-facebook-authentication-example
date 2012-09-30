Example of Facebook Auth plugin for Grails
==========================================

Uses Grails 2.1.1

How to run
----------

Setup an Facebook App, configure app by create file at your HOME directory, named
`.grails/s2fb-config.properties` (`.grails` is a directory where Grails stores all internal data by default),
with following content:

```
grails.plugins.springsecurity.facebook.appId=%APPID%
grails.plugins.springsecurity.facebook.secret=%SECRET%
```

and then you can:

```
grails run-app
```

Questions?
----------

Have any questions? Contact me: igor@artamonov.ru
