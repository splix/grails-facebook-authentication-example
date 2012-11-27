<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to Grails</title>
		<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
		</style>
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
            </sec:ifAllGranted>

		</div>
	</body>
</html>
