package com.nirbhay.rideSharingApp.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateRideDTO {

    @NotBlank(message = "Pickup Location is Required")
    private String pickupLocation;

    @NotBlank(message = "Drop Location is Required")
    private String dropLocation;

    public String getDropLocation() {
        return dropLocation;
    }

    public void setDropLocation(String dropupLocation) {
        this.dropLocation = dropupLocation;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocaton(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }
}
