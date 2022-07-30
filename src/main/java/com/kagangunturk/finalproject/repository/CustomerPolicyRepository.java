package com.kagangunturk.finalproject.repository;


import com.kagangunturk.finalproject.model.CustomerPolicy;
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
public class CustomerPolicyRepository {

    private SessionFactory sessionFactory;


    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void createCustomerPolicy(List<CustomerPolicy> customerPolicyList) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();

        try {
            for (CustomerPolicy customerPolicy : customerPolicyList) {
                session.save(customerPolicy);
            }
            session.getTransaction().commit();
            System.out.println("CustomerPolicy is created!");
        } catch (Exception ex) {
            System.out.println("CustomerPolicy cannot be created! " + ex.getMessage());
            session.getTransaction().rollback();
        }
    }

    public List<CustomerPolicy> getAllCustomerPolicy() {
        try {
            Session session = this.sessionFactory.openSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<CustomerPolicy> criteriaQuery = criteriaBuilder.createQuery(CustomerPolicy.class);
            Root<CustomerPolicy> root = criteriaQuery.from(CustomerPolicy.class);

            CriteriaQuery<CustomerPolicy> criteriaQuery2 = criteriaQuery.select(root);
            Query<CustomerPolicy> query = session.createQuery(criteriaQuery2);
            List<CustomerPolicy> customerPolicyList = query.getResultList();
            return customerPolicyList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<CustomerPolicy>();
        }
    }

    public CustomerPolicy getCustomerPolicy(int id) throws Exception {
        Session session = this.sessionFactory.openSession();
        CustomerPolicy foundCustomerPolicy = session.get(CustomerPolicy.class, id);
        if (foundCustomerPolicy != null) {
            return foundCustomerPolicy;
        }
        throw new Exception("CustomerPolicy does not exist!");
    }


}
