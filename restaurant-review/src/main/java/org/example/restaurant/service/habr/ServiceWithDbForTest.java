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
        if(byId.isEmpty()) throw new RuntimeException(); // обработать ошибку, если не найдена сущность
        Data data = byId.get();
        data.setData(newData);
    }

    public Data getDataById(Long id) {
        return dataRepository.findById(id).get();
    }
}
