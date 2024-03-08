package com.example.enkai.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString(exclude = {"eventUsers"})
@Table(name = "events")
public class Event extends AbstractEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255, nullable = false)
    @NotEmpty(message = "宴会名は必須入力です")
    private String name;

    @Column(length = 255, nullable = false)
    @NotEmpty(message = "イベント詳細は必須入力です")
    private String detail;

    @Column(nullable = false)
    @NotNull(message = "最大参加者数が未入力です")
    @Min(value = 1, message = "最大参加者数は1以上の整数を入力してください")
    private Integer maxParticipant;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull(message = "カテゴリは必須入力です")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "主催者は必須入力です")
    private User user;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    List<EventUser> eventUsers;
}
