package com.tribal
import grails.plugin.springsecurity.annotation.Secured

class ProjectsController {
	
	@Secured(['ROLE_ADMIN'])
    def index() {
		
	}
}
