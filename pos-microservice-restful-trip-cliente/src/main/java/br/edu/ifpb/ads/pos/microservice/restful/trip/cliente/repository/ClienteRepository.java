/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.ads.pos.microservice.restful.trip.cliente.repository;

import br.edu.ifpb.ads.pos.microservice.restful.trip.cliente.model.Cliente;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 22/10/2017, 14:42:33
 */
public interface ClienteRepository {

    void atualizarCliente(Cliente c);

    Cliente buscarCliente(String clienteCpf);

    void deletarCliente(String clienteCpf);

    Cliente[] listarCliente();

    void salvarCliente(Cliente c);

}
