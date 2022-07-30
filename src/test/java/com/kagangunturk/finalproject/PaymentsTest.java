package com.kagangunturk.finalproject;


import com.kagangunturk.finalproject.model.Payment;
import com.kagangunturk.finalproject.service.PaymentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Collection;
import java.util.List;

public class PaymentsTest {


    private PaymentService paymentService;

    @Before
    public void setup() {
        AbstractApplicationContext ctx = null;

        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        this.paymentService = ctx.getBean(PaymentService.class);


    }

    @Test
    public void testPaymentSize() {


        List<Payment> paymentList = paymentService.getAll();

        int actualSizeResult = paymentList.size();
        int expectedSizeResult = 100;
        Assert.assertEquals(expectedSizeResult,actualSizeResult);

    }

    @Test
    public void testPaymentAmountSize(){

        Collection<Double> paymentAmountList =paymentService.getPaymentsDetail().values();
        Integer actualSize=paymentAmountList.size();
        Integer expectedSize=23;
        Assert.assertEquals(expectedSize, actualSize);
    }

}
