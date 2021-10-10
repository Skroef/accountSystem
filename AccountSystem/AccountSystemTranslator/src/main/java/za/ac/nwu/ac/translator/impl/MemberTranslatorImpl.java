package za.ac.nwu.ac.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.MemberDto;
import za.ac.nwu.ac.domain.persistence.Member;
import za.ac.nwu.ac.repo.persistence.MemberRepository;
import za.ac.nwu.ac.translator.MemberTranslator;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class MemberTranslatorImpl implements MemberTranslator {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberTranslatorImpl(MemberRepository memberRepository){

        this.memberRepository = memberRepository;
    }

    @Override
    public List<MemberDto> getAllMembers(){

        List<MemberDto> memberDtos = new ArrayList<>();
        try{
            for (Member member : memberRepository.findAll()){
                memberDtos.add(new MemberDto(member));
            }
        }
        catch(Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }

        return memberDtos;
    }

    @Override
    public MemberDto create(MemberDto memberDto){
        try{
            Member member = memberRepository.save(memberDto.getMember());
            return new MemberDto(member);
        }
        catch(Exception e){
            throw new RuntimeException("Unable to save to the DB", e);
        }
    }


    @Override
    public MemberDto getMemberByContactNumber(long contactNumber){
        try{
            Member member = memberRepository.getMemberByContactNumber(contactNumber);
            return new MemberDto(member);
        } catch(Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }
    }

    @Override
    @Transactional
    public MemberDto deleteMember(long contactNumber){
        try{
            Member member = memberRepository.getMemberByContactNumber(contactNumber);
            memberRepository.deleteMember(contactNumber);
            return new MemberDto(member);
        } catch(Exception e){
            throw new RuntimeException("Unable to delete entity from the DB", e);
        }
    }


//    @Override
//    @Transactional
//    public MemberDto updateMiles(MemberDto member){
//        try{
//            memberRepository.updateMiles(member.getContactNumber(), member.getMiles());
//            return member;
//        } catch(Exception e){
//            throw new RuntimeException("Unable to update entity in the DB", e);
//        }
//    }

}
