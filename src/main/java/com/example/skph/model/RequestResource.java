package com.example.skph.model;

import jakarta.persistence.*;
import lombok.Getter;

@jakarta.persistence.Entity
@Table(name="request_resource")
public class RequestResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

    @Getter
    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;

    /*@Column
    @Getter
    private Long requestId;

    @Column
    @Getter
    private Long resourceId;*/

    public RequestResource(Request request, Resource resource) {
        this.request = request;
        this.resource = resource;
    }

    public RequestResource() {}
}
