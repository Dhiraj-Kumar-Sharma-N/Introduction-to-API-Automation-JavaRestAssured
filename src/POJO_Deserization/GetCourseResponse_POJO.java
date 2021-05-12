package POJO_Deserization;

public class GetCourseResponse_POJO {

	// DESERIALIZING GETCOURSE RESPONSE FROM JSON TO JAVA OBJECT

	private String instructor, url, services, expertise, linkedIn;
	private NestedJsonCourse_POJO courses;
	
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	public NestedJsonCourse_POJO getCourses() {
		return courses;
	}
	public void setCourses(NestedJsonCourse_POJO courses) {
		this.courses = courses;
	}

	
}
