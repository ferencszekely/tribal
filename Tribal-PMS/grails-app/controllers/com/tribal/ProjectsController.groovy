package com.tribal
import grails.plugin.springsecurity.annotation.Secured
import com.tribal.security.Account

class ProjectsController {
	
	def springSecurityService
	
	@Secured(['ROLE_ADMIN'])
    def index() {
		def person = Person.findByAccount((Account) springSecurityService.currentUser)
		log.info(person)
	}
}
