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
import za.ac.nwu.ac.domain.dto.AddMilesDto;
import za.ac.nwu.ac.domain.dto.MemberDto;
import za.ac.nwu.ac.domain.service.GeneralResponse;
import za.ac.nwu.ac.logic.flow.CreateMemberFlow;
import za.ac.nwu.ac.logic.flow.FetchMemberFlow;
import za.ac.nwu.ac.logic.flow.ModifyMemberFlow;

import java.time.LocalDate;
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

    @GetMapping("search/{contactNumber}")
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

    @DeleteMapping("delete/{contactNumber}")
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

    @PutMapping("add/{contactNumber}")
    @ApiOperation(value = "Update the specified Member.", notes = "Updates the Member corresponding to the given contact number.")
    @ApiResponses(value = {
            @ApiResponse(code = 200 ,message = "Member Updated"),
            @ApiResponse(code = 400 ,message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404 ,message = "Response Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500 ,message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<MemberDto>> addMiles(
            @ApiParam(value = "The contact number that uniquely identifies the Member.",
                    example = "0780243567",
                    name = "contactNumber",
                    required = true)
            @PathVariable("contactNumber") final String contactNumber,

            @ApiParam(value = "Enter the miles that needs to be added to the current miles",
                    name = "newMiles",
                    required = true)
            @RequestParam("newMiles") final String newMiles,

            @ApiParam(value = "The new updated name",
                    name = "newName",
                    required = false)
            @RequestParam("newName") final String newName,

            @ApiParam(value = "The new updated surname",
                    name = "newSurname",
                    required = false)
            @RequestParam("newSurname") final String newSurname,

            @ApiParam(value = "The optional new date with which to update the CreationDate is ISO date format (yyy-MM-dd). If empty/null it will not be updated",
                    name = "newStartDate")
            @RequestParam(value = "newStartDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate newStartDate)

    {

        int addMiles = Integer.parseInt(newMiles);
        MemberDto inputValue = new MemberDto(newName, newSurname, contactNumber, addMiles, newStartDate);
        MemberDto member = modifyMemberFlow.addMiles(inputValue);
        GeneralResponse<MemberDto> response = new GeneralResponse<>(true, member);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping("subtract/{contactNumber}")
    @ApiOperation(value = "Update the specified Member.", notes = "Updates the Member corresponding to the given contact number.")
    @ApiResponses(value = {
            @ApiResponse(code = 200 ,message = "Member Updated"),
            @ApiResponse(code = 400 ,message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404 ,message = "Response Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500 ,message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<MemberDto>> subtractMiles(
            @ApiParam(value = "The contact number that uniquely identifies the Member.",
                    example = "0780243567",
                    name = "contactNumber",
                    required = true)
            @PathVariable("contactNumber") final String contactNumber,

            @ApiParam(value = "Enter the miles that needs to be added to the current miles",
                    name = "newMiles",
                    required = true)
            @RequestParam("newMiles") final String newMiles,

            @ApiParam(value = "The new updated name",
                    name = "newName",
                    required = true)
            @RequestParam("newName") final String newName,

            @ApiParam(value = "The new updated surname",
                    name = "surname",
                    required = false)
            @RequestParam("newSurname") final String newSurname,

            @ApiParam(value = "The optional new date with which to update the CreationDate is ISO date format (yyy-MM-dd). If empty/null it will not be updated",
                    name = "newStartDate")
            @RequestParam(value = "newStartDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate newStartDate)
            
    {

        int subtractMiles = Integer.parseInt(newMiles);
        MemberDto inputValue = new MemberDto(newName, newSurname, contactNumber, subtractMiles, newStartDate);
        MemberDto member = modifyMemberFlow.addMiles(inputValue);
        GeneralResponse<MemberDto> response = new GeneralResponse<>(true, member);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
