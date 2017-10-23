package br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.repository;

import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.model.Passagem;
import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.model.Voo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 23/10/2017, 02:36:26
 */
@Stateless
public class VooRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Voo buscarVoo(int vooId) {
        return entityManager.find(Voo.class, vooId);
    }

    public void salvarVoo(Voo voo) {
        entityManager.persist(voo);
    }

    public void atualizarVoo(Voo voo) {
        entityManager.merge(voo);
    }

    public void deletarVoo(int vooId) {
        Voo voo = buscarVoo(vooId);
        entityManager.remove(voo);
    }

    public List<Voo> listarVoos() {
        return entityManager
                .createQuery("SELECT v FROM Voo v ORDER BY v.id", Voo.class)
                .getResultList();
    }

    public List<Passagem> buscarVooPassagens(int vooId) {
        return buscarVoo(vooId).getPassagems();
    }

}
