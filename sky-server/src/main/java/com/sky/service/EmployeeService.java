package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * Add Employee
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * Pagination Query
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuesy(EmployeePageQueryDTO employeePageQueryDTO);
    /**
     * Status Modify: enable or unable employee account
     * @return
     */
    void statusModify(Integer status, Long id);
    /**
     * Get Employee By Id
     * @param id
     * @return
     */
    Employee getById(Long id);
    /**
     * Edit Employee
     * @return
     */
    void editEmployee(EmployeeDTO employeeDTO);
}
