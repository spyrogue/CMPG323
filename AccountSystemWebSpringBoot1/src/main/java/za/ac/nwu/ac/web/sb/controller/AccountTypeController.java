package za.ac.nwu.ac.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountType;
import za.ac.nwu.ac.domain.service.GeneralResponse;

import za.ac.nwu.ac.logic.flow.CreateAccountTypeFlow;
import za.ac.nwu.ac.logic.flow.FetchAccountTypeFlow;
import za.ac.nwu.ac.logic.flow.ModifyAccountTypeFlow;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("account-type")
public class AccountTypeController {

    private final FetchAccountTypeFlow fetchAccountTypeFlow;
    private final CreateAccountTypeFlow createAccountTypeFlow;
    private final ModifyAccountTypeFlow modifyAccountTypeFlow;

    @Autowired
    public AccountTypeController(FetchAccountTypeFlow fetchAccountTypeFlow,
                                 @Qualifier("CreateAccountTypeFlowName") CreateAccountTypeFlow createAccountTypeFlow,ModifyAccountTypeFlow modifyAccountTypeFlow) {
        this.fetchAccountTypeFlow = fetchAccountTypeFlow;
        this.createAccountTypeFlow = createAccountTypeFlow;
        this.modifyAccountTypeFlow = modifyAccountTypeFlow;
    }

    @GetMapping("/all")
    @ApiOperation(value = "Gets all the members signed up.", notes = "Returns list of all members")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account types returned", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)})
    public ResponseEntity<GeneralResponse<List<AccountTypeDto>>> getAll() {
        List<AccountTypeDto> accountTypes = fetchAccountTypeFlow.getAllAccountTypes();
        GeneralResponse<List<AccountTypeDto>> response = new GeneralResponse<>(true, accountTypes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    @ApiOperation(value = "Create new Member.", notes = "Creates a new Member in DB")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The member was created successfully", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad request", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = GeneralResponse.class)})
    public ResponseEntity<GeneralResponse<AccountTypeDto>> create(
            @ApiParam(value = "Request body to create new Member", required = true)
            @RequestBody AccountTypeDto accountType) {
        AccountTypeDto accountTypeResponse = createAccountTypeFlow.create(accountType);
        GeneralResponse<AccountTypeDto> response = new GeneralResponse<>(true, accountTypeResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{mnemonic}")
    @ApiOperation(value = "Fetches the specified Member.",notes = "Fetches the Member corresponding to the given mnemonic.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Goal found"),
            @ApiResponse(code = 400, message = "Bad request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Resource not found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = GeneralResponse.class)
    })


    public ResponseEntity<GeneralResponse<AccountTypeDto>> getAccountType(
            @ApiParam(value = "The mnemonic that uniquely identifies the AccountType.",
                    example = "MILES",
                    name = "mnemonic",
                    required = true)
            @PathVariable("mnemonic") final String mnemonic){
        AccountTypeDto accountType = fetchAccountTypeFlow.getAccountTypeByMnemonic(mnemonic);
        GeneralResponse<AccountTypeDto> response = new GeneralResponse<>(true,accountType);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("{mnemonic}")
    @ApiOperation(value = "Deletes the specified AccountType",notes = "Deletes the AccountType corresponding to given mnemonic")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "AccountType deleted"),
            @ApiResponse(code = 400, message = "Bad request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Resource not found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<AccountTypeDto>> deleteAccountType(
            @ApiParam(value = "The mnemonic that uniquely identifies the AccountType.",
                    example = "MILES",
                    name = "mnemonic",
                    required = true)
            @PathVariable("mnemonic") final String mnemonic){
        AccountTypeDto accountType = modifyAccountTypeFlow.deleteAccountType(mnemonic);
        GeneralResponse<AccountTypeDto> response = new GeneralResponse<>(true,accountType);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    @PutMapping("{mnemonic}")
    @ApiOperation(value = "Updates the specified account type",notes = "Updates the AccountType corresponding to the given mnemonic")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "AccountType updated"),
            @ApiResponse(code = 400, message = "Bad request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Resource not found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = GeneralResponse.class)
    })


    public ResponseEntity<GeneralResponse<AccountTypeDto>> updateAccountType(
            @ApiParam(value = "The mnemonic that uniquely identifies the AccountType.",
                    example = "MILES",
                    name = "mnemonic",
                    required = true)
            @PathVariable("mnemonic") final String mnemonic,

            @ApiParam(value = "The new AccountTypeName that the specified should be updated with.",
                    name = "newAccountTypeName",
                    required = true)
            @RequestParam("newAccountTypeName") final String newAccountTypeName,

        /*@ApiParam(value = "The optional new date with which to update date in ISO date format(yyyy-mm-dd),if not updated it will be null",
                name = "newCreationDate")*/
            @RequestParam(value = "newCreationDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate newCreationDate){

        AccountTypeDto accountType = modifyAccountTypeFlow.updateAccountType(mnemonic, newAccountTypeName, newCreationDate);
        GeneralResponse<AccountTypeDto> response = new GeneralResponse(true, accountType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}




