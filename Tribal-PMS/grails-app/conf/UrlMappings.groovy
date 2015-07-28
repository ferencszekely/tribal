class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: 'landing', action: 'index')
		"/projects"(controller: 'projects', action: 'index')
		"/add"(controller: 'projects', action: 'addNew')
		"/view"(controller: 'projects', action: 'overView')
        "500"(view:'/error')
	}
}
