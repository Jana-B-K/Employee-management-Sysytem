
import java.util.*;
public class EmployeeDB {
    static List<Employee> employees = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public EmployeeDB() {
        employees.add(new Employee(1, "Alice", 30, "HR", "Manager", "None"));
        employees.add(new Employee(2, "Bob", 25, "HR", "Executive", "Alice"));
        employees.add(new Employee(3, "Charlie", 28, "IT", "Developer", "Eve"));
        employees.add(new Employee(4, "David", 32, "IT", "Lead", "Eve"));
        employees.add(new Employee(5, "Eve", 40, "IT", "Manager", "Alice"));
        employees.add(new Employee(6, "Frank", 35, "Finance", "Analyst", "Grace"));
        employees.add(new Employee(7, "Grace", 45, "Finance", "Manager", "Alice"));
        employees.add(new Employee(8, "Helen", 29, "Marketing", "Executive", "Ivy"));
        employees.add(new Employee(9, "Ivy", 38, "Marketing", "Manager", "Alice"));
        employees.add(new Employee(10, "Jack", 26, "IT", "Developer", "David"));
    }

    public static void display() {
        System.out.printf("%-15s %-10s %-10s %-15s %-15s %-15s%n",
                "Employee Id", "Name", "Age", "Department", "Designation", "Reporting To");
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }


    public static void remove(String name) {
        String newReporting = "";
        for (int i = 0; i < employees.size(); i++) {
            Employee emp = employees.get(i);
            if (emp.name.equals(name)) {
                newReporting = emp.reporting_To;
                employees.remove(i);
                for (Employee updateEmp : employees) {
                    if (updateEmp.reporting_To.equals(name)) {
                        updateEmp.reporting_To = newReporting;
                    }
                }
                break;
            }
        }
    }

    public static void reportingTree(String name) {
        String current = name;
        while (!current.equals("None")) {
            System.out.print(current + " -> ");
            boolean found = false;
            for (Employee emp : employees) {
                if (emp.name.equals(current)) {
                    current = emp.reporting_To;
                    found = true;
                    break;
                }
            }
            if (!found) break;
        }
        System.out.println("None");
    }

    public static void main(String[] args) {
        new EmployeeDB(); // load initial data

        while (true) {
            System.out.println("\n1. Display Employees");
            System.out.println("2. Search Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Reporting Tree");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    display();
                    break;
                case 2:
                    searchAndUpdate();
                    break;
                case 3:
                    System.out.print("Enter employee name: ");
                    String name = sc.nextLine();
                    remove(name);
                    break;
                case 4:
                    System.out.print("Enter name to draw tree: ");
                    String startName = sc.nextLine();
                    reportingTree(startName);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void searchAndUpdate() {
        List<Employee> filtered=new ArrayList<>(employees);
        boolean exit=false;

        while(!exit){
            filtered=searchFunction(filtered);
            System.out.println(filtered);
            System.out.println("1.Add Criteria");
            System.out.println("2. Update");
            System.out.println("3. exit");
            int next=sc.nextInt();

            if(next==1){
                continue;
            }else if(next==2){
                update(filtered);
                exit=true;
            }else{
                System.out.println("Exit");
                return;
            }
        }
    }

    private static void update(List<Employee> filtered) {
        System.out.println("select which should update");
        System.out.println("1.Name 2.Age 3.Department 4.Designation 5.Reporting To");
        int ch=sc.nextInt();

        List<Integer > empId=new ArrayList<>();
        for(Employee e:filtered){
            empId.add(e.employee_ID);
        }
        switch (ch) {
            case 1:
                System.out.println("enter the name to de updated");
                sc.nextLine();
                String name=sc.nextLine();
                for(Employee e:employees){
                    for(int i=0;i<empId.size();i++){

                        if(e.employee_ID==empId.get(i)){
                            String nameChange=e.name;
                            e.name=name;
                            for(Employee em:employees){
                                if(em.reporting_To.equals(nameChange)){
                                    em.reporting_To=name;
                                }
                            }
                        }
                    }
                }
                display();
                break;
            case 2:
                System.out.println("enter age to update");
                int age=sc.nextInt();

                for(Employee e:employees){
                    for(int i=0;i<empId.size();i++){
                        if(e.employee_ID==empId.get(i)){
                            e.age=age;
                        }
                    }
                }
                display();
                break;
            case 3:
                System.out.println("enter department to update");
                sc.nextLine();
                String department=sc.nextLine();
                for(Employee e:employees){
                    for(int i=0;i<empId.size();i++){
                        if(e.employee_ID==empId.get(i)){
                            e.department=department;
                        }
                    }
                }
                display();
                break;
            case 4:
                System.out.println("enter designation to update");
                String designation=sc.nextLine();
                for(Employee e:filtered){
                    for(int i=0;i<empId.size();i++){
                        if(e.employee_ID==empId.get(i)){
                            e.designation=designation;
                        }
                    }
                }
                display();
                break;
            case 5:
                System.out.println("enter repoting to update");
                String repotingTo=sc.nextLine();
                for(Employee e:filtered){
                    for(int i=0;i<empId.size();i++){
                        if(e.employee_ID==empId.get(i)){
                            e.reporting_To=repotingTo;
                        }
                    }
                }
                display();
                break;
        }
    }

    static List<Employee> searchFunction(List<Employee> current){
        System.out.println("Search by: 1.Name 2.Age 3.Department 4.Designation 5.Reporting To");
        int field = sc.nextInt();

        List<Employee> result=new ArrayList<>();
        if(field==2){
            System.out.println("1. < 2. > 3. == 4. != 5. between");
            int ageConditon=sc.nextInt();
            int age1=0;
            int age2=0;
            if(ageConditon==5){
                System.out.println("enter from age");
                age1=sc.nextInt();
                System.out.println("enter to age");
                age2=sc.nextInt();

            }else{
                System.out.println("Enter age");
                age1=sc.nextInt();
            }


            for(Employee e:current){
                if(matchAgeCondition(e.age,age1,age2,ageConditon)){
                    result.add(e);
                }
            }
        }else{
            System.out.println("1. equal 2.not equal 3. startWith 4. EndWith 5. contain 6. notContain");
            int condition=sc.nextInt();


            String fieldName= "";
            switch (field) {

                case 1:
                    System.out.println("Enter name");
                    String name=sc.next();
                    for(Employee e:current){
                        if(matchStringCondition(e.name,name,condition)){
                            result.add(e);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Enter department");
                    String department=sc.next();
                    for(Employee e:current){
                        if(matchStringCondition(e.department,department,condition)){
                            result.add(e);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Enter designation");
                    String designation=sc.next();
                    for(Employee e:current){
                        if(matchStringCondition(e.designation,designation,condition)){
                            result.add(e);
                        }
                    }
                    break;
                case 5:
                    System.out.println("Enter repotingTo");
                    String reportingTo=sc.next();
                    for(Employee e:current){
                        if(matchStringCondition(e.reporting_To,reportingTo,condition)){
                            result.add(e);
                        }
                    }
                    break;



            }

        }
        return result;

    }
    static boolean matchAgeCondition(int fieldAge, int age1, int age2, int condition) {
        switch (condition) {
            case 1: return fieldAge < age1;
            case 2: return fieldAge > age1;
            case 3: return fieldAge == age1;
            case 4: return fieldAge != age1;
            case 5: return fieldAge >= age1 && fieldAge <= age2;
            default: return false;
        }
    }
    static boolean matchStringCondition(String fieldValue, String value, int condition) {
        switch (condition) {
            case 1: return fieldValue.equals(value);
            case 2: return !fieldValue.equals(value);
            case 3: return fieldValue.startsWith(value);
            case 4: return fieldValue.endsWith(value);
            case 5: return fieldValue.contains(value);
            case 6: return !fieldValue.contains(value);
            default: return false;
        }
    }
}