package com.kagangunturk.finalproject;

import com.kagangunturk.finalproject.model.Policy;
import com.kagangunturk.finalproject.service.PolicyService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.io.IOException;
import java.util.List;


public class PolicyTypesTest {

    private PolicyService policyService;

    @Before
    public void setup() {
        AbstractApplicationContext ctx = null;

        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        this.policyService = ctx.getBean(PolicyService.class);


    }

    @Test
    public void testPolicy() {


        List<Policy> policyList = policyService.getAll();

        int actualSizeResult = policyList.size();
        int expectedSizeResult = 5;
        Assert.assertEquals(actualSizeResult, expectedSizeResult);


    }

    @Test
    public  void testPolicyName(){
        List<Policy> policyList = policyService.getAll();

        String actualPolicyNameResult = policyList.get(1).getPolicy_name();
        String expectedPolicyNameResult = "vehicle";
        Assert.assertEquals(actualPolicyNameResult, expectedPolicyNameResult);
    }

    @Test
    public  void testReadPolicyCsv() throws IOException {
        List<Policy> readPolicyCSV=policyService.readPolicyCSV("policy.csv");
        Boolean ActualResult =readPolicyCSV.isEmpty();
        Boolean expectedResult = false;
        Assert.assertEquals(ActualResult, expectedResult);
    }
}
