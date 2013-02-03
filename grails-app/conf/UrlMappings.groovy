class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
        "/failed"(view:"/failed")
        "/register"(view:"/register")
		"500"(view:'/error')
	}
}
