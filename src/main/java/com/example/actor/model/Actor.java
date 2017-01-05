package com.example.actor.model;

import java.util.Date;

import lombok.Data;

@Data
public class Actor {

	private Integer id;

	private String name;

	private Integer height;

	private String blood;

	private Date birthday;

	private Integer birthplaceId;

	private Date updateAt;

	///////////////////
	private String prefName;

//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "birthplace_id", insertable = false, updatable = false)
	private Prefecture pref;
}
