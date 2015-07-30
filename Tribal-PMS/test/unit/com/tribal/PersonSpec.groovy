package com.tribal

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification
import com.tribal.security.Account

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Person)
@Mock ( [Account] )
class PersonSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test that none of the fields can be blank or null"() {
		when: 'firstName is blank'
			def p = new Person(firstName: '', lastName: "Smith", account: new Account(username: 'acc', password: 'acc'))
    
		then: 'validate should fail!'
			!p.validate()
			
		when: 'everything filled correctly'
			p = new Person(firstName: 'John', lastName: "Smith", account: new Account(username: 'acc', password: 'acc'))
			
		then: "validate should pass"
			p.validate()	
		}
}
