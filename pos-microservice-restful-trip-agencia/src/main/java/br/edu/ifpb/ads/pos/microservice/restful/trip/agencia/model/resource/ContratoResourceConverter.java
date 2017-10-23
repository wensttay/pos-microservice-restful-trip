package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.resource;

import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.Contrato;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.Pacote;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.repository.PacoteRepository;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.consumer.ClienteConsumer;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.consumer.HotelReservaConsumer;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.consumer.HotelReservaRequestEntity;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.consumer.PassagemReservaConsumer;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.consumer.PassagemReservaResquestEntity;
import java.text.SimpleDateFormat;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 23/10/2017, 07:25:30
 */
@ApplicationScoped
public class ContratoResourceConverter {

    @Inject
    private ClienteConsumer clienteConsumer;

    @Inject
    private PacoteRepository pacoteRepository;

    public Contrato convert(ContratoResource contratoResource) throws Exception {

        if (!clienteConsumer.exists(contratoResource.getClienteCpf())) {
            throw new Exception();
        }

        Pacote pacote = pacoteRepository
                .buscarPacote(contratoResource.getPacoteId());
        
        return new Contrato(contratoResource.getClienteCpf(), pacote);

    }

}
