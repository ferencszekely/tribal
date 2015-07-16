package com.tribal

import com.tribal.Projects
import com.tribal.Person

class ProjectTagLib {
    static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
	
	def personForProject = { attrs, body ->
		def project = attrs.project
		if (project && attrs.lead) {
			def account = project.techLead
			def lead = Person.findByAccount(account)
			out << "${lead?.getFullName()}"
		} else if (project && attrs.manager) {
			def account = project.manager
			def manager = Person.findByAccount(account)
			out << "${manager?.getFullName()}"
		} else {
			out << "N/A"
		}
	}
	
}
