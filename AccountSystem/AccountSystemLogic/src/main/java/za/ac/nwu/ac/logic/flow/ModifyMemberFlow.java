package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.ac.domain.dto.MemberDto;

public interface ModifyMemberFlow {

    MemberDto deleteMember(String contactNumber);

    MemberDto addMiles(MemberDto member);

    MemberDto subtractMiles(MemberDto member);

//    MemberDto subtractMiles(MemberDto member);
}
