package com.tribal

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import grails.test.mixin.services.ServiceUnitTestMixin
import grails.test.mixin.web.ControllerUnitTestMixin
import grails.test.mixin.web.FiltersUnitTestMixin
import grails.test.mixin.web.GroovyPageUnitTestMixin
import grails.test.mixin.web.UrlMappingsUnitTestMixin
import grails.test.mixin.hibernate.HibernateTestMixin
import com.tribal.security.Account
import com.tribal.security.AccountRole
import com.tribal.security.Role
import com.tribal.enums.ProjectPhase

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ProjectsController)
@Mock([ Person, Projects, Account, AccountRole, Role ])
class ProjectsControllerSpec extends Specification {

	def springSecurityService
	
    def setup() {
		
    }

    def cleanup() {
    }

    void "test index"() {
		def user = Person.get(1)
		controller.springSecurityService = [currentUser: user]
		
		when:
		controller.index()
		
		then:
		response.status == response.SC_OK
    }
	
	void "test overView"() {
		when:
		controller.overView()
		
		then:
		response.status == response.SC_OK
	}
	
	void "test add project"() {
		given: "accounts mocked"
			Account tech = new Account(username: 'testtech', password: 'testpass').save(flush: true)
			Account man = new Account(username: 'testman', password: 'testpass').save(flush: true)
			
		and: "params set"
			controller.params.name = "testproj"
			controller.params.code = "test00x1"
			controller.params.techLead = "testtech"
			controller.params.manager = "testman"
			controller.params.date = "07/31/2015"
			controller.params.phase = "Briefing"
			controller.params.priority = 51
			controller.params.desc = "Description for test"
			
		when: "action call"
			controller.addNew()
			// controller.save()
			
		then: "should be redirected to /view"
			response.redirectedUrl == "/view"
			
		and: "it should have success flash message"
			flash.success == true
			
	}
	
	void "test edit"() {
		given: "project"
			Account tech = new Account(username: 't', password: 't').save(flush: true)
			Account man = new Account(username: 'm', password: 'm').save(flush: true)
			Projects project = new Projects(
				name: 'testProject',
				code: 'tst0001',	
				techLead: tech ,
				manager: man,
				deliveryDate: new Date(),
				phase: ProjectPhase.DEVELOPMENT,
				priority: 13,
				description: ''
			)
			project.save(flush: true)
			Person person = new Person(firstName: 'techFirst', lastName: 'techLast', account: tech).save(flush: true)
			Person person2 = new Person(firstName: 'manFirst', lastName: 'manLast', account: man).save(flush: true)
			
		when: 
			params.id = 1
			controller.edit()
			
		then:
			response.status == response.SC_OK
	}
}
