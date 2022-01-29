package hibernate.one_to_one_bi;

import hibernate.one_to_one_bi.entity.Detail;
import hibernate.one_to_one_bi.entity.Employee;
import org.junit.Test;

import static org.junit.Assert.*;

public class SaveTest {

    @Test
    public void saveEmployee() {
        Save emp = new Save();
        Employee employee = new Employee("Artur", "Frimu", "IT", 2000);
        Detail detail = new Detail("Chisinau", "1234554321", "artur@gmail.com");
        emp.saveEmployee(employee, detail);
        Employee employee1 = new Get().getEmployee(employee.getId());
        assertEquals(employee1, employee);
    }

    @Test
    public void saveDetail() {
        new Save().saveDetail(new Detail("Chisinau", "1234554321", "artur@gmail.com"), new Employee("Artur", "Frimu", "IT", 2000));
    }
}