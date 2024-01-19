package net.javaguides.ems.service;

import java.util.List;

import net.javaguides.ems.dto.EmployeeDto;

public interface EmployeeService {
	EmployeeDto createEmployee(EmployeeDto employeeDto);

	EmployeeDto getEMployeeById(Long employeeId);

	List<EmployeeDto> getAllEmployees();

	EmployeeDto updaEmployee(Long employeeId, EmployeeDto updatedEmployee);

	void deleteEmployee (Long employeeId);
}
