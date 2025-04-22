package com.EMS.demo.Repositories;

import com.EMS.demo.Entities.HR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HRRepo extends JpaRepository<HR,Integer> {
}
