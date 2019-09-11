package com.lain.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface Ktr8052Mapper {
	List<Integer> findKtr8052Address(int diId);
	void updataKtr8052Galery(	@Param("status") int status,
								@Param("gallery") String gallery,
								@Param("address") int address,
								@Param("diId") int diId);
}
