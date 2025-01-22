package com.example.skph.controller.dto;

public class TransportAssignmentRequest {
    private Long transportId;
    // Możesz dodać tu dane dot. trasy, ładunku, itp.

    public Long getTransportId() {
        return transportId;
    }

    public void setTransportId(Long transportId) {
        this.transportId = transportId;
    }
}
