package com.kagangunturk.finalproject.charts;

import com.kagangunturk.finalproject.service.PolicyService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.io.File;
import java.io.IOException;
import java.util.Map;


public class PaymentsBarChart {

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    public void drawPaymentsBarChart() throws IOException {

        AbstractApplicationContext ctx = null;

        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        PolicyService policyService = ctx.getBean(PolicyService.class);

        Map<String, Double> data = policyService.getPolicysDetail();
        for (String str : data.keySet()) {
            dataset.addValue(data.get(str), str, str);

        }

        JFreeChart chart = ChartFactory.createBarChart("Policy amount Bar Chart", "Policy Name", "Amount", dataset, PlotOrientation.VERTICAL, false, true, false);

        ChartPanel panel = new ChartPanel(chart);
        panel.setVisible(true);
        panel.setSize(200, 200);

        ChartUtilities.saveChartAsJPEG(new File("images/Policy amount Bar Chart.jpg"), chart, 1024, 768);
    }


}
