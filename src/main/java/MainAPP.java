import example.entity.service.EmployeeService;

import javax.xml.ws.Endpoint;

public class MainAPP {
    public static void main(String[] argv) {
        Object implementor = new EmployeeService();
        String address = "http://localhost:9000/employ";
        Endpoint.publish(address, implementor);
    }
}
