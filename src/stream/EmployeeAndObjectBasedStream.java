package stream;

import stream.dto.Department;
import stream.dto.Employee;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EmployeeAndObjectBasedStream {
    public static void main(String[] args) {
        // --- SEEDING SAMPLE DATA ---
        List<Employee> employees = Arrays.asList(
                new Employee(101, "Suresh Kumar", "IT", 1200000, 31),
                new Employee(102, "Sumit Sharma", "HR", 450000, 26),
                new Employee(103, "Amit Verma", "IT", 1500000, 35),
                new Employee(104, "Sunita Rao", "Finance", 1100000, 29),
                new Employee(105, "Rahul", "HR", 600000, 28), // No last name
                new Employee(106, "Neha Gupta", "Finance", 1500000, 32),
                new Employee(107, "Priya Nair", "IT", 800000, 24)
        );

        // 26. Find the highest-paid employee.
        employees.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .ifPresent(System.out::println);

        // 27. Find the second-highest salary.
        employees.stream()
                .map(Employee::getSalary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .limit(1)
                .findFirst()
                .ifPresent(System.out::println);

        // 28. Find the highest-paid employee in each department.
        Map<String, Optional<Employee>> collect = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))

                ));
        System.out.println(collect);

        // 29. Group employees by department.
        Map<String, List<Employee>> collect1 = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment
                ));
        System.out.println(collect1);

        // 30. Count employees in each department.
        Map<String, Long> collect2 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println(collect2);

        // 31. Calculate average salary by department.
        Map<String, Double> collect3 = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));
        System.out.println(collect3);

        // 32. Find employees earning more than ₹10 lakh.
        List<Employee> collect4 = employees.stream()
                .filter(e -> e.getSalary() > 1_000_000)
                .collect(Collectors.toList());
        System.out.println(collect4);

        // 33. Sort employees by salary, then name.
        List<Employee> collect5 = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).thenComparing(Employee::getName))
                .collect(Collectors.toList());
        System.out.println(collect5);
        // 34. Find the top three highest-paid employees.
        List<Employee> collect6 = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(collect6);
        // 35. Convert a list of employees into a Map keyed by employee ID.
        Map<Integer, Employee> collect7 = employees.stream()
                .collect(Collectors.toMap(
                        Employee::getId,
                        Function.identity()
                ));
        System.out.println(collect7);
        // 36. Handle duplicate keys when using Collectors.toMap().
        Map<String, Employee> collect8 = employees.stream()
                .collect(Collectors.toMap(
                        Employee::getDepartment,
                        Function.identity(),
                        (existing, current) -> existing.getSalary() > current.getSalary() ? existing : current
                ));
        System.out.println(collect8);
        // 37. Find employees whose names start with "SU".
        List<Employee> collect9 = employees.stream()
                .filter(e -> e.getName().toLowerCase(Locale.ROOT).startsWith("su"))
                .collect(Collectors.toList());
        System.out.println(collect9);
        // 38. Find employees with a null last name without throwing an exception.
        List<Employee> collect10 = employees.stream()
                .filter(e -> e.getName() != null && !e.getName().contains(" "))
                .collect(Collectors.toList());
        System.out.println(collect10);
        // 39. Separate employees into salary bands using partitioningBy().
        Map<Boolean, List<Employee>> collect11 = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 1_000_000));
        System.out.println(collect11.get(true));
        // 40. Flatten a list of departments containing lists of employees using flatMap().
        List<Department> departmentContext = Arrays.asList(
                new Department("Engineering", Arrays.asList(employees.get(0), employees.get(2))),
                new Department("Operations", Arrays.asList(employees.get(1), employees.get(4)))
        );

        List<Employee> collect12 = departmentContext.stream()
                .flatMap(s -> s.getEmployees().stream())
                .collect(Collectors.toList());
        System.out.println(collect12);
    }

}
