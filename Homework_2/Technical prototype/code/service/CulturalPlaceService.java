package mk.finki.ukim.diansproject.service;

import mk.finki.ukim.diansproject.model.CulturalPlace;

import java.io.IOException;
import java.util.List;

public interface CulturalPlaceService {
    List<CulturalPlace> findAll();
    List<CulturalPlace> filter(String name,String category,String location) throws IOException, InterruptedException;
}
