class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: 'landing', action: 'index')
		"/projects"(controller: 'projects', action: 'index')
        "500"(view:'/error')
	}
}
