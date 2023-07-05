package com.example.finalproject.controllers;
import com.example.finalproject.entity.Result;
import com.example.finalproject.entity.Client;
import com.example.finalproject.repo.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@EnableAutoConfiguration
public class MainController {
    @Autowired
    ClientRepo clientRepo;

    @RequestMapping("/hello")
    @ResponseBody
    public String helloCourse() {
        System.out.println("123");
        return "Hello SkillFactory!";
    }

    @GetMapping("/getBalance/{id}")
    @ResponseBody
    public String getBalance(@PathVariable Long id) {
        Result result = new Result();
        Optional<Client> client = clientRepo.findById(id);
        if (client.isPresent()) {
            result.setValue(client.get().getBalance());
            result.setError("");
        }
        else {
            result.setValue(-1);
            result.setError("Клиента с таким id не существует");
        }
        return Result.serializedBalance(result);
    }

    @GetMapping("/takeMoney/{id}/{value}")
    @ResponseBody
    public String takeMoney(@PathVariable Long id, @PathVariable int value) {
        Result result = new Result();
        Optional<Client> client = clientRepo.findById(id);
        if (client.isPresent()) {
            // если недостаточно средств
            if (value > client.get().getBalance()){
                result.setValue(0);
                result.setError("Недостаточно средств для выполнения операции");
            }else {
                client.get().setBalance(client.get().getBalance()-value);
                clientRepo.save(client.get());
                result.setValue(1);
                result.setError("");
            }
        }
        else {
            result.setValue(-1);
            result.setError("Клиента с таким id не существует");
        }
        return Result.serializedBalance(result);
    }

    @GetMapping("/putMoney/{id}/{value}")
    @ResponseBody
    public String putMoney(@PathVariable Long id, @PathVariable int value) {
        Result result = new Result();
        Optional<Client> client = clientRepo.findById(id);
        if (client.isPresent()) {
            // если есть деньги на счете
                client.get().setBalance(client.get().getBalance()+value);
                clientRepo.save(client.get());
                result.setValue(1);
                result.setError("");
        }
        else {
            result.setValue(0);
            result.setError("Клиента с таким id не существует");
        }
        return Result.serializedBalance(result);
    }
}
