package com.capg.ChildVaccination.ServiceImpl;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.ChildVaccination.Dto.ChildDto;
import com.capg.ChildVaccination.Entity.Booking;
import com.capg.ChildVaccination.Entity.Child;
import com.capg.ChildVaccination.Entity.Parent;
import com.capg.ChildVaccination.Exceptions.ParentNotFoundException;
import com.capg.ChildVaccination.Repository.ChildRepository;
import com.capg.ChildVaccination.Repository.ParentRepository;
import com.capg.ChildVaccination.Service.ChildService;

@Service
public class ChildServiceImpl implements ChildService{
	
	@Autowired
	ParentRepository parentRepo;
	@Autowired
	ChildRepository childRepo;

	@Override
	public Child addChild(String email , ChildDto child) {
		Parent p1 = parentRepo.findByEmail(email);
		Child c1 = new Child();
		if(p1 != null) {
			c1.setName(child.getName());
			c1.setAge(child.getAge());
			c1.setWeight(child.getWeight());
			c1.setGender(child.getGender());
			c1.setParent(p1);
			return childRepo.save(c1);
		}else {
			throw new ParentNotFoundException(email);
		}
			
	}

	@Override
	public Child updateChild(int id, ChildDto child) {
		Child c1 = childRepo.findById(id).orElse(null);
//		Parent p1 = parentRepo.findById(child.ge).orElse(null);
		if(c1 == null) {
			throw new ParentNotFoundException("child/parent not found");
		}
		c1.setName(child.getName());
		c1.setAge(child.getAge());
		c1.setWeight(child.getWeight());
		c1.setGender(child.getGender());
		c1.setParent(c1.getParent());
		return childRepo.save(c1);
	}

	@Override
	public Child delChild(int id) {
		Child c1 = childRepo.findById(id).orElse(null);
		if(c1 == null) {
			throw new ParentNotFoundException("Child not found");
		}
		childRepo.deleteById(id);
		return c1;
	}

	@Override
	public Child viewChild(int id) {
		Child c1 = childRepo.findById(id).orElse(null);
		if(c1 == null) {
			throw new ParentNotFoundException("Child not found");
		}
		return c1;
	}

	@Override
	public List<Child> viewAllChild(int id) {
		Iterable<Child> c1 = childRepo.findAll();
		List<Child> c2 = new ArrayList<>();
		for(Child c : c1) {
			if(c.getParent().getParentid() == id) {
				c2.add(c);
			}
		}
		return c2;
	}

	@Override
	public List<Child> viewAllChildByEmail(String email) {
		Parent p1 = parentRepo.findByEmail(email);
		Iterable<Child> list = childRepo.findAll();
		List<Child> l1 = new ArrayList<>();
		for(Child c: list) {
			if(c.getParent().getParentid() == p1.getParentid()) {
				l1.add(c);
			}
		}
		return l1;
	}
	
	@Override
	public Page<Child> getChildPagination(Integer pageNumber, Integer pageSize, String sortProperty) {
        Pageable pageable = null;
        if(null!=sortProperty){
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC,sortProperty);
        }else {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC,"name");
        }
        return childRepo.findAll(pageable);
    }
}



	
