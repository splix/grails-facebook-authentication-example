Example of Facebook Auth plugin for Grails
==========================================

Based on:

 * Grails 3.0.10
 * Spring Security Core 3.0.3
 * Spring Security Facebook 0.18 

How to run
----------

Add to `application.yml` your Facebook credentials:

```
grails:
    plugin:
        springsecurity:
            facebook:
                appId: 1234567890123
                secret: 89ba4527d238c8f5b28d6e74693435ca
```

and then you can:

```
grails run-app
```

Authors
-------

[Igor Artamonov](http://igorartamonov.com) and [The 6 Hours](http://the6hours.com)