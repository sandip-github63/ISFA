package com.isfa.clientadminpanel.home.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isfa.clientadminpanel.home.entity.HomePageField;
import com.isfa.clientadminpanel.home.repository.HomePageFieldRepository;

@Service
public class HomePageService {
    @Autowired
    private HomePageFieldRepository repository;

    public List<HomePageField> getHomePageFields() {
        return repository.findAll();
    }
    
       

        public List<Map<String, Object>> getHomePageFieldsUD() {
            List<HomePageField> homePageFields = repository.findAll();
            List<Map<String, Object>> menu = new ArrayList<>();

            for (HomePageField field : homePageFields) {
                Map<String, Object> fieldMap = new HashMap<>();
                fieldMap.put("name", field.getFieldName());
                fieldMap.put("key", field.getId());
                fieldMap.put("redirectUrl", field.getRedirectUrl());

                // Encode icon image as Base64 string
                if (field.getIconImage() != null) {
                    byte[] iconBytes = field.getIconImage();
                    String iconString = Base64.getEncoder().encodeToString(iconBytes);
                    fieldMap.put("icon", iconString);
                }

                fieldMap.put("isActive", true);
                menu.add(fieldMap);
            }

            return menu;
        }
    }



