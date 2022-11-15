package org.example.restaurant.model.habr;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Data {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private Long id;

    @Column
    @Setter
    private String data;

    @Column
    @OneToMany(mappedBy= "dataLink"
            , cascade = CascadeType.PERSIST
            , fetch = FetchType.LAZY)
    @OrderBy("id")
    @Getter
    private List<LazyData> lazyDataList;
}
