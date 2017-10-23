package br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.repository;

import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.model.Passagem;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 23/10/2017, 02:36:01
 */
@Stateless
public class PassagemRepository {
    
    @PersistenceContext
    private EntityManager entityManager;

    public Passagem buscarPassagem(int passagem_id) {
        return entityManager.find(Passagem.class, passagem_id);
    }

    public void salvarPassagem(Passagem passagem) {
        entityManager.persist(passagem);
    }

    public List<Passagem> listarPassagens() {
        return entityManager
                .createQuery("SELECT p FROM Passagem p ORDER BY p.id", Passagem.class)
                .getResultList();
    }

    public void atualizarPassagem(Passagem passagem) {
        entityManager.merge(passagem);
    }

}
