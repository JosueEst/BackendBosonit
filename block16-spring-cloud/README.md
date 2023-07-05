# 16. Spring Cloud

## Proyecto Cloud

#### **Nombre proyecto Maven:**  block16-spring-cloud

### **1. block16-01-app-cliente-viaje:**

- **Passenger:** 

        idPassenger, name, surname, age, email, phoneNumber


- Se ha realizado un **CRUD básico** (buscar por nombre y por id, crear, actualizar y borrar).

<br>

- **Trip:**

        idTrip, origin, destination, departureDate, arrivalDate

- Endpoints:

    - Basic CRUD for trip.
    - Add passenger to trip. We will use the id of a passenger and the id of a trip; it will look like this:

            http://localhost:8080/trip/addPassenger/{idTrip}/{idPassenger}

    - We will keep a count of passengers in each trip, as adding a passenger to a trip should be limited to a count of 40 passengers.

            localhost:8080/trip/passenger/count/{tripId}

    - An endpoint to **change** the availability of the trip, as it's possible for the bus to break down.

            localhost:8080/trip/{idTrip}/{status}

    - Finally, we will create an endpoint that show the availability of the trip.

            localhost:8080/trip/status/show/{idTrip}

- *We need to consider that both entities are connected to a database (embedded H2).*

#

### **2. block16-02-app-ticket**

We will create the backend-Front application with the following entity:

- **Ticket (Stored in a different database than trips or customers).**
- Variables:

        idTicket, passengerId, passengerName, passengerSurname, passengerEmail, tripOrigin, tripDestination, tripDepartureDate y tripArrivalDate.

We need to create two identical entities for the backend but without database access since we will use RestTemplate to retrieve variables of customer and trip types

<br>

- Endpoints:

We will create two endpoints:

-   *To create a bus ticket:*


        localhost:8080/ticket/{passengerId}/{idTrip}

- *To show a bus ticket:*

        localhost:8080/ticket/{idTicket}

<br>

- *For this part, we need to understand the use of RestTemplate or Feign, which will make use of the backend application running simultaneously with this one.*


#

### **3. block16-03-eureka-naming-server**

Crearemos la aplicación eureka-naming-server.


**What is eureka server?**

The Eureka server acts as a centralized registry where services can register themselves when they start up and provide information about their location, status, and capabilities. Other services can consult the Eureka server to discover and locate the necessary services to perform a specific function.

#

### **4. block16-04-gateway-api**

We will create the API Gateway application, which will open the ports and allow us to associate our microservices with Eureka.

#

### **5.'Dockerizing' the app**

Finally, it is executed in Docker containers to test the architecture of the app, respecting how they depend on databases and each other; it is recommended to use 'Docker Compose' (docker-compose.yml) with 'Dockerfiles'.

    