package com.service.controller;

import com.service.enums.CountryCodeEnum;
import com.service.enums.ResponseStatus;
import com.service.exception.AmbiguousEmpIdException;
import com.service.model.Response;
import com.service.model.dto.EmployeeDto;
import com.service.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/employee")
@RestController
@Log4j2
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Get all employees and their details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of employees",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "No Employees found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @Parameter(in = ParameterIn.HEADER, name = "correlation-id", required = true, allowEmptyValue = false)
    @Parameter(in = ParameterIn.HEADER, name = "session-id", required = true, allowEmptyValue = false)
    @Parameter(in = ParameterIn.HEADER, name = "locale", required = true, allowEmptyValue = false, example = "en-US")
    @Parameter(in = ParameterIn.HEADER, name = "country-code", required = true, allowEmptyValue = false, schema = @Schema(implementation = CountryCodeEnum.class), example = "UK")
    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<Response> getAllEmployees() {
        Response response = Response.builder().status(ResponseStatus.SUCCESS).data(employeeService.get()).build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Save new employee and their details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New Employee added",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @Parameter(in = ParameterIn.HEADER, name = "correlation-id", required = true, allowEmptyValue = false)
    @Parameter(in = ParameterIn.HEADER, name = "session-id", required = true, allowEmptyValue = false)
    @Parameter(in = ParameterIn.HEADER, name = "locale", required = true, allowEmptyValue = false, example = "en-US")
    @Parameter(in = ParameterIn.HEADER, name = "country-code", required = true, allowEmptyValue = false, schema = @Schema(implementation = CountryCodeEnum.class), example = "UK")
    @PostMapping(path = "/create",consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response> create(@RequestBody EmployeeDto user) {
        employeeService.save(user);

        Response response = Response.builder().status(ResponseStatus.SUCCESS).data(user).build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete specific employee details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee details deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @Parameter(in = ParameterIn.HEADER, name = "correlation-id", required = true, allowEmptyValue = false)
    @Parameter(in = ParameterIn.HEADER, name = "session-id", required = true, allowEmptyValue = false)
    @Parameter(in = ParameterIn.HEADER, name = "locale", required = true, allowEmptyValue = false, example = "en-US")
    @Parameter(in = ParameterIn.HEADER, name = "country-code", required = true, allowEmptyValue = false, schema = @Schema(implementation = CountryCodeEnum.class), example = "UK")
    @DeleteMapping(value = "/{empId}/delete")
    public ResponseEntity<Response> delete(@Parameter(description = "Id of the employee to be deleted") @PathVariable String empId) {
        employeeService.delete(empId);

        Response response = Response.builder().status(ResponseStatus.SUCCESS).build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update specific employee details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee details updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @Parameter(in = ParameterIn.HEADER, name = "correlation-id", required = true, allowEmptyValue = false)
    @Parameter(in = ParameterIn.HEADER, name = "session-id", required = true, allowEmptyValue = false)
    @Parameter(in = ParameterIn.HEADER, name = "locale", required = true, allowEmptyValue = false, example = "en-US")
    @Parameter(in = ParameterIn.HEADER, name = "country-code", required = true, allowEmptyValue = false, schema = @Schema(implementation = CountryCodeEnum.class), example = "UK")
    @PutMapping(value = "/{empId}/update")
    public ResponseEntity<Response> updateEmployeeDetails(@Parameter(description = "Id of the employee to be updated") @PathVariable String empId,
                                                   @RequestBody EmployeeDto employee) throws AmbiguousEmpIdException {
        employeeService.update(empId, employee);

        Response response = Response.builder().status(ResponseStatus.SUCCESS).build();
        return ResponseEntity.ok(response);
    }
}
