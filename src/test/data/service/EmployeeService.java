package test.data.service;

import test.data.dao.EmployeeDAO;
import test.data.entity.Employee;

import java.util.Collection;

public class EmployeeService {

    public static void main(String[] args) {

        String newEmpName = "陈梓";
        Collection<Employee> all = EmployeeDAO.getAll();
        int count = 0;
        for (Employee employee : all) {
            String name = employee.getName();
            if (name.length()>=newEmpName.length()){

            }
        }
    }


}
