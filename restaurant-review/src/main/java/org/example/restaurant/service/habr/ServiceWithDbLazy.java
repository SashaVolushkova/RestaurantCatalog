package org.example.restaurant.service.habr;

import lombok.RequiredArgsConstructor;
import org.example.restaurant.model.habr.Data;
import org.example.restaurant.model.habr.LazyData;
import org.example.restaurant.repository.habr.DataRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceWithDbLazy {
    private final ServiceWithDbForTest service;

    public List<String> doSmthWithLazy(Long id) {
        Data dataById = service.getDataById(id);
        return dataById.getLazyDataList().stream()
                .map(LazyData::getData)
                .collect(Collectors.toList());
    }
}
