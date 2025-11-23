package pl.wsb.fitnesstracker.event;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventRepository {

    @PersistenceContext
    private EntityManager em;

    public EventRepository(EntityManager em) {
        this.em = em;
    }

    List<Event> getEventsByName(String name){
        String jpql = "SELECT e FROM Event e WHERE e.name = :name";

        return em.createQuery(jpql, Event.class).setParameter("name", name).getResultList();
    }
}
