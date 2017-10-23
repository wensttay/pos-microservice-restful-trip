package br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model.skinny;

import java.io.Serializable;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 22/10/2017, 18:16:29
 */
public class SkinnyRef implements Serializable {
    
    private String descricao;
    private String uri;

    public SkinnyRef() {
    }

    public SkinnyRef(String descricao, String uri) {
        this.descricao = descricao;
        this.uri = uri;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "SkinnyRef{" + "descricao=" + descricao + ", uri=" + uri + '}';
    }
   
}
