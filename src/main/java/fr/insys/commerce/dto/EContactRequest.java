package fr.insys.commerce.dto;

public enum EContactRequest {
	TYPE_PARTENARIAT("Demande de partenariat"),
	TYPE_AVIS("Avis sur le site"),
	TYPE_CONSEIL("Conseil sur le site");
	
	
	
	private String content;
	
	private EContactRequest(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
}
