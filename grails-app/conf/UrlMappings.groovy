class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

        "/json"(controller: 'json', action: 'show')

		"/"(view:"/index")
        "/failed"(view:"/failed")
        "/register"(view:"/register")
		"500"(view:'/error')
	}
}
