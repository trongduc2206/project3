package com.project.recruitment.service.impl;

import com.project.recruitment.entity.Recruitment;
import com.project.recruitment.repository.RecruitmentRepository;
import com.project.recruitment.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RecruitmentServiceImpl implements RecruitmentService {
    @Autowired
    private RecruitmentRepository recruitmentRepository;

    public RecruitmentServiceImpl(RecruitmentRepository recruitmentRepository){
        this.recruitmentRepository = recruitmentRepository;
    }

    @Override
    public Recruitment getCurrentRecruitment() {
        List<Recruitment> recruitments = recruitmentRepository.findByStatus(1);
        return recruitments.get(0);
    }

    @Override
    public void startNewRecruitment(Recruitment recruitment) {
        Recruitment currentRecruitment = recruitmentRepository.findByStatus(1).get(0);
        currentRecruitment.setStatus(0);
        currentRecruitment.setClosedTime(new Date());
        recruitmentRepository.save(currentRecruitment);
        recruitment.setStatus(1);
        recruitment.setStartedTime(new Date());
        recruitment.setClosedTime(null);
        recruitmentRepository.save(recruitment);
    }

    @Override
    public List<Recruitment> getClosedRecruitment() {
        return recruitmentRepository.findByStatus(0);
    }

    @Override
    public List<Recruitment> getAllRecruitment() {
        return recruitmentRepository.findAll();
    }

    @Override
    public Recruitment getById(Integer id) {
        Optional<Recruitment> recruitmentOptional = recruitmentRepository.findById(id);
        if(recruitmentOptional.isPresent()){
            return recruitmentOptional.get();
        } else {
            return null;
        }
    }

    @Override
    public void updateCurrentRecruitment(String information) {
        Recruitment recruitment = recruitmentRepository.findByStatus(1).get(0);
        recruitment.setInformation(information);
        recruitmentRepository.save(recruitment);
    }


}
