package com.dyulok.dewa.dao.customer;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
/*import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;*/
import com.dyulok.dewa.dao.customer.CustomerDao;
import com.dyulok.dewa.model.customer.Customer;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

public class CustomerDaoHnateImpl extends HibernateDaoSupport implements CustomerDao {
	
	/*protected SessionFactory getSessionFactory() {
		// create configuration using hibernate API
		Configuration configuration = new Configuration();
		/*configuration.setProperty("connection.driver_class",
				"com.mysql.jdbc.Driver");
		configuration.setProperty("hibernate.connection.url",
				"jdbc:mysql://localhost/movie_ticketing_system");
		configuration.setProperty("hibernate.connection.username", "root");
		configuration.setProperty("hibernate.connection.password", "boxmarker");
		configuration.addAnnotatedClass(Customer.class);*/
		/*return configuration.buildSessionFactory();
	}*/

	/*Configuration config=new Configuration().configure("/hibernate.cfg.xml");
	ServiceRegistryBuilder builder=new ServiceRegistryBuilder().applySettings(config.getProperties());
	ServiceRegistry serviceRegistry=builder.buildServiceRegistry();*/
	/*SessionFactory sessionFactory=getSessionFactory();
	Session session= sessionFactory.openSession();*/
	
	@Override
	public int addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(customer);
		int custId=customer.getCustomerId();
		//System.out.println("Id:"+id);
		return custId;
	}

	@Override
	public void deleteCustomer(int customerid) {
		// TODO Auto-generated method stub
		Customer cust=new Customer();
		cust.setCustomerId(customerid);
		getHibernateTemplate().delete(cust);
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(customer);
	}

	@Override
	public Customer searchCustomerbyid(int customerid) {
		// TODO Auto-generated method stub
		String queryString="FROM "+Customer.class.getName()+" WHERE customerId = :customerid";
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(queryString);
		query.setParameter("customerid", customerid);
		
		List<Customer> custList=query.list();
		return custList.get(0);
	}

	@Override
	public List<Customer> searchCustomerbyname(String name) {
		// TODO Auto-generated method stub
		String queryString="FROM "+Customer.class.getName()+" WHERE customerName LIKE :name";
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(queryString);
		query.setParameter("name", name);
		
		List<Customer> nameList=query.list();
		return nameList;
	}

	@Override
	public List<Customer> searchCustomerbyage(int age) {
		// TODO Auto-generated method stub
		String queryString="FROM "+Customer.class.getName()+" WHERE age = :age";
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(queryString);
		query.setParameter("age", age);
		List<Customer> ageList=query.list();
		return ageList;
	}

	@Override
	public List<Customer> searchCustomerbyaddress(String add) {
		// TODO Auto-generated method stub
		String queryString="FROM "+Customer.class.getName()+" WHERE address LIKE :add";
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(queryString);
		query.setParameter("add", add);
		List<Customer> addList=query.list();
		return addList;
	}

}
