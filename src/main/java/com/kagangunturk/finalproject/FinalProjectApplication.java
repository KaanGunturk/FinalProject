package com.kagangunturk.finalproject;


import com.kagangunturk.finalproject.UserPanel.Login;
import com.kagangunturk.finalproject.service.CustomerPolicyService;
import com.kagangunturk.finalproject.service.CustomerService;
import com.kagangunturk.finalproject.service.PaymentService;
import com.kagangunturk.finalproject.service.PolicyService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class FinalProjectApplication {

    public static void main(String[] args) {
        AbstractApplicationContext ctx = null;
        try {
            ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            CustomerService customerService = ctx.getBean(CustomerService.class);
            PolicyService policyService = ctx.getBean(PolicyService.class);
            CustomerPolicyService customerPolicyService = ctx.getBean(CustomerPolicyService.class);
            PaymentService paymentService = ctx.getBean(PaymentService.class);

            customerService.initCustomer();
            policyService.initPolicy();
            customerPolicyService.initCustomerPolicy();
            paymentService.initPayment();
            Login.login();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ctx != null)
                ctx.close();
        }
    }
}