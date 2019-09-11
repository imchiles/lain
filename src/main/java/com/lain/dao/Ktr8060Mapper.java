package com.lain.dao;

import java.util.List;

import com.lain.entity.Ktr8060;

public interface Ktr8060Mapper {
	Ktr8060 findKtrAddress(int diId ,int id);
	List<Ktr8060> findKtrAddressById(int kId);
}
