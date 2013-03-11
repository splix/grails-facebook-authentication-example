<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>JSON authentication test</title>
	</head>
	<body>
		<div id="page-body" class="json-test" role="main">
			<h1>JSON authentication test</h1>
			<p></p>

            <h2>Try</h2>
            <form action="${processUrl}">
                <label>Access Token</label>
                <input type="text" name="access_token">
                <g:select name="type"
                          from="${[json: 'JSON', jsonp: 'JSONP']}"
                          value="${type}"
                          optionKey="key"
                          optionValue="value"
                />
                <input type="submit" value="Submit" onclick="send(); return false"/>
            </form>
            <div class="server-status"></div>
            <div class="server-response"></div>

            <g:javascript>
            function send() {
                console.log('Send token to server');
                $('.server-status').html('Sending').show();
                $('.server-response').empty().show();
                $.ajax({
                    url: '${processUrl}',
                    type: 'POST',
                    data: {
                        access_token: $('input[name="access_token"]').val()
                    },
                    dataType: $('select[name="type"]').val(),
                    success: function(data) {
                        $('.server-status').html('Success');
                    },
                    error: function(xhr, status) {
                        $('.server-status').html('Failed: ' + status);
                    },
                    complete: function(xhr) {
                        console.log('Complete', xhr);
                        $('.server-response').html(xhr.responseText);
                    }
                });
            }
            </g:javascript>
		</div>
	</body>
</html>
