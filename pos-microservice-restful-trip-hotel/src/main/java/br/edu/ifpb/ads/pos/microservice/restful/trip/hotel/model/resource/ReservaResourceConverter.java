package br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model.resource;

import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model.Hotel;
import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model.Quarto;
import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model.Reserva;
import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.repository.HotelRepository;
import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.repository.QuartoRepository;
import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.service.consumer.ClienteConsumer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 22/10/2017, 19:05:13
 */
@ApplicationScoped
public class ReservaResourceConverter {

    @Inject
    private HotelRepository hotelRepository;
    @Inject
    private QuartoRepository quartoRepository;
    @Inject
    private ClienteConsumer clienteConsumer;

    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public Reserva convert(ReservaResource reservaResource) throws Exception {

        try {
            String cpf = reservaResource.getClienteCpf();
            if (!clienteConsumer.exists(cpf)) {
                throw new Exception();
            }

            Hotel hotel = hotelRepository.buscarHotel(reservaResource.getHotel_id());
            Quarto quarto = quartoRepository.buscarQuarto(reservaResource.getQuarto_id());

            Date inicio = new Date(format.parse(reservaResource.getInicio()).getTime());
            Date termino = new Date(format.parse(reservaResource.getTermino()).getTime());

            return new Reserva(cpf, inicio, termino, hotel, quarto);
        } catch (ParseException ex) {
            Logger.getLogger(ReservaResourceConverter.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
