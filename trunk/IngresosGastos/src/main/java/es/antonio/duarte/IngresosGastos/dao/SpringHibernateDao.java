package es.antonio.duarte.IngresosGastos.dao;

/**
 * Ver tutotial
 * http://www.adictosaltrabajo.com/tutoriales/tutoriales.php?pagina=desarrolloRapidoJava
 */

1. @Repository  
2. public class SpringHibernateDao extends HibernateDaoSupport implements Dao {  
3.   
4.     @Autowired  
5.     public SpringHibernateDao(SessionFactory sessionFactory) {  
6.         super.setSessionFactory(sessionFactory);  
7.     }  
8.   
9.     @Transactional  
10.     public void persist(Object entity) {  
11.         getHibernateTemplate().saveOrUpdate(entity);  
12.     }  
13.   
14.     @Transactional  
15.     public void persist(Object[] entities) {  
16.         for (int i = 0; i < entities.length; i++) {  
17.             persist(entities[i]);  
18.         }  
19.     }  
20.   
21.     @Transactional(readOnly = true)  
22.     public <T> List<T> find(Class<T> entityClass) {  
23.         final List<T> entities = getHibernateTemplate().loadAll(entityClass);  
24.         return entities;  
25.     }  
26.   
27.     @Transactional(readOnly = true)  
28.     public <T> T load(Class<T> entityClass, Serializable id) {  
29.         final T entity = (T)getHibernateTemplate().load(entityClass, id);  
30.         return entity;  
31.     }  
32.   
33.     @Transactional(readOnly = true)  
34.     public <T> List<T> find(String hql) {  
35.         final List<T> entities = getHibernateTemplate().find(hql);  
36.         return entities;  
37.     }  
38.   
39. }  