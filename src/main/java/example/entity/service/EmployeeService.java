package example.entity.service;

import example.entity.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import example.entity.ultis.HibernateUtil;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService()
public class EmployeeService {
    @WebMethod
    public List<Employee> getEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        try (Session session = HibernateUtil.getSession()) {
            employeeList = session.createQuery("from Employee", Employee.class).list();
        } catch (Exception ex) {
            System.out.println("Can not findAll student");
        }
        return employeeList;
    }

    @WebMethod
    public Employee addEmployees(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
            return employee;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }
    }

    public Employee findById(long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            transaction.commit();
            return employee;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Cannot findById...");
            return null;
        }
    }

    @WebMethod
    public Employee updateEmployee(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
            return employee;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }
    }
}