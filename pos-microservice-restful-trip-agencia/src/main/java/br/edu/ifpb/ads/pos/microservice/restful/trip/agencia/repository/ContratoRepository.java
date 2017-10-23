package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.repository;

import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.Contrato;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 23/10/2017, 07:26:05
 */
@Stateless
public class ContratoRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public void salvarContrato(Contrato contrato) {
        entityManager.persist(contrato);
    }
    
    public Contrato buscarContrato(int contratoId) {
        return entityManager.find(Contrato.class, contratoId);
    }
    
    public List<Contrato> listarContratos() {
        return entityManager.createQuery("SELECT c FROM Contrato c ORDER BY c.id",
                Contrato.class).getResultList();
    }
    
    public void deletarContrato(int contratoId) {
        Contrato contrato = buscarContrato(contratoId);
        entityManager.remove(contrato);
    }
    
    public void atualizarContrato(Contrato contrato) {
        entityManager.merge(contrato);
    }
    
}
