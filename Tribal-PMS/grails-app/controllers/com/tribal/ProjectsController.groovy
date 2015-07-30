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
	def dateFormat = new java.text.SimpleDateFormat("MM/dd/yyyy")
	
	// status labels for view
	private def currentPhase = [
		(ProjectPhase.BRIEFING)		: 'default',
		(ProjectPhase.SCOPING)  	: 'warning',
		(ProjectPhase.INTERACTION)  : 'info',
		(ProjectPhase.DEVELOPMENT)  : 'primary',
		(ProjectPhase.QA)			: 'danger',
		(ProjectPhase.RELEASE)		: 'success'	
	]
	
	// findig all projects in the database, using Hibernate
	private def getProjects() {
		Projects.findAll().sort {a,b -> a.priority <=> b.priority }
	} // loads the entire table before sorting, it is advisible to use .list instead
	// for large tables
	
	// Getting a list of technical leads for selection
	private def techs() {
		def techs = Role.findByAuthority('ROLE_TECH')
		def techAccounts = AccountRole.findAllByRole(techs)
		def techLeads = Person.findAllByAccountInList(techAccounts.account)
		return techLeads
	}
	
	// Getting a list of managers for selection
	private def mans() {
		def mans = Role.findByAuthority('ROLE_MANAGER')
		def manAccounts = AccountRole.findAllByRole(mans)
		def managers = Person.findAllByAccountInList(manAccounts.account)
		return managers
	}
	
	
	// welcome page for the admin
	def index() {
		def user = Person.findByAccount((Account) springSecurityService.currentUser)
		
		[u: user, projects: getProjects()]
	}
	
	/*
	 * Projects section - listing all projects and related information
	 */
	def overView() {
		
		[projects: getProjects(), t: techs(), m: mans(), phases: ProjectPhase.values(), currentPhase: currentPhase]
	}
	
	/*
	 * 	Adding a new project to the database
	 * @param params
	 */
	def addNew() {
		// Avoiding use of bindData, as I had many problems/bugs in the past with that
		if (params && params.date) {
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
				if (p.save(failOnError:true)) {
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
			def currentLead = Person.findByAccount(project.techLead)
			def currentManager = Person.findByAccount(project.manager)
			
			[p: project, t: techs(), m: mans(), lead: currentLead, manager: currentManager, phases: ProjectPhase.values()]
		
		} else {
			log.info("there's an error")
			flash.error = true
			return render(view: 'edit')
		}
	}
	
	// updating specified project
	// TODO maybe we can use the addNew action for this? look into it
	def submitEdit() {
		def project = Projects.get(params.project.toInteger())
		def date = dateFormat.parse(params.date)
		if (project) {
			project.name = params.name
			project.code = params.code
			project.techLead = Account.findByUsername(params.techLead)
			project.manager = Account.findByUsername(params.manager)
			project.deliveryDate = date
			project.phase = params.phase ? ProjectPhase.values().find {it.value == params.phase} : null
			project.priority = params.priority.toInteger()
			project.description = params.desc
			
			if (project.save(flush: true)) {
				flash.success = true
				return redirect(action: 'overView')
			} else {
				flash.error1 = true
				return render(view: 'edit', params: [p: params])
			}
		} else {
			flash.error2 = true
			return render(view: 'edit', params: [p: params])
		}
	}
	
	// Listing managers
	def managers() {
		[m: mans()]
	}
	
	// Listing Technical Leads
	def techLeads() {
		[t: techs()]
	}
	
	/*
	 * We need to verify instantly if a priority is
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
