package com.EMS.demo.Repositories;

import com.EMS.demo.Entities.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepo extends JpaRepository<ConfirmationToken,Long>{
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
