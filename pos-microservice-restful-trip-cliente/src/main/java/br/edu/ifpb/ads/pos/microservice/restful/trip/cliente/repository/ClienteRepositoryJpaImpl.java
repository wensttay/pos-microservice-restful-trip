package br.edu.ifpb.ads.pos.microservice.restful.trip.cliente.repository;

import br.edu.ifpb.ads.pos.microservice.restful.trip.cliente.model.Cliente;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 02/10/2017, 10:25:10
 */
@RequestScoped
@Transactional
public class ClienteRepositoryJpaImpl implements ClienteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void salvarCliente(Cliente c) {
        entityManager.persist(c);
    }

    @Override
    public void atualizarCliente(Cliente c) {
        entityManager.merge(c);
    }

    @Override
    public void deletarCliente(String clienteCpf) {
        Cliente merge = buscarCliente(clienteCpf);
        entityManager.remove(merge);
    }

    @Override
    public Cliente buscarCliente(String clienteCpf) {
        return entityManager
                .createQuery("SELECT c FROM Cliente c WHERE c.cpf = :cpfValue", Cliente.class)
                .setParameter("cpfValue", clienteCpf)
                .getSingleResult();
    }

    @Override
    public Cliente[] listarCliente() {
        List<Cliente> resultList = entityManager.createQuery("SELECT c FROM Cliente c order by c.id", Cliente.class)
                .getResultList();
        return resultList.toArray(new Cliente[]{});
    }
}
