package za.ac.nwu.ac.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.service.GeneralResponse;
import za.ac.nwu.ac.logic.flow.FetchAccountTransactionFlow;

import java.util.List;

@RestController
@RequestMapping("account-transaction")
public class AccountTransactionController {

    private final FetchAccountTransactionFlow fetchAccountTransactionFlow;
//    private  final CreateAccountTypeFlow createAccountTypeFlow;

//    @Autowired
//    public AccountTransactionController(FetchAccountTransactionFlow fetchAccountTransactionFlow,
//                                 @Qualifier("createAccountTypeFlowName") CreateAccountTransactionFlow createAccountTransactionFlow){
//        this.fetchAccountTransactionFlow = fetchAccountTransactionFlow;
//        this.createAccountTransactionFlow = createAccountTransactionFlow;
//    }

    @Autowired
    public AccountTransactionController(FetchAccountTransactionFlow fetchAccountTransactionFlow){
        this.fetchAccountTransactionFlow = fetchAccountTransactionFlow;
    }

    @GetMapping("/all")
    @ApiOperation(value = "Gets all the configured Account types.", notes = "Returns a list of account types")
    @ApiResponses(value = {
            @ApiResponse(code = 200 ,message = "Account types returned", response = GeneralResponse.class),
            @ApiResponse(code = 400 ,message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404 ,message = "Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500 ,message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<List<AccountTransactionDto>>> getAll(){
        List<AccountTransactionDto> accountTransactions = fetchAccountTransactionFlow.getAllAccountTransactions();
        GeneralResponse<List<AccountTransactionDto>> response = new GeneralResponse<>(true, accountTransactions);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
