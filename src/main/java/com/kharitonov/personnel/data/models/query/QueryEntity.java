package com.kharitonov.personnel.data.models.query;

import com.kharitonov.personnel.data.models.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "query")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String title;
    private String description;
    @Column(name = "create_at", updatable = false)
    private LocalDateTime createAt = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private UserEntity fromUser;
    @OneToOne
    @JoinColumn(name = "for_user_id")
    private UserEntity forUser;


}
