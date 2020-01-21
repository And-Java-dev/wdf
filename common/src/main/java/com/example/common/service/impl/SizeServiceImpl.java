package com.example.common.service.impl;

import com.example.common.model.Size;
import com.example.common.repository.SizeRepository;
import com.example.common.service.SizeService;
import org.springframework.stereotype.Service;

@Service
public class SizeServiceImpl implements SizeService {

    private final SizeRepository sizeRepository;

    public SizeServiceImpl(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    @Override
    public void save(Size size) {
        sizeRepository.save(size);
    }
}
