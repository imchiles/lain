package com.lain.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lain.entity.Ktr8060;

public interface Ktr8060Mapper {
	Ktr8060 findKtrAddress(@Param("diId")int diId ,@Param("id")int id);
	List<Ktr8060> findKtrAddressById(int kId);
}
