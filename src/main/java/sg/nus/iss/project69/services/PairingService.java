package sg.nus.iss.project69.services;

import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import sg.nus.iss.project69.models.ProductMatches;
import sg.nus.iss.project69.repositories.PairingRepository;



@Service
public class PairingService {
    private static final String URL="https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/food/wine/pairing";

    @Value("{API_KEY}")
    private String key;

    @Autowired
    private PairingRepository pairRepo;

    public List<ProductMatches> getProductMatches(String food){
        //check for caching
        Optional<String> opt = pairRepo.get(food);
        String payload;

        System.out.println(">>>>> food: "+ food);

        //chuk's empty box rule
        if(opt.isEmpty()){
            System.out.println("Obtaining recommendations...");
            try{

            String url = UriComponentsBuilder
                .fromHttpUrl(URL)
                .queryParam("food", food)
                .toUriString();

                RequestEntity<Void> req = RequestEntity
                .get(url)
                .header("X-RapidAPI-Key", "2e8eacadf2msh3aae8cf390e798bp126852jsn7b2511623066")//have to directly implement the key for now, error 403 due to rapidapi bug if use key
                .build();

                RestTemplate rTemplate = new RestTemplate();

                ResponseEntity<String> resp;
                resp = rTemplate.exchange(req, String.class);
                payload = resp.getBody();
                System.out.println(">>>>>  payload: " + payload);
                pairRepo.save(food, payload);

                // final List<ProductMatches> pm = new LinkedList<>();

                // try(StringReader  strReader = new StringReader(resp.getBody())){
                //     JsonReader r = Json.createReader(strReader);
                //     JsonObject jo = r.readObject();
                //     for(JsonValue v: jo.getJsonArray(url))
                } catch (Exception e){
                    System.err.println("Error: "+e.getMessage());
                    return Collections.emptyList();
                }
            }else{
                payload = opt.get();
                System.out.println(">>>>>> cache: " + payload); //test if caching is working

            }
            //convert payload to jsonObject and then string to reader
            Reader strReader = new StringReader(payload);
            JsonReader jsonReader = Json.createReader(strReader);
            JsonObject result = jsonReader.readObject();
            JsonArray foods = result.getJsonArray("productMatches");
            List<ProductMatches> list = new LinkedList<>();
            for(int i = 0; i < foods.size(); i++){
                JsonObject jo = foods.getJsonObject(i);
                list.add(ProductMatches.create(jo));

            }
            return list;


        }
        
    }


    

