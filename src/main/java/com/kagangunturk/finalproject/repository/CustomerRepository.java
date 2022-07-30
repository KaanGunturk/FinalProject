package com.kagangunturk.finalproject.repository;



import com.kagangunturk.finalproject.model.Customer;
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

@Repository
public class CustomerRepository {
    private SessionFactory sessionFactory;


    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void createCustomer(List<Customer> customerList) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();

        try {
            for (Customer customer : customerList) {
                session.save(customer);
            }
            session.getTransaction().commit();
            System.out.println("Customer is created!");
        } catch (Exception ex) {
            System.out.println("Customer cannot be created! " + ex.getMessage());
            session.getTransaction().rollback();
        }
    }

    public List<Customer> getAllCustomer() {
        try {
            Session session = this.sessionFactory.openSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);

            CriteriaQuery<Customer> criteriaQuery2 = criteriaQuery.select(root);
            Query<Customer> query = session.createQuery(criteriaQuery2);
            List<Customer> customerList = query.getResultList();
            return customerList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<Customer>();
        }
    }

    public Customer getCustomer(int id) throws Exception {
        Session session = this.sessionFactory.openSession();
        Customer foundCustomer = session.get(Customer.class, id);
        if (foundCustomer != null) {
            return foundCustomer;
        }
        throw new Exception("Customer does not exist!");
    }


}