package com.kagangunturk.finalproject.charts;


import com.kagangunturk.finalproject.service.PolicyService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;


public class PieCharts {

    DefaultPieDataset dataset=new DefaultPieDataset();

    public void drawPieChart() throws IOException {


        AbstractApplicationContext ctx = null;

        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        PolicyService policyService = ctx.getBean(PolicyService.class);

        Map<String, BigInteger> data = policyService.getPolicyNameUsersCount();
        for (String str : data.keySet()) {
            dataset.setValue(str,data.get(str));

        }


        JFreeChart chart = ChartFactory.createPieChart("Policy User Pie Chart", dataset, true, true, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        ChartPanel panel = new ChartPanel(chart);
        panel.setVisible(true);
        panel.setSize(200, 200);
        ChartUtilities.saveChartAsJPEG(new File("images/Policy User Pie Chart.jpg"), chart, 1024, 768);
    }


}
