package br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.repository;

import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model.Quarto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 19/10/2017, 14:36:07
 */
@Stateless
public class QuartoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void salvarQuarto(Quarto q) {
        entityManager.persist(q);
    }

    public void atualizarQuarto(Quarto q) {
        entityManager.merge(q);
    }

    public void deletarQuarto(int quartoId) {
        Quarto merge = buscarQuarto(quartoId);
        entityManager.remove(merge);
    }

    public Quarto buscarQuarto(int id) {
        return entityManager.find(Quarto.class, id);
    }

    public List<Quarto> listarQuartos() {
        return entityManager
                .createQuery("SELECT q FROM Quarto q ORDER BY q.id", Quarto.class)
                .getResultList();
    }
    
}
