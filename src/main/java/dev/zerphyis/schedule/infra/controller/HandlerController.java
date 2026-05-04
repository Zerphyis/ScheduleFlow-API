package dev.zerphyis.schedule.infra.controller;

import dev.zerphyis.schedule.application.exception.ProfessionalException.BusinessException;
import dev.zerphyis.schedule.application.exception.ProfessionalException.DuplicateProfessionalException;
import dev.zerphyis.schedule.application.exception.ProfessionalException.InvalidProfessionalDataException;
import dev.zerphyis.schedule.application.exception.ProfessionalException.ProfessionalNotFoundException;

import dev.zerphyis.schedule.application.exception.appointmentException.ConflictException;
import dev.zerphyis.schedule.application.exception.appointmentException.ResourceNotFoundException;
import dev.zerphyis.schedule.application.exception.clientException.ClientAlreadyExistsException;
import dev.zerphyis.schedule.application.exception.clientException.ClientNotFoundException;
import dev.zerphyis.schedule.application.exception.clientException.InvalidClientDataException;

import dev.zerphyis.schedule.infra.mappers.dtos.ErrorResponse;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Tag(name = "Exception Handler", description = "Tratamento global de exceções")
@RestControllerAdvice
public class HandlerController {

    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleClientNotFound(
            ClientNotFoundException ex,
            HttpServletRequest request
    ) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    @ApiResponse(responseCode = "409", description = "Cliente já existe")
    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleClientConflict(
            ClientAlreadyExistsException ex,
            HttpServletRequest request
    ) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage(), request);
    }

    @ApiResponse(responseCode = "400", description = "Dados inválidos do cliente")
    @ExceptionHandler(InvalidClientDataException.class)
    public ResponseEntity<ErrorResponse> handleClientBadRequest(
            InvalidClientDataException ex,
            HttpServletRequest request
    ) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    @ApiResponse(responseCode = "404", description = "Profissional não encontrado")
    @ExceptionHandler(ProfessionalNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProfessionalNotFound(
            ProfessionalNotFoundException ex,
            HttpServletRequest request
    ) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    @ApiResponse(responseCode = "409", description = "Profissional duplicado")
    @ExceptionHandler(DuplicateProfessionalException.class)
    public ResponseEntity<ErrorResponse> handleProfessionalConflict(
            DuplicateProfessionalException ex,
            HttpServletRequest request
    ) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage(), request);
    }

    @ApiResponse(responseCode = "400", description = "Erro de regra de negócio")
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(
            BusinessException ex,
            HttpServletRequest request
    ) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    @ApiResponse(responseCode = "400", description = "Erro de validação")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return buildResponse(HttpStatus.BAD_REQUEST, message, request);
    }

    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(
            Exception ex,
            HttpServletRequest request
    ) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request);
    }

    @ApiResponse(responseCode = "404", description = "Recurso não encontrado")
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request
    ) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    @ApiResponse(responseCode = "400", description = "Dados inválidos do profissional")
    @ExceptionHandler(InvalidProfessionalDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidProfessional(
            InvalidProfessionalDataException ex,
            HttpServletRequest request
    ) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    @ApiResponse(responseCode = "409", description = "Conflito de agendamento")
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflict(
            ConflictException ex,
            HttpServletRequest request
    ) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage(), request);
    }

    private ResponseEntity<ErrorResponse> buildResponse(
            HttpStatus status,
            String message,
            HttpServletRequest request
    ) {

        ErrorResponse error = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message,
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(status).body(error);
    }
}