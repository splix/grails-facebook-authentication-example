<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to Grails</title>
	</head>
	<body>
		<div id="page-body" role="main">
			<h1>Welcome to Grails</h1>
			<p>Congratulations, you have successfully started Facebook Authentication plugin example</p>

            <h2>Facebook Authentication</h2>
            <facebookAuth:init> <!-- for client-side authentication -->
                FB.Event.subscribe('auth.login', function() {
                    if (typeof(console) === 'object' && typeof(console.log) === 'function') {
                        console.log('Process auth.login...');
                    }
                    window.location.reload();
                });
            </facebookAuth:init>
            <sec:ifNotGranted roles="ROLE_FACEBOOK">
                <ul>
                    <li><facebookAuth:connect/></li>
                </ul>
            </sec:ifNotGranted>
            <sec:ifAllGranted roles="ROLE_FACEBOOK">
                Welcome! <sec:username/>

                <g:javascript> <!-- for client-side authentication -->
                function doLogout() {
                    if (typeof(FB) === 'object') {
                        FB.logout(function() {
                            window.location.href = "${createLink(uri: '/j_spring_security_logout')}";
                        });
                        return false;
                    }
                    return true;
                }
                </g:javascript>

                <g:link uri="/j_spring_security_logout">Logout</g:link>

                <div class="actions">
                    <h2>More actions:</h2>
                    <ul>
                    <li><g:link controller="testing" action="expireToken">Expire Facebook access_token</g:link> - to test
                    how plugin going to reload it from facebook</li>
                    </ul>
                </div>
            </sec:ifAllGranted>

		</div>
	</body>
</html>
