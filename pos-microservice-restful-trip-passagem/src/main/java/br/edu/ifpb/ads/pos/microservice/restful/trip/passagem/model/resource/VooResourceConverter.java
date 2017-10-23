package br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.model.resource;

import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.model.Voo;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 23/10/2017, 04:14:40
 */
@ApplicationScoped
public class VooResourceConverter {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

    public Voo convert(VooResource vooResource) {
        try {
            Voo voo = new Voo(vooResource.getCode(),
                    vooResource.getDestino(),
                    vooResource.getCompania());
            
            voo.setSaida(new Timestamp(dateFormat.parse(vooResource.getSaida()).getTime()));
            voo.setChegada(new Timestamp(dateFormat.parse(vooResource.getChegada()).getTime()));
            
            return voo;
        } catch (ParseException ex) {
            Logger.getLogger(VooResourceConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

}
