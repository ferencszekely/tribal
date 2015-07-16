import com.tribal.security.Account
import com.tribal.security.AccountRole
import com.tribal.security.Role
import com.tribal.Person

class BootStrap {

    def init = { servletContext ->
		
		
		def techRole = new Role(authority: 'ROLE_TECH').save(flush: true)
		def managerRole = new Role(authority: 'ROLE_MANAGER').save(flush: true)
		// def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
		def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
		
		def adminUser = new Account(username: 'test', password: 'test')
			adminUser.save(flush: true)
  
		def tech1 = new Account(username: 'tech1', password: 'tech')
			tech1.save(flush: true)
		def tech2 = new Account(username: 'tech2', password: 'tech')
			tech2.save(flush: true)
		def tech3 = new Account(username: 'tech3', password: 'tech')
			tech3.save(flush: true)
		def tech4 = new Account(username: 'tech4', password: 'tech')
			tech4.save(flush: true)
		
		def manager1 = new Account(username: 'mngr1', password: 'man')
			manager1.save(flush: true)
		def manager2 = new Account(username: 'mngr2', password: 'man')
			manager2.save(flush: true)
		def manager3 = new Account(username: 'mngr3', password: 'man')
			manager3.save(flush: true)
		def manager4 = new Account(username: 'mngr4', password: 'man')
			manager4.save(flush: true)
		
		AccountRole.create adminUser, adminRole, true
		AccountRole.create tech1, techRole, true
		AccountRole.create tech2, techRole, true
		AccountRole.create tech3, techRole, true
		AccountRole.create tech4, techRole, true
		AccountRole.create manager1, managerRole, true
		AccountRole.create manager2, managerRole, true
		AccountRole.create manager3, managerRole, true
		AccountRole.create manager4, managerRole, true
			
			
		def pers1 = new Person(firstName: 'Admin', lastName: 'John', account: adminUser)
			pers1.save(flush: true)
		def pers2 = new Person(firstName: 'Techlead', lastName: 'One', account: tech1)
			pers2.save(flush: true)
		def pers3 = new Person(firstName: 'Techlead', lastName: 'Two', account: tech2)
			pers3.save(flush: true)
		def pers4 = new Person(firstName: 'Techlead', lastName: 'Three', account: tech3)
			pers4.save(flush: true)
		def pers5 = new Person(firstName: 'Techlead', lastName: 'Four', account: tech4)
			pers5.save(flush: true)
		def pers6 = new Person(firstName: 'Manager', lastName: 'One', account: manager1)
			pers6.save(flush: true)
		def pers7 = new Person(firstName: 'Manager', lastName: 'Two', account: manager2)
			pers7.save(flush: true)
		def pers8 = new Person(firstName: 'Manager', lastName: 'Three', account: manager3)
			pers8.save(flush: true)
		def pers9 = new Person(firstName: 'Manager', lastName: 'Four', account: manager4)
			pers9.save(flush: true)
		
		
		
		
		assert Account.count() == 9
		assert Role.count() == 3
		assert AccountRole.count() == 9
		assert Person.count() == 9
		
    }
    def destroy = {
    }
}
