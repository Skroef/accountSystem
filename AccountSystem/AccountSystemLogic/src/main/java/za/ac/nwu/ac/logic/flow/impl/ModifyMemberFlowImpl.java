package za.ac.nwu.ac.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.dto.AddMilesDto;
import za.ac.nwu.ac.domain.dto.MemberDto;
import za.ac.nwu.ac.logic.flow.ModifyMemberFlow;
import za.ac.nwu.ac.translator.MemberTranslator;

import javax.transaction.Transactional;

@Transactional
@Component
public class ModifyMemberFlowImpl implements ModifyMemberFlow {

    private final MemberTranslator memberTranslator;

    @Autowired
    public ModifyMemberFlowImpl(MemberTranslator memberTranslator){
        this.memberTranslator = memberTranslator;
    }


    @Override
    public MemberDto deleteMember(String contactNumber){
        MemberDto result = memberTranslator.deleteMember(contactNumber);
        return result;
    }

    @Override
    public MemberDto addMiles(MemberDto member){
        int currentBalance = memberTranslator.getMemberByContactNumber(member.getContactNumber()).getMiles();
        currentBalance = currentBalance + member.getMiles();
        member.setMiles(currentBalance);
        MemberDto result = memberTranslator.addMiles(member);
        return result;
    }

    @Override
    public MemberDto subtractMiles(MemberDto member){
        int currentBalance = memberTranslator.getMemberByContactNumber(member.getContactNumber()).getMiles();
        currentBalance = currentBalance - member.getMiles();
        member.setMiles(currentBalance);
        MemberDto result = memberTranslator.subtractMiles(member);
        return result;
    }

}
