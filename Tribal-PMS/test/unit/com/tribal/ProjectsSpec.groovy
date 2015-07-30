package com.tribal

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.Mock
import com.tribal.security.Account

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Projects)
@Mock([Account])

class ProjectsSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test validation"() {
		given:
			mockForConstraintsTests Projects
			
		when: "the name is not alphanumeric"
			def project = new Projects()
				project.name = "non alphanumeric!!"
				project.code = "xx01"
				project.techLead = new Account(username: 'techp', password: 't').save(flush: true)
				project.manager = new Account(username: 'manp', password: 'm').save(flush: true)
				project.phase = "SCOPING"
				project.priority = 11
				project.description = ""
				
		then: "validation should fail"
			!project.validate()
			project.hasErrors()
			project.errors['name'] == 'matches'
			
		when: "the name is alphanumeric"
			project.name = "Project0011"
		then: "validation should pass"
			project.validate()
			!project.hasErrors()
		
		when: "manager is null"
			project.manager = null
		then: "validation should fail"
			!project.validate()	
			project.hasErrors()
			project.errors['manager'] == 'nullable'
			
		when: "priority is already used"
			project.priority = 1
		then: "validation should fail"
			!project.validate()
			project.hasErrors()
	}
}
