Example of Facebook Auth plugin for Grails
==========================================

Uses Grails 2.4.2

How to run
----------

Setup an Facebook App, configure app by create file at your HOME directory, named
`.grails/s2fb-config.properties` (`.grails` is a directory where Grails stores all internal data by default),
with following content:

```
grails.plugin.springsecurity.facebook.appId=%APPID%
grails.plugin.springsecurity.facebook.secret=%SECRET%
```

and then you can:

```
grails run-app
```

Authors
-------

[Igor Artamonov](http://igorartamonov.com) and [The 6 Hours](http://the6hours.com)