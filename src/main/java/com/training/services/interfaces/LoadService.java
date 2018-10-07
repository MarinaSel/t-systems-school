package com.training.services.interfaces;

import com.training.models.Load;
import com.training.services.BaseService;

import java.util.List;

public interface LoadService extends BaseService<Load, Long> {

    List<Load> getAll();
}
