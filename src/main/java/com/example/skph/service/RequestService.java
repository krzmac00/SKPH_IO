//package com.example.skph.service;
//
//import com.example.skph.model.Request;
//import com.example.skph.model.Requester;
//import com.example.skph.repository.RequestRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Service
//public class RequestService {
//    private final RequestRepository requestRepository;
//
//    @Autowired
//    public RequestService(RequestRepository requestRepository) {
//        this.requestRepository = requestRepository;
//    }
//
//    @Transactional
//    public Request getRequestById(Long id) {
//        return requestRepository.findById(id).isPresent() ? requestRepository.findById(id).get() : null;
//    }
//
//    @Transactional
//    public List<Request> getRequestsByRequester(Requester requester) {
//        return requestRepository.findByRequester(requester);
//    }
//
//    @Transactional
//    public List<Request> getRequestsByStartDate(LocalDate startDate) {
//        return requestRepository.findByStartDate(startDate);
//    }
//}

package com.example.skph.service;

import com.example.skph.model.Request;
import com.example.skph.model.Requester;
import com.example.skph.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RequestService {

    private final RequestRepository requestRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public void saveRequest(Request request) {
        requestRepository.save(request); // Zapisz dane zgłoszenia
    }

    public Request getRequestById(Long id) {
        return requestRepository.findById(id).orElse(null);
    }

    public List<Request> getRequestsByRequesterId(Long id) {
        return requestRepository.findByRequesterId(id); // Zakładając, że masz metodę w repozytorium
    }

    public List<Request> getRequestsByStartDate(LocalDate startDate) {
        return requestRepository.findByStartDate(startDate); // Podobnie jak wyżej
    }
}

