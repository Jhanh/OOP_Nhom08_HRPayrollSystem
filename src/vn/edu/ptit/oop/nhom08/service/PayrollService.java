package vn.edu.ptit.oop.nhom08.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import vn.edu.ptit.oop.nhom08.model.Employee;
import vn.edu.ptit.oop.nhom08.model.TaxCalculator;
import vn.edu.ptit.oop.nhom08.repository.EmployeeRepository;
import vn.edu.ptit.oop.nhom08.util.CurrencyFormatUtil;

public class PayrollService {

    private final EmployeeRepository repository;

    public PayrollService(EmployeeRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("EmployeeRepository không được null!");
        }
        this.repository = repository;
    }

    public double calculateTotalPayroll() {
        double total = 0.0;
        for (Employee e : repository.findAll()) {
            total += e.calculateSalary();
        }
        return total;
    }

    public double calculateTotalTax() {
        return TaxCalculator.calculateTotalTax(repository.findAll());
    }

    public double calculateTotalNetSalary() {
        double total = 0.0;
        for (Employee e : repository.findAll()) {
            total += TaxCalculator.calculateNetSalary(e);
        }
        return total;
    }

    public Map<String, Double> calculatePayrollByDepartment() {
        Map<String, Double> result = new LinkedHashMap<>();
        for (Employee e : repository.findAll()) {
            String dept = e.getDepartment();
            result.put(dept, result.getOrDefault(dept, 0.0) + e.calculateSalary());
        }
        return result;
    }

    public Map<String, Long> countEmployeesByDepartment() {
        Map<String, Long> result = new LinkedHashMap<>();
        for (Employee e : repository.findAll()) {
            String dept = e.getDepartment();
            result.put(dept, result.getOrDefault(dept, 0L) + 1L);
        }
        return result;
    }

    public Map<String, Long> countEmployeesByType() {
        Map<String, Long> result = new LinkedHashMap<>();
        for (Employee e : repository.findAll()) {
            String type = e.getEmployeeType();
            result.put(type, result.getOrDefault(type, 0L) + 1L);
        }
        return result;
    }

    public int getTotalEmployeeCount() {
        return repository.count();
    }

    public double calculateAverageSalary() {
        List<Employee> list = repository.findAll();
        if (list.isEmpty()) return 0.0;
        return calculateTotalPayroll() / list.size();
    }

    public Optional<Employee> findHighestPaidEmployee() {
        List<Employee> list = repository.findAll();
        if (list.isEmpty()) return Optional.empty();
        Employee highest = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).calculateSalary() > highest.calculateSalary()) {
                highest = list.get(i);
            }
        }
        return Optional.of(highest);
    }

    public String getStatusBarSummary() {
        int totalEmp = getTotalEmployeeCount();
        String totalGross = CurrencyFormatUtil.formatWithUnit(calculateTotalPayroll());
        String totalNet = CurrencyFormatUtil.formatWithUnit(calculateTotalNetSalary());
        return String.format(" Sẵn sàng | Nhân viên: %d | Quỹ lương: %s | Thực lĩnh: %s",
                totalEmp, totalGross, totalNet);
    }
}
