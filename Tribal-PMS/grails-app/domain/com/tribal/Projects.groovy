package com.tribal

import com.tribal.security.Account
import com.tribal.enums.ProjectPhase

class Projects {

	String name
	String code
	
	Account techLead
	Account manager
	
	Date deliveryDate
	
	ProjectPhase phase // = ProjectPhase.BRIEFING
	
	Integer priority
	
	String description
	
	static alphaNumeric = "[A-Za-z0-9]*"
	static numerical = "[0-9]"
	
	// need to be able to change priority and two projects cannot share the same priority
	// need to be able to change phase , deliveryDate, techLead, manager
	
    static constraints = {
		name (nullable: false, blank: false, matches: alphaNumeric)
		code (nullable: false, blank: false, matches: alphaNumeric)
		techLead (nullable: false, blank: false)
		manager (nullable: false, blank: false)
		deliveryDate (nullable: true, blank: true)
		phase (nullable: true, blank: true)
		priority (unique: true, nullable: false, blank: false, matches: numerical)
		description (nullable: true, blank: true)
	}
}
