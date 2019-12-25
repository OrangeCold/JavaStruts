package test.data.dao;

import test.data.entity.Employee;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EmployeeDAO {

    public static Map<Integer, Employee> employeeMap = null;

    static {
        employeeMap = new HashMap<>();
        employeeMap.put(1,new Employee(1,"陈梓涵"));
        employeeMap.put(2,new Employee(2,"陈梓涵"));
        employeeMap.put(3,new Employee(3,"陈梓"));
        employeeMap.put(4,new Employee(4,"陈梓涵3"));
    }

    public static Collection<Employee> getAll(){
        return employeeMap.values();
    }

}
