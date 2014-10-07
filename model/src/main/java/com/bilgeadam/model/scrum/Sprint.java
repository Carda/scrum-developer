package com.bilgeadam.model.scrum;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="sprint")
public class Sprint {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Date startTime;
	
	private Date endTime;
	
	@OneToMany
	private List<UserStory> sprintBacklog;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<UserStory> getSprintBacklog() {
		return sprintBacklog;
	}

	public void setSprintBacklog(List<UserStory> sprintBacklog) {
		this.sprintBacklog = sprintBacklog;
	}
	
	
	
	

}
