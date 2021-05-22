package com.nh7.ecommerce.service;

import com.nh7.ecommerce.dto.developer.ActionDto;
import com.nh7.ecommerce.entity.Action;
import com.nh7.ecommerce.repository.ActionRepository;
import com.nh7.ecommerce.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActionService {
    @Autowired
    private ActionRepository actionRepository;
    @Autowired
    private ModelMapperUtil modelMapperUtil;
    public void saveAll(List<ActionDto> actions){
        System.out.println("ACTION SERVICE");
        List<Action> acts = new ArrayList<>();
        for (ActionDto actionDto : actions){
            Action action = new Action();
            action.setName(actionDto.getName());
            acts.add(action);
        }
        actionRepository.saveAll(acts);
    }
    public List<ActionDto> findAll(){
        return modelMapperUtil.mapList( (List<Action>) actionRepository.findAll(), ActionDto.class);
    }
}
