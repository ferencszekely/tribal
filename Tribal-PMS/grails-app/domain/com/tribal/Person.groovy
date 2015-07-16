package com.tribal

import com.tribal.security.Account

class Person {

	String firstName
	String lastName
	
	Account account
	
    static constraints = {
		firstName (blank: false, maxSize: 30, nullable:false)
		lastName (blank: false, maxSize: 30, nullable:false)
		account (nullable: false)
    }
}
