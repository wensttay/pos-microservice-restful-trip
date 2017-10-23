package br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.model.resource;

import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.model.Passagem;
import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.model.Reserva;
import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.model.Voo;
import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.repository.PassagemRepository;
import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.repository.VooRepository;
import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.service.consumer.ClienteConsumer;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 23/10/2017, 02:30:34
 */
@ApplicationScoped
public class ReservaResourceConverter {

    @Inject
    private ClienteConsumer clienteConsumer;
    @Inject
    private VooRepository vooRepository;
    @Inject
    private PassagemRepository passagemRepository;

    public Reserva convert(ReservaResource reservaResource) throws Exception {

        String clienteCpf = reservaResource.getClienteCpf();

        if (!clienteConsumer.exists(clienteCpf)) {
            throw new Exception();
        }

        Voo voo = vooRepository.buscarVoo(reservaResource.getVoo_id());
        Passagem passagem = passagemRepository
                .buscarPassagem(reservaResource.getPassagem_id());

        return new Reserva(voo, passagem, clienteCpf);
    }
}
