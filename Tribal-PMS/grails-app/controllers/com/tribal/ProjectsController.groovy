package com.tribal
import grails.plugin.springsecurity.annotation.Secured
import com.tribal.security.Account
import com.tribal.Projects
import com.tribal.enums.ProjectPhase

@Secured(['ROLE_ADMIN'])
class ProjectsController {
	
	def springSecurityService
	
	// findig all projects in the database, using Hibernate
	private def getProjects() {
		Projects.findAll()
	} // TODO sort by priority
	
	// welcome page for the admin
	def index() {
		def user = Person.findByAccount((Account) springSecurityService.currentUser)
		
		[u: user, projects: getProjects()]
	}
	
	/*
	 * Projects section - listing all projects and related information
	 */
	def overView() {
		if (!getProjects()) {
		def prj = new Projects()
			prj.name = "test01"
			prj.code = "ttt01"
			prj.techLead = Account.findByUsername("tech1")
			prj.manager = Account.findByUsername("mngr1")
			prj.deliveryDate = new Date()
			prj.phase = ProjectPhase.BRIEFING
			prj.priority = 1
			prj.description = "Desc"
			prj.save(failOnError: true)
		}
			
		[projects: getProjects()]
	}
}
