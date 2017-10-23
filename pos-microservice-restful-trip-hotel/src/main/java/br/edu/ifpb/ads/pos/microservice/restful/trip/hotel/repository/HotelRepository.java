package br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.repository;

import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model.Hotel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 02/10/2017, 10:59:44
 */
@Stateless
public class HotelRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void salvarHotel(Hotel h) {
        entityManager.persist(h);
    }

    public void atualizarHotel(Hotel h) {
        entityManager.merge(h);
    }

    public void deletarHotel(int id) {
        Hotel merge = buscarHotel(id);
        entityManager.remove(merge);
    }

    public Hotel buscarHotel(int id) {
        return entityManager.find(Hotel.class, id);
    }

    public List<Hotel> listarHoteis() {
        return entityManager
                .createQuery("SELECT h FROM Hotel h ORDER BY h.id", Hotel.class)
                .getResultList();
    }
    
}
