package com.tribal.enums

public enum ProjectPhase {
	
	BRIEFING("Briefing"),
	SCOPING("Scoping"),
	INTERACTION("Interaction"),
	DEVELOPMENT("Development"),
	QA("Qa"),
	RELEASE("Release")
	
	final String value
	
	ProjectPhase(String value) { this.value = value }
	
	@Override
	String toString() { value }

	String getKey() { name() }
	
}
