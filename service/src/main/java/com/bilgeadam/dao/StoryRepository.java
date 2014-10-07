package com.bilgeadam.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilgeadam.model.scrum.UserStory;

public interface StoryRepository extends JpaRepository<UserStory, Long>{

}
