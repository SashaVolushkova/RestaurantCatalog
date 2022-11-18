package org.example.restaurant.service.impl;


import org.example.restaurant.service.ObjectReader;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service("fileReader")
@Profile("!test")
public class FileReader implements ObjectReader {
    @Override
    public String readObject() {
        return "fileReader";
    }
}
