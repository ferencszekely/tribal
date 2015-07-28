package com.tribal
import grails.plugin.springsecurity.annotation.Secured
import com.tribal.security.Account
import com.tribal.security.AccountRole
import com.tribal.security.Role
import com.tribal.Projects
import com.tribal.Person
import com.tribal.enums.ProjectPhase
import grails.converters.JSON

@Secured(['ROLE_ADMIN'])
class ProjectsController {
	
	def springSecurityService
	
	// findig all projects in the database, using Hibernate
	private def getProjects() {
		Projects.findAll().sort {a,b -> a.priority <=> b.priority }
	} // loads the entire table before sorting, it is advisible to use .list instead
	// for large tables
	
	
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
		// Creating a list of technical leads for selection
		def techs = Role.findByAuthority('ROLE_TECH')
		def techAccounts = AccountRole.findAllByRole(techs)
		def techLeads = Person.findAllByAccountInList(techAccounts.account)
		
		// Creating a list of managers for selection
		def mans = Role.findByAuthority('ROLE_MANAGER')
		def manAccounts = AccountRole.findAllByRole(mans)
		def managers = Person.findAllByAccountInList(manAccounts.account)
		
		
		[projects: getProjects(), t: techLeads, m: managers, phases: ProjectPhase.values()]
	}
	
	/*
	 * 	Adding a new project to the database
	 * @param params
	 */
	def addNew() {
		// Avoiding use of bindData, as I had many problems/bugs in the past with that
		if (params && params.date) {
			def dateFormat = new java.text.SimpleDateFormat("MM/dd/yyyy")
			def date = dateFormat.parse(params.date)
			
			def p = new Projects()
				p.name = params.name
				p.code = params.code
				p.techLead = Account.findByUsername(params.techLead)
				p.manager = Account.findByUsername(params.manager)
				p.deliveryDate = date
				p.phase = params.phase ? ProjectPhase.values().find {it.value == params.phase} : null
				p.priority = params.priority.toInteger()
				p.description = params.desc
				if (p.save()) {
					flash.success = true
					return redirect(action: 'overView')
				} else {
					flash.error = true
					return redirect(action: 'overView')
				}
				
		} else {
			flash.error = true
			return redirect(action: 'overView')
		}
	}
	
	/*
	 * Editing projects, returning a view with the specified project id
	 * @param id
	 */
	def edit(Integer id) {
		def project = Projects.get(id)
		if (project) {
			
		}
	}
	/*
	 * We need to verify instanly if a priority is
	 * already in use. Accepts an integer and returns either
	 * success or error.
	 * @param priority
	 */
	@Secured('permitAll()')
	def verifyPriority() {
		try {
			def priority = params.pr.toInteger()
			if (priority) {
				def existingPriority = Projects.findByPriority(priority)
				if (existingPriority) {
					render([error: true] as JSON)
				} else {
					render([success: true] as JSON)
				}
			} else {
				render([error: true] as JSON)
			}
		} catch (NumberFormatException e) {
			render([error: true] as JSON)
		}
	}
	
}
