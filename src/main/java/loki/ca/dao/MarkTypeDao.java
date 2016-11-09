package loki.ca.dao;

import java.util.List;

import loki.ca.model.MarkType;
import loki.ca.model.MarkTypeScope;
import loki.ca.model.Project;

public interface MarkTypeDao {

	MarkType getMarkTypeById(int id);
	
	void addMarkType(MarkType markType);
	
	void updateMarkType(MarkType markType);
	
	void deleteMarkType(MarkType markType);
	
	void allowMarkType(MarkType markType, Project project, MarkTypeScope scope);
	
	void disallowMarkType(MarkType markType, Project project, MarkTypeScope scope);
	
	List<MarkType> getAllowed(Project project, MarkTypeScope scope);
	
}
