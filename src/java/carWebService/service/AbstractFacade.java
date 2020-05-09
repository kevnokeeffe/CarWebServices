/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carWebService.service;

import java.util.List;
import javax.persistence.EntityManager;
import carWebService.Cars;
import carWebService.Users;

/**
 *
 * @author Kev
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }
    
   // Method to find cars with certain model name and year 
   public List<Cars> findModelAndYear(String cModel, int cYear){
       // Create cars List
   List<Cars> cars = (List<Cars>) getEntityManager()
                .createNamedQuery("Cars.findByCModelAndYear", Cars.class)
           // Set Paremeter cModel
                .setParameter("cModel", cModel)
           // Set Paremeter cYear
                .setParameter("cYear", cYear)
                .getResultList();
   // Return car list
        return cars;
   }
   
   // Method to find a user by userID and password
   public List<Users> findByIDAndPassword(int userID, String password){
       // Create user List
   List<Users> user = (List<Users>) getEntityManager()
                .createNamedQuery("Users.findByIDAndPassword", Users.class)
           // Set Paremeter UserID
                .setParameter("userID", userID)
           // Set Paremeter Password
                .setParameter("password", password)
                .getResultList();
   // Return user list
        return user;
   }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    Cars findByCModelAndYear(String c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Cars findByIDAndPassword(String c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    List<Cars> findByCModelAndYear(String cModel, int cYear) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
