package io.upschool.controller;

import io.upschool.dto.request.create.TicketBuyRequest;
import io.upschool.dto.request.search.TicketSearchRequest;
import io.upschool.dto.response.BaseResponse;
import io.upschool.dto.response.TicketResponse;
import io.upschool.entity.Ticket;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.exception.TicketSoldOut;
import io.upschool.exception.UnsupportedPaymentType;
import io.upschool.mapper.response.TicketResponseMapper;
import io.upschool.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
@Slf4j
public class TicketController {
    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);
    private final TicketService ticketService;
    private final TicketResponseMapper ticketResponseMapper;

    //--------> CREATE <--------\\
    @PostMapping
    public ResponseEntity<BaseResponse<TicketResponse>> create(@Valid @RequestBody TicketBuyRequest ticketBuyRequest) throws DataNotFoundException, DuplicateEntryException, TicketSoldOut, UnsupportedPaymentType {
        Ticket ticket = ticketService.buy(ticketBuyRequest);
        logger.warn("ticket buy info");
        var response = BaseResponse.<TicketResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(ticketResponseMapper.map(ticket))
                .build();
        return ResponseEntity.ok(response);
    }

    //--------> READ <--------\\
    @GetMapping
    public ResponseEntity<BaseResponse<Page<TicketResponse>>> getAllTickets(Pageable pageable) {
        Page<Ticket> tickets = ticketService.findAll(pageable);
        var response = BaseResponse.<Page<TicketResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(tickets.map(ticketResponseMapper::map))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/flight/{id}")
    public ResponseEntity<BaseResponse<Page<TicketResponse>>> getAllTicketsByFlightId(@PathVariable Long id, Pageable pageable) {
        Page<Ticket> tickets = ticketService.findAllByFlightId(id, pageable);
        var response = BaseResponse.<Page<TicketResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(tickets.map(ticketResponseMapper::map))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponse<List<TicketResponse>>> getAllTickets() {
        List<Ticket> tickets = ticketService.findAll();
        var response = BaseResponse.<List<TicketResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(ticketResponseMapper.map(tickets))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<TicketResponse>> getById(@PathVariable Long id) throws DataNotFoundException {
        Ticket ticket = ticketService.findById(id);
        var response = BaseResponse.<TicketResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(ticketResponseMapper.map(ticket))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponse<Page<TicketResponse>>> search(Pageable pageable, @Valid @RequestBody TicketSearchRequest ticketSearchRequest) {
        Page<Ticket> tickets = ticketService.search(ticketSearchRequest, pageable);
        var response = BaseResponse.<Page<TicketResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(tickets.map(ticketResponseMapper::map))
                .build();
        return ResponseEntity.ok(response);
    }

    //--------> UPDATE <--------\\
    @PutMapping("/{id}/cancel")
    public ResponseEntity<BaseResponse<TicketResponse>> cancel(@PathVariable Long id) throws DataNotFoundException, DuplicateEntryException {
        Ticket ticket = ticketService.cancel(id);
        var response = BaseResponse.<TicketResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(ticketResponseMapper.map(ticket))
                .build();
        return ResponseEntity.ok(response);
    }
}
