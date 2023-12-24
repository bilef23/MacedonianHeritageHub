package mk.finki.ukim.diansproject.web;

import com.opencsv.exceptions.CsvException;
import mk.finki.ukim.diansproject.model.CulturalPlace;
import mk.finki.ukim.diansproject.model.UserReview;
import mk.finki.ukim.diansproject.service.CulturalPlaceService;
import mk.finki.ukim.diansproject.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CulturalPlaceController {
    private final CulturalPlaceService culturalPlaceService;
    private final ReviewService reviewService;

    public CulturalPlaceController(CulturalPlaceService culturalPlaceService, ReviewService reviewService) {
        this.culturalPlaceService = culturalPlaceService;

        this.reviewService = reviewService;
    }

    @GetMapping("/places")
    public String getMainPage(Model model) throws IOException, CsvException {

        model.addAttribute("places",culturalPlaceService.findAll());

        return "index.html";
    }
    private double calculateAverageRating(List<UserReview> reviews) {
        if (reviews.isEmpty()) {
            return 0; // or any default value
        }

        int sum = 0;
        for (UserReview review : reviews) {
            sum += review.getRating();
        }

        return (double) sum / reviews.size();
    }
 @GetMapping("/additional-information/{id}")
 @ResponseBody
 public Map<String, Object> getAdditionalInfo(@PathVariable Long id) {
     Map<String, Object> response = new HashMap<>();

     CulturalPlace culturalPlace = culturalPlaceService.findById(id);
     if (culturalPlace != null) {
         int reviews = reviewService.getAllReviewsByCulturalPlaceId(id).size();
         double rating = calculateAverageRating(reviewService.getAllReviewsByCulturalPlaceId(id));

         response.put("number", culturalPlace.getPhoneNumber());
         response.put("reviews", reviews);
         response.put("rating", rating);
     } else {
         // Handle the case where the cultural place with the given ID is not found
         response.put("error", "Cultural place not found");
     }

     return response;
 }
    @GetMapping("/listPlaces")
    public String filterPlaces( String category,  String searchName, String searchLocation, Model model) throws IOException, InterruptedException {
            List<CulturalPlace> filtered=culturalPlaceService.filter(searchName,category,searchLocation);
            model.addAttribute("places",filtered);
            return "index.html";
    }

}
