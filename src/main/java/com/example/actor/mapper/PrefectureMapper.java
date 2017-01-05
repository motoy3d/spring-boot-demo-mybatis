package com.example.actor.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.actor.model.Prefecture;

@Mapper
public interface PrefectureMapper {
	@Select("select * from prefecture order by id")
	List<Prefecture> findAll();

}
