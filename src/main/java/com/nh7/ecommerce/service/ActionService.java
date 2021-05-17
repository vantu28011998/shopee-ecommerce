package com.nh7.ecommerce.service;

import com.nh7.ecommerce.entity.Action;
import com.nh7.ecommerce.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionService {
    @Autowired
    private ActionRepository actionRepository;
    public void saveAll(List<Action> actions){
        actionRepository.saveAll(actions);
    }
    public List<Action> findAll(){
        return (List<Action>) actionRepository.findAll();
    }
}
