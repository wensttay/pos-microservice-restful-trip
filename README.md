# pos-microservice-restful-trip
Project to POS' Class

With docker-compose installed, just open the terminal here and write the command:

$ sh up.sh

Or do this:

$ mvn clean install
$ docker-compose up -d 

Wait a minute while all containers starts ... and done!

The WebServes for this aplication shoud be working on this urls:

<b>pos-microservice-restful-trip-cliente:</b>
- http://localhost:8081/pos-microservice-restful-trip-cliente/api/clientes

<b>pos-microservice-restful-trip-hotel:</b>
- http://localhost:8082/pos-microservice-restful-trip-hotel/api/hoteis
- http://localhost:8082/pos-microservice-restful-trip-hotel/api/quartos/
- http://localhost:8082/pos-microservice-restful-trip-hotel/api/reservas/

<b>pos-microservice-restful-trip-passagem:</b>
- http://localhost:8083/pos-microservice-restful-trip-passagem/api/passagens/
- http://localhost:8083/pos-microservice-restful-trip-passagem/api/reservas/
- http://localhost:8083/pos-microservice-restful-trip-passagem/api/voos/

<b>pos-microservice-restful-trip-agencia:</b>
- http://localhost:8084/pos-microservice-restful-trip-agencia/api/agencias/
- http://localhost:8084/pos-microservice-restful-trip-agencia/api/contratos/
- http://localhost:8084/pos-microservice-restful-trip-agencia/api/pacotes/

<b>Some helps:</b>

Shutdown Command:

 - $ sh down.sh
Restart Command: 
 - $ sh restart.sh

If you use postman, I make all most of possibilities of requests for this projets.
Here are the links to "import" the commands:

pos-microservice-restful-trip-cliente: 
- https://www.getpostman.com/collections/1885f0bc26c52f124da1

pos-microservice-restful-trip-hotel: 
- https://www.getpostman.com/collections/ed72060cda459bde6107

pos-microservice-restful-trip-passagem: 
- https://www.getpostman.com/collections/f04e96f976dc3f6a7dad

pos-microservice-restful-trip-agencia:
- https://www.getpostman.com/collections/cfb638c30bca329d5e1e
