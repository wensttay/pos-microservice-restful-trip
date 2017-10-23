package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.repository;

import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.Agencia;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.Contrato;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.Pacote;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 23/10/2017, 07:25:56
 */
@Stateless
public class AgenciaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void salvarAgencia(Agencia agencia) {
        entityManager.persist(agencia);
    }

    public List<Contrato> buscarAgenciaContratos(int id) {
        Agencia agencia = entityManager
                .createQuery("SELECT a FROM Agencia a WHERE a.id = :idValue",
                        Agencia.class).setParameter("idValue", id).getSingleResult();
        return agencia.getContratos();
    }

    public List<Pacote> buscarAgenciaPacotes(int id) {
        Agencia agencia = entityManager
                .createQuery("SELECT a FROM Agencia a WHERE a.id = :idValue",
                        Agencia.class).setParameter("idValue", id).getSingleResult();
        return agencia.getPacotes();
    }

    public void atualizarAgencia(Agencia agencia) {
        entityManager.merge(agencia);
    }

    public void deletarAgencia(int agenciaId) {
        Agencia agencia = buscarAgencia(agenciaId);
        entityManager.remove(agencia);
    }

    public List<Agencia> listarAgencias() {
        return entityManager.createQuery("SELECT a FROM Agencia a ORDER BY a.id",
                Agencia.class).getResultList();
    }

    public Agencia buscarAgencia(int agenciaId) {
        return entityManager.find(Agencia.class, agenciaId);
    }

}
