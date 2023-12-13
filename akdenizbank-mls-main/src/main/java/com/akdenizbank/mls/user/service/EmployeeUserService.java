package com.akdenizbank.mls.user.service;

import com.akdenizbank.mls.user.EmployeeUser;
import com.akdenizbank.mls.user.repository.EmployeeUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeUserService {
    private EmployeeUserRepository employeeUserRepository;
    public EmployeeUserService(EmployeeUserRepository employeeUserRepository){
        this.employeeUserRepository = employeeUserRepository;
    }
public EmployeeUser create(EmployeeUser employeeUser){
        return this.employeeUserRepository.save(employeeUser);

}
public EmployeeUser getById(String id){
        return this.employeeUserRepository.findById(id).orElse(null);
}
public Page<EmployeeUser> getAll(Pageable pageable){
        return this.employeeUserRepository.findAll(pageable);
}
public void delete(String id){
        EmployeeUser employeeUser = this.getById(id);
    if (employeeUser == null){
        throw new RuntimeException("No such Employee");
    }
    this.employeeUserRepository.delete(employeeUser);
    }
}
