package com.example.enkai.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "event_users")
public class EventUser extends AbstractEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @NotNull(message = "宴会は必須入力です")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "参加者は必須入力です")
    private User user;
}
