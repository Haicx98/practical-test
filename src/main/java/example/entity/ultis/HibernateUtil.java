package example.entity.ultis;

import example.entity.Employee;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        Transaction transaction = null;
        try {

            transaction = session.beginTransaction();
            Employee employee = new Employee();
            employee.setName("xuan hai");
            employee.setSalary(1000);
            session.save(employee);
            System.out.println("success!!!!");
            transaction.commit();
        }
        catch (Exception ex){
            if(transaction!=null){
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }
}