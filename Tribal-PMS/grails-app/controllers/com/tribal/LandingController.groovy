package com.tribal
import grails.plugin.springsecurity.annotation.Secured

class LandingController {

	@Secured(['ROLE_ADMIN'])
    def index() {
		if (isLoggedIn()) {
			redirect(controller: 'projects')
		}
	}
}
