package com.kagangunturk.finalproject.charts;


import com.kagangunturk.finalproject.service.PaymentService;
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

public class PaymentsByYearLineChart {

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();


    public void drawPaymentsBarChart() throws IOException {

        AbstractApplicationContext ctx = null;

        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        PaymentService paymentService = ctx.getBean(PaymentService.class);

        Map<String, Double> data = paymentService.getPaymentsDetail();
        for (String str : data.keySet()) {
            dataset.addValue(data.get(str), "a", str);

        }


        JFreeChart lineChart = ChartFactory.createLineChart("Payment Line Chart", "Date", "Amount", dataset, PlotOrientation.VERTICAL, false, true, false);
        ChartPanel panel = new ChartPanel(lineChart);
        panel.setVisible(true);
        panel.setSize(200, 200);

        ChartUtilities.saveChartAsJPEG(new File("images/Payment Line Chart.jpg"), lineChart, 1024, 768);
    }


}