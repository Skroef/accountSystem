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
import za.ac.nwu.ac.domain.dto.MemberDto;
import za.ac.nwu.ac.domain.service.GeneralResponse;
import za.ac.nwu.ac.logic.flow.CreateMemberFlow;
import za.ac.nwu.ac.logic.flow.FetchMemberFlow;
import za.ac.nwu.ac.logic.flow.ModifyMemberFlow;

import java.util.List;

@RestController
@RequestMapping("member")
public class MemberController {

    private final FetchMemberFlow fetchMemberFlow;
    private final CreateMemberFlow createMemberFlow;
    private final ModifyMemberFlow modifyMemberFlow;

    @Autowired
    public MemberController(FetchMemberFlow fetchMemberFlow,
                                 @Qualifier("createMemberFlowName") CreateMemberFlow createMemberFlow,
                                    ModifyMemberFlow modifyMemberFlow){
        this.fetchMemberFlow = fetchMemberFlow;
        this.createMemberFlow = createMemberFlow;
        this.modifyMemberFlow = modifyMemberFlow;
    }

    @GetMapping("/all")
    @ApiOperation(value = "Gets all the details of members.", notes = "Returns a list of member details")
    @ApiResponses(value = {
            @ApiResponse(code = 200 ,message = "Member details returned", response = GeneralResponse.class),
            @ApiResponse(code = 400 ,message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404 ,message = "Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500 ,message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<List<MemberDto>>> getAll(){
        List<MemberDto> members = fetchMemberFlow.getAllMembers();
        GeneralResponse<List<MemberDto>> response = new GeneralResponse<>(true, members);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    @ApiOperation(value = "Creates a new Member.", notes = "Create a new Member in the DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Member was created successfully", response = GeneralResponse.class),
            @ApiResponse(code = 400 ,message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 500 ,message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<MemberDto>> create(
            @ApiParam(value = "Request body to create a new Member.", required = true)
            @RequestBody MemberDto member) {

        MemberDto memberResponse = createMemberFlow.create(member);
        GeneralResponse<MemberDto> response = new GeneralResponse<>(true, memberResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{contactNumber}")
    @ApiOperation(value = "Fetch the specified Member.", notes = "Fetches the Member corresponding to the given contact number.")
    @ApiResponses(value = {
            @ApiResponse(code = 200 ,message = "Goal Found"),
            @ApiResponse(code = 400 ,message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404 ,message = "Response Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500 ,message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<MemberDto>> getMember(
            @ApiParam(value = "The contact number that uniquely identifies the member.",
                    example = "0780243567",
                    name = "contactNumber",
                    required = true)
            @PathVariable("contactNumber") final String contactNumber){

        MemberDto member = fetchMemberFlow.getMemberByContactNumber(contactNumber);
        GeneralResponse<MemberDto> response = new GeneralResponse<>(true, member);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{contactNumber}")
    @ApiOperation(value = "Delete the specified member.", notes = "Deletes the member corresponding to the given Contact Number.")
    @ApiResponses(value = {
            @ApiResponse(code = 200 ,message = "Member Deleted"),
            @ApiResponse(code = 400 ,message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404 ,message = "Response Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500 ,message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<MemberDto>> deleteMember(
            @ApiParam(value = "The contact number that uniquely identifies the Member.",
                    example = "0780243567",
                    name = "contactNumber",
                    required = true)
            @PathVariable("contactNumber") final String contactNumber){

        MemberDto member = modifyMemberFlow.deleteMember(contactNumber);
        GeneralResponse<MemberDto> response = new GeneralResponse<>(true, member);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @PutMapping("{contactNumber}")
//    @ApiOperation(value = "Update the specified Member.", notes = "Updates the Member corresponding to the given contact number.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200 ,message = "Account Updated"),
//            @ApiResponse(code = 400 ,message = "Bad Request", response = GeneralResponse.class),
//            @ApiResponse(code = 404 ,message = "Response Not found", response = GeneralResponse.class),
//            @ApiResponse(code = 500 ,message = "Internal Server Error", response = GeneralResponse.class)
//    })
//    public ResponseEntity<GeneralResponse<MemberDto>> updateMile(
//            @ApiParam(value = "The contact number that uniquely identifies the Member.",
//                    example = "0780243567",
//                    name = "contactNumber",
//                    required = true)
//            @PathVariable("contactNumber") final long contactNumber,
//
//            @ApiParam(value = "The new updated miles",
//                    name = "newMiles",
//                    required = true)
//            @RequestParam("newMiles") final long newMiles)
//
//    {
//
//        MemberDto inputValue = new MemberDto(newMiles, contactNumber);
//        MemberDto member = modifyMemberFlow.updateMiles(inputValue);
//        GeneralResponse<MemberDto> response = new GeneralResponse<>(true, member);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//
//    }

}
