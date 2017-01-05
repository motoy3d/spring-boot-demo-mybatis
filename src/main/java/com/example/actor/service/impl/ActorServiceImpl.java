package com.example.actor.service.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.actor.mapper.ActorMapper;
import com.example.actor.model.Actor;
import com.example.actor.service.ActorService;
import com.example.actor.web.form.ActorForm;

@Service
public class ActorServiceImpl implements ActorService {
	final static Logger logger = LoggerFactory.getLogger(ActorServiceImpl.class);

	@Autowired
	private ActorMapper actorMapper;

	public List<Actor> findAll() {
		return actorMapper.findAll();
	}

	public Actor findOne(Integer id) {
		return actorMapper.findOne(id);
	}

	public List<Actor> findActors(String keyword) {
		return actorMapper.findActors(keyword);
	}

	@Transactional
	public Actor save(ActorForm form) {
		Actor actor = convert(form);

		logger.debug("actor:{}", actor.toString());

		actorMapper.insert(actor);

		return actor;
	}

	@Transactional
	public int delete(Integer id) {
		return actorMapper.delete(id);
	}

	/**
	 * convert form to model.
	 */
	private Actor convert(ActorForm form) {
		Actor actor = new Actor();
		actor.setName(form.getName());
		if (StringUtils.isNotEmpty(form.getHeight())) {
			actor.setHeight(Integer.valueOf(form.getHeight()));
		}
		if (StringUtils.isNotEmpty(form.getBlood())) {
			actor.setBlood(form.getBlood());
		}
		if (StringUtils.isNotEmpty(form.getBirthday())) {
			DateTimeFormatter withoutZone = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime parsed = LocalDateTime.parse(form.getBirthday() + " 00:00:00", withoutZone);
			Instant instant = parsed.toInstant(ZoneOffset.ofHours(9));
			actor.setBirthday(Date.from(instant));
		}
		if (StringUtils.isNotEmpty(form.getBirthplaceId())) {
			actor.setBirthplaceId(Integer.valueOf(form.getBirthplaceId()));
		}
		actor.setUpdateAt(new Date());
		return actor;
	}



}
