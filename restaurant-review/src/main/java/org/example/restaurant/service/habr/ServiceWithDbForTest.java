package org.example.restaurant.service.habr;

import lombok.RequiredArgsConstructor;
import org.example.restaurant.model.habr.Data;
import org.example.restaurant.repository.habr.DataRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceWithDbForTest {
    private final DataRepository dataRepository;
    @Transactional
    public void updateData(Long id, String newData) {
        Optional<Data> byId = dataRepository.findById(id);
        byId.ifPresentOrElse(data -> data.setData(newData), () -> {
            throw new RuntimeException();
        });
    }

    public Data getDataById(Long id) {
        return dataRepository.findById(id).orElseThrow();
    }
}
