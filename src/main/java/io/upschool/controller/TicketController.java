package io.upschool.controller;

import io.upschool.dto.request.TicketRequest;
import io.upschool.dto.response.TicketResponse;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.mapper.response.TicketResponseMapper;
import io.upschool.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;
    private final TicketResponseMapper ticketResponse;

    @PostMapping
    public TicketResponse create(@Valid @RequestBody TicketRequest ticketRequest) throws DataNotFoundException, DuplicateEntryException {
        return ticketResponse.map(ticketService.save(ticketRequest));
    }
}
