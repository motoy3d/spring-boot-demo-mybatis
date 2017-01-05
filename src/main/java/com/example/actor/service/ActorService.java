package com.example.actor.service;

import java.util.List;

import com.example.actor.model.Actor;
import com.example.actor.web.form.ActorForm;

public interface ActorService {
	List<Actor> findAll();

	Actor findOne(Integer id);

	List<Actor> findActors(String keyword);

	Actor save(ActorForm form);

	int delete(Integer id);
}
