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
}
