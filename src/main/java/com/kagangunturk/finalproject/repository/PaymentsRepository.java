package com.kagangunturk.finalproject.repository;


import com.kagangunturk.finalproject.model.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Repository
public class PaymentsRepository {

    private SessionFactory sessionFactory;


    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void createPayment(List<Payment> paymentList) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();

        try {
            for (Payment payment : paymentList) {
                session.save(payment);
            }
            session.getTransaction().commit();
            System.out.println("Payment is created!");
        } catch (Exception ex) {
            System.out.println("Payment cannot be created! " + ex.getMessage());
            session.getTransaction().rollback();
        }
    }

    public List<Payment> getAllPayment() {
        try {
            Session session = this.sessionFactory.openSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Payment> criteriaQuery = criteriaBuilder.createQuery(Payment.class);
            Root<Payment> root = criteriaQuery.from(Payment.class);

            CriteriaQuery<Payment> criteriaQuery2 = criteriaQuery.select(root);
            Query<Payment> query = session.createQuery(criteriaQuery2);
            List<Payment> payments = query.getResultList();
            return payments;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<Payment>();
        }
    }

    public Payment getPayment(int id) throws Exception {
        Session session = this.sessionFactory.openSession();
        Payment foundPayment = session.get(Payment.class, id);
        if (foundPayment != null) {
            return foundPayment;
        }
        throw new Exception("Payment does not exist!");
    }

    public Map<String, Double> getPaymentsWithTotalAmountByYear() {
        Session session = this.sessionFactory.openSession();

        List list = session.createQuery("select payments_date, sum(payments_amount) as payments_amount from Payment group by payments_date order by payments_date").getResultList();

        Map<String, Double> result = new TreeMap<>();

        for (int i = 0; i < list.size(); i++) {
            Object[] row = (Object[]) list.get(i);
            String date = row[0].toString();
            double amount = (double) row[1];

            result.put(date, amount);
        }

        return result;

    }


}