package com.kagangunturk.finalproject;

import com.kagangunturk.finalproject.model.Customer;
import com.kagangunturk.finalproject.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class CustomerTest {


    private CustomerService customerService;

    @Before
    public void setup() {

        AbstractApplicationContext ctx = null;
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        this.customerService = ctx.getBean(CustomerService.class);


    }

    @Test
    public void testCustomerSize() {


        List<Customer> paymentList = customerService.getAll();

        int actualSizeResult = paymentList.size();
        int expectedSizeResult = 100;
        Assert.assertEquals(expectedSizeResult, actualSizeResult);

    }

    @Test
    public void testCustomerName(){
        Customer customer = customerService.getAll().get(1);
        System.out.println(customer);

        String expectedName = customer.getCustomer_firstname();
        String actualName = "Cayla";
        Assert.assertEquals(expectedName, actualName);
    }
}
