import com.tribal.security.Account
import com.tribal.security.AccountRole
import com.tribal.security.Role

class BootStrap {

    def init = { servletContext ->
		
		
		def techRole = new Role(authority: 'ROLE_TECH').save(flush: true)
		def managerRole = new Role(authority: 'ROLE_MANAGER').save(flush: true)
		// def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
		def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
		
		def adminUser = new Account(username: 'test', password: 'test')
		adminUser.save(flush: true)
  
		//def tech1 = new Account(username: 'Tech1', password: 'tech')
		//tech1.save(flush: true)
		//def tech2 = new Account(username: 'Tech2', password: 'tech')
		//tech2.save(flush: true)
		//def tech3 = new Account(username: 'Tech2', password: 'tech')
		//tech3.save(flush: true)
		//def tech4 = new Account(username: 'Tech2', password: 'tech')
		//tech4.save(flush: true)
		
		// TODO -> create isntances for firstname, lastname in a separate table
		
		AccountRole.create adminUser, adminRole, true
		//AccountRole.create tech1, techRole, true
		//AccountRole.create tech2, techRole, true
		//AccountRole.create tech3, techRole, true
		//AccountRole.create tech4, techRole, true
		
		assert Account.count() == 1
		assert Role.count() == 3
		assert AccountRole.count() == 1
		
    }
    def destroy = {
    }
}
