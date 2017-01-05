package com.example.actor.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.actor.model.Actor;

@Mapper
public interface ActorMapper {
	List<Actor> findAll();

	Actor findOne(Integer id);

	List<Actor> findActors(String keyword);

//	int update(Actor actor);

	void insert(Actor actor);

	int delete(Integer id);
}
