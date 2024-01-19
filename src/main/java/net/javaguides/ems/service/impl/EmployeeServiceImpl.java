package net.javaguides.ems.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.mapper.EmployeeMapper;
import net.javaguides.ems.repository.EmployeeRepository;
import net.javaguides.ems.service.EmployeeService;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		// TODO Auto-generated method stub
		
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		Employee savedEmployee = employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
		
	}

	@Override
	public EmployeeDto getEMployeeById(Long employeeId) {
		// TODO Auto-generated method stub

		Employee employee = employeeRepository.findById(employeeId)
			.orElseThrow(()-> new ResourceNotFoundException("Employee does not exist with given id: "+ employeeId));
		return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		// TODO Auto-generated method stub
		List<Employee> employees = employeeRepository.findAll();
		return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
				.collect(Collectors.toList()); 
	}

	@Override
	public EmployeeDto updaEmployee(Long employeeId, EmployeeDto updatedEmployee) {
		// TODO Auto-generated method stub
		
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(
			() -> new ResourceNotFoundException("Employee does not exists wiht the given ID" + employeeId)
		);

		employee.setFirstName(updatedEmployee.getFirstName());
		employee.setLastName(updatedEmployee.getLastName());
		employee.setEmail(updatedEmployee.getEmail());

		Employee updatedEmployeeObj = employeeRepository.save(employee);

		return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		// TODO Auto-generated method stub

		Employee employee = employeeRepository.findById(employeeId).orElseThrow(
			() -> new ResourceNotFoundException("Employee does not exists wiht the given ID" + employeeId)
		);

		employeeRepository.deleteById(employeeId);
	}


}
