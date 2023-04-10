package hibernate;

import Personel.Destination;
import Personel.Status;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DestinationHib {
    EntityManager entityManager = null;
    EntityManagerFactory emf = null;

    public DestinationHib(EntityManagerFactory entityManagerFactory) {
        this.emf = entityManagerFactory;
    }
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public DestinationHib(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public void createDestination(Destination destination) {
        if (entityManager == null) {
            throw new IllegalStateException("entityManager is null - please initialize or inject it before calling deleteDestination()");
        }
        entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(destination);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) entityManager.close();
        }
    }

    public void updateDestination(Destination destination) {
        if (entityManager == null) {
            throw new IllegalStateException("entityManager is null - please initialize or inject it before calling deleteDestination()");
        }
        entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(destination);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) entityManager.close();
        }
    }

    public void deleteDestination(Destination destination){
        if (entityManager == null) {
            throw new IllegalStateException("entityManager is null - please initialize or inject it before calling deleteDestination()");
        }
        entityManager.getTransaction().begin();
        entityManager.remove(destination);
        entityManager.getTransaction().commit();
    }

    public Destination getDestinationById(int id) {
        entityManager = emf.createEntityManager();
        Destination destination = null;
        try {
            entityManager.getTransaction().begin();
            destination = entityManager.find(Destination.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No such cargo by given Id");
        }
        return destination;
    }

    public List<Destination> getAllDestination() {
        entityManager = emf.createEntityManager();
        try {
            CriteriaQuery<Object> query = entityManager.getCriteriaBuilder().createQuery();
            query.select(query.from(Destination.class));
            Query q = entityManager.createQuery(query);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return new ArrayList<>();
    }

    public List<Destination> getDataByFilter(Status status, LocalDate arrivalDate, LocalDate departureDate) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TypedQuery<Destination> query = em.createQuery("SELECT u FROM Destination u WHERE status = :status AND arrivalDate <= :departureDate AND departureDate >= :arrivalDate", Destination.class);
            query.setParameter("status", status);
            query.setParameter("arrivalDate", arrivalDate);
            query.setParameter("departureDate", departureDate);
            return query.getResultList();
        } catch (NoResultException ex) {
            System.err.println("not found");
            return new ArrayList<>();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
