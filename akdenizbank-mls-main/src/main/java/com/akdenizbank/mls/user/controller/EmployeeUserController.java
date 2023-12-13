package com.akdenizbank.mls.user.controller;

import com.akdenizbank.mls.generic.rest.GenericApiResponse;
import com.akdenizbank.mls.user.EmployeeUser;
import com.akdenizbank.mls.user.service.EmployeeUserService;
import com.akdenizbank.mls.xaction.UpdateEmployeeXAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeUserController {
    @Autowired
    private EmployeeUserService employeeUserService;

    @GetMapping
    public GenericApiResponse getAllEmployee(Pageable pageable){
        Page<EmployeeUser> employeeUserPage = this.employeeUserService.getAll(pageable);
        return new GenericApiResponse(200,"Success","0001#0001",employeeUserPage);
    }

    @GetMapping("/{id}")
    public GenericApiResponse getCustomerById(@PathVariable String id) {
        EmployeeUser employeeUser = this.employeeUserService.getById(id);
        if (employeeUser == null) {
            throw new RuntimeException("No Such Customer");
        }
        return new GenericApiResponse(200, "Success", "0001#0002", employeeUser);
    }

    @PatchMapping("/{id}")
    public GenericApiResponse updateCustomerEmail(@PathVariable String id, @RequestBody UpdateEmployeeXAction xaction) {
        EmployeeUser employeeUser = this.employeeUserService.getById(id);
        if (employeeUser == null) {
            throw new RuntimeException("No Such Customer");
        }
        employeeUser.setEmail(xaction.getEmail());
        employeeUser = employeeUserService.create(employeeUser);
        return new GenericApiResponse(200, "Success", "0001#0003", employeeUser);
    }


    @DeleteMapping("/{id}")
    public GenericApiResponse deleteCustomer(@PathVariable String id) {
        this.employeeUserService.delete(id);
        return new GenericApiResponse(200, "Success", "0001#0004","Successfully deleted ");
    }






}
