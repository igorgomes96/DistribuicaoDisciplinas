/*package br.com.ufu.repository;

import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	
	 	private static Configuration configuration;  
	    private static SessionFactory sessionFactory;  
	    private static final ThreadLocal threadSession = new ThreadLocal();  
	    private static final ThreadLocal threadTransaction = new ThreadLocal();  
	    private static final ThreadLocal threadInterceptor = new ThreadLocal();  
	  
	 static {  
	        try {  
	            configuration = new Configuration();
	            sessionFactory = configuration.configure().buildSessionFactory();  
	        } catch (Throwable ex) {  
	            throw new ExceptionInInitializerError(ex);  
	        }  
	    }  
	  
	    public static SessionFactory getSessionFactory() {  
	        return sessionFactory;  
	    }  
	  
	    public static Configuration getConfiguration() {  
	        return configuration;  
	    }  
	  
	    public static Session getSession()  
	        throws HibernateException {  
	        Session s = (Session) threadSession.get();  
	        try {  
	            if (s == null) {  
	                if (getInterceptor() != null) {  
	                    //s = getSessionFactory().openSession(getInterceptor());  
	                } else {  
	                    s = getSessionFactory().openSession();  
	                }  
	                threadSession.set(s);  
	            }  
	        } catch (HibernateException ex) {  
	            throw new RuntimeException(ex);  
	        }  
	        return s;  
	    }  
	  
	    public static void closeSession()  
	        throws HibernateException {  
	        try {  
	            Session s = (Session) threadSession.get();  
	            threadSession.set(null);  
	            if (s != null && s.isOpen()) {  
	                s.close();  
	            }  
	        } catch (HibernateException ex) {  
	            throw new RuntimeException(ex);  
	        }  
	    }  
	  
	    public static void beginTransaction()  
	        throws HibernateException {  
	        Transaction tx = (Transaction) threadTransaction.get();  
	        try {  
	            if (tx == null) {  
	                tx = getSession().beginTransaction();  
	                threadTransaction.set(tx);  
	            }  
	        } catch (HibernateException ex) {  
	            throw new RuntimeException(ex);  
	        }  
	    }  
	  
	    public static void commitTransaction()  
	        throws HibernateException {  
	        Transaction tx = (Transaction) threadTransaction.get();  
	        try {  
	            if ( tx != null && !tx.wasCommitted() && !tx.wasRolledBack() ) {  
	                tx.commit();  
	            }  
	            threadTransaction.set(null);  
	        } catch (HibernateException ex) {  
	            rollbackTransaction();  
	            throw new RuntimeException(ex);  
	        }  
	    }  
	  
	    public static void rollbackTransaction()  
	        throws HibernateException {  
	        Transaction tx = (Transaction) threadTransaction.get();  
	        try {  
	            threadTransaction.set(null);  
	            if ( tx != null && !tx.wasCommitted() && !tx.wasRolledBack() ) {  
	                tx.rollback();  
	            }  
	        } catch (HibernateException ex) {  
	            throw new RuntimeException(ex);  
	        } finally {  
	            closeSession();  
	        }  
	    }  
	  
	    public static void registerInterceptor(Interceptor interceptor) {  
	        threadInterceptor.set(interceptor);  
	    }  
	  
	    private static Interceptor getInterceptor() {  
	        Interceptor interceptor = (Interceptor) threadInterceptor.get();  
	        return interceptor;  
	    } 

}
*/