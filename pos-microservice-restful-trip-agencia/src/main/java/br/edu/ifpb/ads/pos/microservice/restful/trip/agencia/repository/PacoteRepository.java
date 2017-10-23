package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.repository;

import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.Contrato;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.Pacote;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 23/10/2017, 07:26:14
 */
@Stateless
public class PacoteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private ContratoRepository contratoRepository;

    public Pacote buscarPacote(int pacoteId) {
        return entityManager.find(Pacote.class, pacoteId);
    }

    public void salvarPacote(Pacote pacote) {
        entityManager.persist(pacote);
    }

    public List<Pacote> listarPacotes() {
        return entityManager.createQuery("SELECT p FROM Pacote p ORDER BY p.id",
                Pacote.class).getResultList();
    }

    public void deletarPacote(int pacoteId) {
        Pacote pacote = buscarPacote(pacoteId);
        entityManager.remove(pacote);
    }

    public void atualizarPacote(Pacote pacote) {
        entityManager.merge(pacote);
    }

}
