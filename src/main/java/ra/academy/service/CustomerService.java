package ra.academy.service;

import org.springframework.stereotype.Service;
import ra.academy.model.Customer;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomerService {
    private List<Customer> list = Arrays.asList(new Customer(1,"Hồ Xuân Hùng")
    ,new Customer(2,"Đào Hoàng Hải"));
   public List<Customer> findAll(){
       return list;
   }
}
