package za.ac.nwu.ac.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.service.GeneralResponse;
import za.ac.nwu.ac.logic.flow.CreateAccountTransactionFlow;
import za.ac.nwu.ac.logic.flow.FetchAccountTransactionFlow;

import java.util.List;

@RestController
@RequestMapping("account-transaction")
public class AccountTransactionController {

    private final FetchAccountTransactionFlow fetchAccountTransactionFlow;
    private  final CreateAccountTransactionFlow createAccountTransactionFlow;

    @Autowired
    public AccountTransactionController(FetchAccountTransactionFlow fetchAccountTransactionFlow,
                                        @Qualifier("createAccountTransactionFlowName") CreateAccountTransactionFlow createAccountTransactionFlow){
        this.fetchAccountTransactionFlow = fetchAccountTransactionFlow;
        this.createAccountTransactionFlow = createAccountTransactionFlow;
    }

//    @Autowired
//    public AccountTransactionController(FetchAccountTransactionFlow fetchAccountTransactionFlow){
//        this.fetchAccountTransactionFlow = fetchAccountTransactionFlow;
//    }

    @GetMapping("/all")
    @ApiOperation(value = "Gets all the configured Account transaction.", notes = "Returns a list of account transactions")
    @ApiResponses(value = {
            @ApiResponse(code = 200 ,message = "Account transaction returned", response = GeneralResponse.class),
            @ApiResponse(code = 400 ,message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404 ,message = "Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500 ,message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<List<AccountTransactionDto>>> getAll(){
        List<AccountTransactionDto> accountTransactions = fetchAccountTransactionFlow.getAllAccountTransactions();
        GeneralResponse<List<AccountTransactionDto>> response = new GeneralResponse<>(true, accountTransactions);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    @ApiOperation(value = "Creates a new AccountTransaction.", notes = "Create a new AccountTransaction in the DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The AccountTransaction was created successfully", response = GeneralResponse.class),
            @ApiResponse(code = 400 ,message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 500 ,message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<AccountTransactionDto>> create(
            @ApiParam(value = "Request body to create a new AccountTransaction.", required = true)
            @RequestBody AccountTransactionDto accountTransaction) {

        AccountTransactionDto accountTransactionResponse = createAccountTransactionFlow.create(accountTransaction);
        GeneralResponse<AccountTransactionDto> response = new GeneralResponse<>(true, accountTransactionResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    @GetMapping("{mnemonic}")
//    @ApiOperation(value = "Fetch the specified AccountType.", notes = "Fetches the AccountType corresponding to the given mnemonic.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200 ,message = "Goal Found"),
//            @ApiResponse(code = 400 ,message = "Bad Request", response = GeneralResponse.class),
//            @ApiResponse(code = 404 ,message = "Response Not found", response = GeneralResponse.class),
//            @ApiResponse(code = 500 ,message = "Internal Server Error", response = GeneralResponse.class)
//    })
//    public ResponseEntity<GeneralResponse<AccountTypeDto>> getAccountType(
//            @ApiParam(value = "The mnemonic that uniquely identifies the AccountType.",
//                    example = "MILES",
//                    name = "mnemonic",
//                    required = true)
//            @PathVariable("mnemonic") final String mnemonic){
//
//        AccountTypeDto accountType = fetchAccountTypeFlow.getAccountTypeByMnemonic(mnemonic);
//        GeneralResponse<AccountTypeDto> response = new GeneralResponse<>(true, accountType);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
}
