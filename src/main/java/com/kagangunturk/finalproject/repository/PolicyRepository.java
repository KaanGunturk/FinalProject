package com.kagangunturk.finalproject.repository;


import com.kagangunturk.finalproject.model.Policy;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Repository
public class PolicyRepository {
    private SessionFactory sessionFactory;
    private final String COMMA_DELIMITER = ",";

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void createPolicy(List<Policy> policyList) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();

        try {
            for (Policy policy : policyList) {
                session.save(policy);
            }
            session.getTransaction().commit();
            System.out.println("Policy is created!");
        } catch (Exception ex) {
            System.out.println("Policy cannot be created! " + ex.getMessage());
            session.getTransaction().rollback();
        }
    }

    public List<Policy> getAllCPolicy() {
        try {
            Session session = this.sessionFactory.openSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Policy> criteriaQuery = criteriaBuilder.createQuery(Policy.class);
            Root<Policy> root = criteriaQuery.from(Policy.class);

            CriteriaQuery<Policy> criteriaQuery2 = criteriaQuery.select(root);
            Query<Policy> query = session.createQuery(criteriaQuery2);
            List<Policy> policyList = query.getResultList();
            return policyList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<Policy>();
        }
    }

    public Policy getPolicy(int id) throws Exception {
        Session session = this.sessionFactory.openSession();
        Policy foundPolicy = session.get(Policy.class, id);
        if (foundPolicy != null) {
            return foundPolicy;
        }
        throw new Exception("Policy does not exist!");
    }

    public Map<String, Double> getPolicyNameWithTotalAmount() {
        Session session = this.sessionFactory.openSession();
        String sql = "select policy_name, sum(payments_amount) as total_amount from policy inner join customer_policy on customer_policy.policy_id=policy.policy_id inner join payments on payments.customerpolicy_id=customer_policy.customerpolicy_id group by policy_name";
        SQLQuery query = session.createSQLQuery(sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List list = query.list();

        Map<String, Double> result = new TreeMap<>();

        for (int i = 0; i < list.size(); i++){
            Map row =(Map) list.get(i);
            String policyName = (String) row.get("policy_name");
            Double totalAmount = (Double) row.get("total_amount");

            result.put(policyName,totalAmount);
        }

        return result;

    }

    public Map<String, BigInteger> getPolicyNameByTotalUser() {
        Session session = this.sessionFactory.openSession();
        String sql = "select  policy.policy_name,temp.count as number_of_user from (select customer_policy.policy_id, count (*) from customer_policy group by customer_policy.policy_id) as temp inner join policy on temp.policy_id=policy.policy_id";
        SQLQuery query = session.createSQLQuery(sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List list = query.list();

        Map<String, BigInteger> result = new TreeMap<>();

        for (int i = 0; i < list.size(); i++){
            Map row =(Map) list.get(i);
            String policyName = (String) row.get("policy_name");
            BigInteger totalAmount = (BigInteger) row.get("number_of_user");

            result.put(policyName,totalAmount);
        }
        return result;

    }

}
