package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class MainController {

    @GetMapping(value = "/api/fibonacci/{value}")
    public List<Integer> fibonacciPrint(@PathVariable int value) {
        return fibonacci(value);
    }

    public List<Integer> fibonacci(int value) {
        List<Integer> listOfNumbers = new ArrayList();
        listOfNumbers.add(0);
        listOfNumbers.add(1);
        if (value == 1) {
            listOfNumbers.remove(1);
            return listOfNumbers;
        } else {
            while (listOfNumbers.size() != value && value > 2)
                listOfNumbers.add(listOfNumbers.get(listOfNumbers.size() - 2) + listOfNumbers.get(listOfNumbers.size() - 1));
            if (listOfNumbers.size() == value) {
                return listOfNumbers;
            }
        }
        return listOfNumbers;
    }

    @GetMapping(value = "api/{number1}/minus/{number2}")
    public Integer minus(@PathVariable Integer number1, @PathVariable Integer number2) {
        return number1 - number2;
    }

    List<Lunch> lunchList = new ArrayList<>();

    @PostMapping(value = "/api/lunch")
    public Lunch createLunch(@RequestBody Lunch lunch){
        lunch.setGrams(new Random().nextInt(500));
        lunchList.add(lunch);
        return lunch;
    }

    @GetMapping(value = "/api/lunch")
    public List<Lunch> getLunch(){
        return lunchList;
    }

    @DeleteMapping(value = "/api/lunch/{lunchOrder}")
    public ResponseEntity deleteLunch(@PathVariable int lunchOrder){
        lunchList.remove(lunchOrder-1);
        return ResponseEntity.noContent().build();
    }






}



