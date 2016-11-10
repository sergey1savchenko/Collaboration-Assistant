package loki.ca.model;

public class Team {

	private int id;
	private int project_id;
	private String title;
	
	public Team(){
		
	}
	
	public Team(int id, int pr_id, String title){
		this.id = id;
		this.project_id = pr_id;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	@Override
	public String toString(){
		return "Team [id = " + this.id + ", project_id = " + this.project_id + ", title = " + title + "]"; 
	}
	
}
