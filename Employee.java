public class Employee {
    int employee_ID;
    String name;
    int age;
    String department;
    String designation;
    String reporting_To;

    public Employee(int employee_ID,String  name,int age,String department,String designation,String reporting_To){
        this.employee_ID=employee_ID;
        this.name=name;
        this.age=age;
        this.department=department;
        this.designation=designation;
        this.reporting_To=reporting_To;
    }
    public String toString() {
        return String.format("%-15d %-10s %-10d %-10s %-15s %-15s",
                employee_ID, name, age, department, designation, reporting_To);
    }
    
}