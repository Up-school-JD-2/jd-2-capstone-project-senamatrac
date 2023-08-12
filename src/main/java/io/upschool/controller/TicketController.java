package io.upschool.controller;

import io.upschool.dto.request.TicketBuyRequest;
import io.upschool.dto.response.BaseResponse;
import io.upschool.dto.response.TicketResponse;
import io.upschool.entity.Ticket;
import io.upschool.exception.DataCannotDelete;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.exception.TicketSoldOut;
import io.upschool.mapper.response.TicketResponseMapper;
import io.upschool.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;
    private final TicketResponseMapper ticketResponseMapper;

    //--------> CREATE <--------\\
    @PostMapping
    public BaseResponse<TicketResponse> create(@Valid @RequestBody TicketBuyRequest ticketBuyRequest) throws DataNotFoundException, DuplicateEntryException, TicketSoldOut {
        Ticket ticket = ticketService.buy(ticketBuyRequest);
        return BaseResponse.<TicketResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(ticketResponseMapper.map(ticket))
                .build();
    }
    //--------> READ <--------\\
    @GetMapping
    public BaseResponse<Page<TicketResponse>> getAllTickets(Pageable pageable) {
        Page<Ticket> tickets = ticketService.findAll(pageable);
        return BaseResponse.<Page<TicketResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(tickets.map(ticketResponseMapper::map))
                .build();
    }

    @GetMapping("/flight/{id}")
    public BaseResponse<Page<TicketResponse>> getAllTicketsByFlightId(@PathVariable Long id, Pageable pageable) {
        Page<Ticket> tickets = ticketService.findAllByFlightId(id,pageable);
        return BaseResponse.<Page<TicketResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(tickets.map(ticketResponseMapper::map))
                .build();
    }
    @GetMapping("/all")
    public BaseResponse<List<TicketResponse>> getAllTickets() {
        List<Ticket> tickets = ticketService.findAll();
        return BaseResponse.<List<TicketResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(ticketResponseMapper.map(tickets))
                .build();
    }

    @GetMapping("/{id}")
    public BaseResponse<TicketResponse> getById(@PathVariable Long id) throws DataNotFoundException {
        Ticket ticket = ticketService.findById(id);
        return BaseResponse.<TicketResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(ticketResponseMapper.map(ticket))
                .build();
    }

    //--------> UPDATE <--------\\
    @PutMapping("/{id}/cancel")
    public BaseResponse<TicketResponse> cancel(@PathVariable Long id) throws DataNotFoundException, DuplicateEntryException {
        Ticket ticket = ticketService.cancel(id);
        return BaseResponse.<TicketResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(ticketResponseMapper.map(ticket))
                .build();
    }

    //--------> DELETE <--------\\
    @DeleteMapping({"/{id}"})
    public BaseResponse<String> delete(@PathVariable Long id) throws DataNotFoundException, DataCannotDelete {
        return BaseResponse.<String>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody("soon")
                .build();
    }
}
