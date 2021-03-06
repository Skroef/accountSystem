package za.ac.nwu.ac.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.service.GeneralResponse;
import za.ac.nwu.ac.logic.flow.CreateAccountTypeFlow;
import za.ac.nwu.ac.logic.flow.FetchAccountTypeFlow;
import za.ac.nwu.ac.logic.flow.ModifyAccountTypeFlow;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("account-type")
public class AccountTypeController {

    private final static Logger LOGGER = LoggerFactory.getLogger(AccountTypeController.class);

    private final FetchAccountTypeFlow fetchAccountTypeFlow;
    private final CreateAccountTypeFlow createAccountTypeFlow;
    private final ModifyAccountTypeFlow modifyAccountTypeFlow;

    @Autowired
    public AccountTypeController(FetchAccountTypeFlow fetchAccountTypeFlow,
                                 @Qualifier("createAccountTypeFlowName") CreateAccountTypeFlow createAccountTypeFlow,
                                 ModifyAccountTypeFlow modifyAccountTypeFlow){
        this.fetchAccountTypeFlow = fetchAccountTypeFlow;
        this.createAccountTypeFlow = createAccountTypeFlow;
        this.modifyAccountTypeFlow = modifyAccountTypeFlow;
    }

    @GetMapping("/all")
    @ApiOperation(value = "Gets all the configured Account types.", notes = "Returns a list of account types")
    @ApiResponses(value = {
            @ApiResponse(code = 200 ,message = "Account types returned", response = GeneralResponse.class),
            @ApiResponse(code = 400 ,message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404 ,message = "Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500 ,message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<List<AccountTypeDto>>>getAll(){
        LOGGER.info("Attempting to fetch all AccountTypes");
        List<AccountTypeDto> accountTypes = fetchAccountTypeFlow.getAllAccountTypes();
        LOGGER.info("Fetched AccountTypes {} ", accountTypes);
        GeneralResponse<List<AccountTypeDto>> response = new GeneralResponse<>(true, accountTypes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("")
    @ApiOperation(value = "Creates a new AccountType.", notes = "Create a new AccountType in the DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The AccountType was created successfully", response = GeneralResponse.class),
            @ApiResponse(code = 400 ,message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 500 ,message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<AccountTypeDto>> create(
            @ApiParam(value = "Request body to create a new AccountType.", required = true)
            @RequestBody AccountTypeDto accountType) {

        LOGGER.info("Attempting to Create a AccountType");
        AccountTypeDto accountTypeResponse = createAccountTypeFlow.create(accountType);
        LOGGER.info("Created AccountType {} ", accountTypeResponse);
        GeneralResponse<AccountTypeDto> response = new GeneralResponse<>(true, accountTypeResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{mnemonic}")
    @ApiOperation(value = "Fetch the specified AccountType.", notes = "Fetches the AccountType corresponding to the given mnemonic.")
    @ApiResponses(value = {
            @ApiResponse(code = 200 ,message = "Goal Found"),
            @ApiResponse(code = 400 ,message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404 ,message = "Response Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500 ,message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<AccountTypeDto>> getAccountType(
            @ApiParam(value = "The mnemonic that uniquely identifies the AccountType.",
            example = "MILES",
            name = "mnemonic",
            required = true)
            @PathVariable("mnemonic") final String mnemonic){

        LOGGER.info("Attempting to fetch a certain AccountType");
        AccountTypeDto accountType = fetchAccountTypeFlow.getAccountTypeByMnemonic(mnemonic);
        LOGGER.info("Fetched AccountType {} ", accountType);
        GeneralResponse<AccountTypeDto> response = new GeneralResponse<>(true, accountType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{mnemonic}")
    @ApiOperation(value = "Delete the specified AccountType.", notes = "Deletes the AccountType corresponding to the given mnemonic.")
    @ApiResponses(value = {
            @ApiResponse(code = 200 ,message = "Account Deleted"),
            @ApiResponse(code = 400 ,message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404 ,message = "Response Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500 ,message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<AccountTypeDto>> deleteAccountType(
            @ApiParam(value = "The mnemonic that uniquely identifies the AccountType.",
                    example = "MILES",
                    name = "mnemonic",
                    required = true)
            @PathVariable("mnemonic") final String mnemonic){

        LOGGER.info("Attempting to delete a certain AccountType");
        AccountTypeDto accountType = modifyAccountTypeFlow.deleteAccountType(mnemonic);
        LOGGER.info("Deleted AccountType {} ", accountType);
        GeneralResponse<AccountTypeDto> response = new GeneralResponse<>(true, accountType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("{mnemonic}")
    @ApiOperation(value = "Update the specified AccountType.", notes = "Updates the AccountType corresponding to the given mnemonic.")
    @ApiResponses(value = {
            @ApiResponse(code = 200 ,message = "Account Updated"),
            @ApiResponse(code = 400 ,message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404 ,message = "Response Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500 ,message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<AccountTypeDto>> updateAccountType(
            @ApiParam(value = "The mnemonic that uniquely identifies the AccountType.",
                    example = "MILES",
                    name = "mnemonic",
                    required = true)
            @PathVariable("mnemonic") final String mnemonic,

            @ApiParam(value = "The new accountTypeName that the specified AccountType should be updated with.",
                    name = "newAccountTypeName",
                    required = true)
            @RequestParam("newAccountTypeName") final String newAccountTypeName,

            @ApiParam(value = "The optional new date with which to update the CreationDate is ISO date format (yyy-MM-dd). If empty/null it will not be updated",
                    name = "newCreationDate")
            @RequestParam(value = "newCreationDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate newCreationDate )
    {

        AccountTypeDto inputValue = new AccountTypeDto(mnemonic, newAccountTypeName, newCreationDate);
        AccountTypeDto accountType = modifyAccountTypeFlow.updateAccountType(inputValue);
        GeneralResponse<AccountTypeDto> response = new GeneralResponse<>(true, accountType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

