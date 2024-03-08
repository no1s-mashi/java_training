package com.example.enkai.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "カテゴリ名が未入力です")
    @Size(max = 255, message = "カテゴリ名は255字以内で入力してください")
    private String name;

//    @OneToMany(mappedBy = "category")
//    private List<Event> events;
}

