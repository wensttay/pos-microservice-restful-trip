package br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.repository;

import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model.Reserva;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 17/10/2017, 02:31:19
 */
@Stateless
public class ReservaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void salvarReserva(Reserva r) {
        entityManager.persist(r);
    }

    public void atualizarReserva(Reserva r) {
        entityManager.merge(r);
    }

    public void deletarReserva(int id) {
        Reserva merge = buscarReserva(id);
        entityManager.remove(merge);
    }

    public Reserva buscarReserva(int id) {
        return entityManager.find(Reserva.class, id);
    }

    public List<Reserva> listarReservas() {
        return entityManager
                .createQuery("SELECT r FROM Reserva r ORDER BY r.id", Reserva.class)
                .getResultList();
    }
}
