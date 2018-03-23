package es.uji.ei1027.sape.model;

public class Estancia {

	int id;
	String cifEmpresa;
	String contactPerson;
	String mailContactPerson;
	String internshipDescription;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCifEmpresa() {
		return cifEmpresa;
	}
	public void setCifEmpresa(String cifEmpresa) {
		this.cifEmpresa = cifEmpresa;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getMailContactPerson() {
		return mailContactPerson;
	}
	public void setMailContactPerson(String mailContactPerson) {
		this.mailContactPerson = mailContactPerson;
	}
	public String getInternshipDescription() {
		return internshipDescription;
	}
	public void setInternshipDescription(String internshipDescription) {
		this.internshipDescription = internshipDescription;
	}
	
	@Override
	public String toString() {
		return "Estancia [id=" + id + ", cifEmpresa=" + cifEmpresa + ", contactPerson=" + contactPerson
				+ ", mailContactPerson=" + mailContactPerson + ", internshipDescription=" + internshipDescription + "]";
	}
	
	
	
}
