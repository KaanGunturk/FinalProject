package com.kagangunturk.finalproject;

import com.kagangunturk.finalproject.model.Customer;
import com.kagangunturk.finalproject.model.CustomerPolicy;
import com.kagangunturk.finalproject.service.CustomerPolicyService;
import com.kagangunturk.finalproject.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class CustomerPolicyTest {

    private CustomerPolicyService customerPolicyService;

    @Before
    public void setup() {

        AbstractApplicationContext ctx = null;
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        this.customerPolicyService = ctx.getBean(CustomerPolicyService.class);
    }

    @Test
    public void testCustomerSize() {


        List<CustomerPolicy> customerPolicyList = customerPolicyService.getAll();

        int actualSizeResult = customerPolicyList.size();
        int expectedSizeResult = 100;
        Assert.assertEquals(expectedSizeResult, actualSizeResult);
    }

    @Test
    public  void testReadCustomerPolicyCsv() throws Exception {
        List<CustomerPolicy> readPolicyCSV=customerPolicyService.readCustomerPolicyCSV("customer_policy.csv");
        Boolean actualResult =readPolicyCSV.isEmpty();
        Boolean expectedResult = false;
        Assert.assertEquals(expectedResult, actualResult);
    }
}
