# pos-microservice-restful-trip
Project to POS' Class

With docker-compose installed, just open the terminal here and write the command:

$ sh up.sh

Or:

$ mvn clean install
$ docker-compose up -d 

Wait a minute while all containers starts ... and done!

The WebServes for this aplication shoud be working on this urls:

- pos-microservice-restful-trip-cliente:
http://localhost:8081/pos-microservice-restful-trip-cliente/api/clientes

- pos-microservice-restful-trip-hotel:
http://localhost:8082/pos-microservice-restful-trip-hotel/api/hoteis
http://localhost:8082/pos-microservice-restful-trip-hotel/api/quartos/
http://localhost:8082/pos-microservice-restful-trip-hotel/api/reservas/

- pos-microservice-restful-trip-passagem:
http://localhost:8083/pos-microservice-restful-trip-passagem/api/passagens/
http://localhost:8083/pos-microservice-restful-trip-passagem/api/reservas/
http://localhost:8083/pos-microservice-restful-trip-passagem/api/voos/

- pos-microservice-restful-trip-agencia:
http://localhost:8084/pos-microservice-restful-trip-agencia/api/agencias/
http://localhost:8084/pos-microservice-restful-trip-agencia/api/contratos/
http://localhost:8084/pos-microservice-restful-trip-agencia/api/pacotes/

Ps:
- Shutdown: $ sh down.sh
- Restart: $ sh restart.sh