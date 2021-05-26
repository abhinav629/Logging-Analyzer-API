package com.fedex.logger.dto;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class FlightSearchDTO {
    @NotNull(message = "Flight Number Can't Be Null")
    private Integer flightNumber;

    @NotNull(message = "Leg Number Can't Be Null")
    private Integer legNumber;

    @NotNull(message = "Flight Date Can't Be Null")
    private Date flightDate;

    public Integer getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Integer flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Integer getLegNumber() {
        return legNumber;
    }

    public void setLegNumber(Integer legNumber) {
        this.legNumber = legNumber;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }
}
