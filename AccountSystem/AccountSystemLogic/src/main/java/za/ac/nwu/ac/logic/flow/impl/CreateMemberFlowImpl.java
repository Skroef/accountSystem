package za.ac.nwu.ac.logic.flow.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.dto.MemberDto;
import za.ac.nwu.ac.logic.flow.CreateAccountTypeFlow;
import za.ac.nwu.ac.logic.flow.CreateMemberFlow;
import za.ac.nwu.ac.translator.AccountTypeTranslator;
import za.ac.nwu.ac.translator.MemberTranslator;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Transactional
@Component("createMemberFlowName")
public class CreateMemberFlowImpl implements CreateMemberFlow {

//    private static final Logger LOGGER = LoggerFactory.getLogger(CreateMemberFlowImpl.class)

    private final MemberTranslator memberTranslator;


    public CreateMemberFlowImpl(MemberTranslator memberTranslator){

        this.memberTranslator = memberTranslator;
    }

    @Override
    public MemberDto create(MemberDto member){
//        LOGGER.info("The input Object was {}", member);

        if(member.getStartDate() == null){
            member.setStartDate(LocalDate.now());
        }
//        if(member.getContactNumber() ){
//            Creat a Exception here for when mobile number excists
//        }

        return memberTranslator.create(member);
    }
}
