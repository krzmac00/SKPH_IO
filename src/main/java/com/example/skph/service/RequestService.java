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

import com.example.skph.DTO.DayDTO;
import com.example.skph.DTO.RequestDTO;
import com.example.skph.DTO.ResourceDTO;
import com.example.skph.DTO.TaskDTO;
import com.example.skph.model.*;
import com.example.skph.repository.RequestRepository;
import com.example.skph.repository.TaskRepository;
import com.example.skph.repository.StatusHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RequestService {

    private final RequestRepository requestRepository;
//    private final TaskRepository taskRepository;
//    private final StatusHistoryRepository statusHistoryRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository, TaskRepository taskRepository, StatusHistoryRepository statusHistoryRepository) {
        this.requestRepository = requestRepository;
//        this.taskRepository = taskRepository;
//        this.statusHistoryRepository = statusHistoryRepository;
    }

    public void saveRequest(Request request) {
        requestRepository.save(request); // Zapisz dane zgłoszenia
    }

    public Request getRequestById(Long id) {
        return requestRepository.findById(id).orElse(null);
    }

    public List<Request> getRequestsByRequesterId(Long id) {
        System.out.println("I'm in service");
        return requestRepository.findByRequesterId(id); // Zakładając, że masz metodę w repozytorium
    }

    public List<Request> getRequestsByStartDate(LocalDate startDate) {
        return requestRepository.findByStartDate(startDate); // Podobnie jak wyżej
    }

    public RequestDTO getRequestDetailsByRequesterId(Long requesterId) {
        // Pobieramy listę zgłoszeń na podstawie requesterId
        List<Request> requests = requestRepository.findByRequesterId(requesterId);

        if (requests.isEmpty()) {
            throw new IllegalArgumentException("No requests found for the given requester ID.");
        }

        // Wybieramy pierwsze zgłoszenie
        Request request = requests.get(0);

        // Pobieramy dane o requesterze
        Requester requester = request.getRequester();
        String requesterFullName = requester.getFirstName() + " " + requester.getLastName();
        String address = request.getAddress().getAddress();

        // Tworzymy DTO zgłoszenia
        RequestDTO requestDTO = new RequestDTO(
                request.getId(),
                requesterFullName,
                address,
                request.getStartDate(),
                request.getEndDate(),
                request.isAccomplished(),
                null
        );

        // Pobieramy zasoby związane z tym zgłoszeniem
        Set<RequestResource> requestResources = request.getResourceList();
        List<ResourceDTO> resourceDTOs = new ArrayList<>();

        for (RequestResource requestResource : requestResources) {
            Resource resource = requestResource.getResource();

            // Tworzymy DTO zasobu
            ResourceDTO resourceDTO = new ResourceDTO(
                    resource.getId(),
                    resource.getName(),
                    resource.getAmount(),
                    resource.isToGive(),
                    (resource instanceof Food) ? ((Food) resource).getTemperature() : null,
                    (resource instanceof Food) ? ((Food) resource).isAllergyFree() : null,
                    (resource instanceof Clothes) ? ((Clothes) resource).getSize() : null,
                    (resource instanceof Clothes) ? ((Clothes) resource).getSex() : null,
                    (resource instanceof Shelter) ? ((Shelter) resource).isWithAnimals() : null,
                    (resource instanceof Other) ? ((Other) resource).getDescription() : null
            );

            resourceDTOs.add(resourceDTO);
        }

        // Przypisujemy listę zasobów do DTO zgłoszenia
        requestDTO.setResources(resourceDTOs);

//        // Pobieranie tasków
//        List<Task> tasks = taskRepository.findByRequestId(request.getId());
//        for (Task task : tasks) {
//            TaskDTO taskDTO = new TaskDTO(task.getId(), task.getResource().getName());
//
//            // Pobieranie statusów dla danego tasku
//            List<Day> days = statusHistoryRepository.findByTaskId(task.getId());
//            for (Day day : days) {
//                taskDTO.getStatusHistory().add(new DayDTO(day.getStatus(), day.getDayIndex(), day.getTime()));
//            }
//
//            requestDTO.getTasks().add(taskDTO);
//        }

        return requestDTO;
    }


}

