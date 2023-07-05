package com.example.finalproject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SerializedName("id")
    Long id;
    @SerializedName("balance")
    int balance;

    public Client(int balance) {
        this.balance = balance;
    }
}

