package br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.repository;

import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.model.Reserva;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 23/10/2017, 02:36:14
 */
@Stateless
public class ReservaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Reserva buscarReserva(int reservaId) {
        return entityManager.find(Reserva.class, reservaId);
    }

    public List<Reserva> listarReservas() {
        return entityManager
                .createQuery("SELECT r FROM Reserva r ORDER BY r.id", Reserva.class)
                .getResultList();
    }

    public void salvarReserva(Reserva reserva) {
        entityManager.persist(reserva);
    }

    public void deletarReserva(int reservaId) {
        Reserva reserva = buscarReserva(reservaId);
        entityManager.remove(reserva);
    }

    public void atualizarReserva(Reserva reserva) {
        entityManager.merge(reserva);
    }

}
