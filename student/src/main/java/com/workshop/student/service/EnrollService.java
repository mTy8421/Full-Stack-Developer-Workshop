package com.workshop.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.student.entity.EnrollEntity;
import com.workshop.student.repository.EnrollRepository;

@Service
public class EnrollService {
    @Autowired
    private EnrollRepository enrollRepository;

    public List<EnrollEntity> getEnrollAll() {
        return enrollRepository.findAll();
    }

    public EnrollRepository getEnrollById(Integer enrollId) {
        Optional<EnrollEntity> enroll = enrollRepository.findById(enrollId);
        if (enroll.isPresent()) {
            enroll.get();
        }
        return null;
    }

    public EnrollRepository saveEnroll(EnrollEntity enrollEntity) {
        EnrollEntity enroll = enrollRepository.save(enrollEntity);
        return (EnrollRepository) enroll;
    }

    public void deleteEnrollById(Integer enrollId) {
        enrollRepository.deleteById(enrollId);
    }
}
