package com.bosonit.formacion.events;

import lombok.Data;
import java.util.Date;

/**
 * Clase que representa metadatos de nuestros mensajes, junto con el contenido en sí del mensaje
 * @param <T>
 */
@Data
public abstract class Event <T>{
    private String id; // Metadato
    private Date date; //Metadato
    private EventType type; //Metadato
    private T data; //Mensaje - dato
}
