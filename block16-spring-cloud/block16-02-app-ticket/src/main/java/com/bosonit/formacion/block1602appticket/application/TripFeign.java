package com.bosonit.formacion.block1602appticket.application;

import com.bosonit.formacion.block1602appticket.domain.Trip;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "feignTrip", url = "${feignTrip.url}")
public interface TripFeign {
    @GetMapping("trip/{idTrip}")
    public Trip getTripById (@PathVariable int idTrip);
}
