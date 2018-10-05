package com.training.services.interfaces;

import com.training.entities.LoadEntity;
import com.training.services.BaseService;

import java.util.List;

public interface LoadService extends BaseService<LoadEntity, Long> {

    List<LoadEntity> getAll();
}
