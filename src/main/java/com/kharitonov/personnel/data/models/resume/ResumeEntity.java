package com.kharitonov.personnel.data.models.resume;

import com.kharitonov.personnel.data.models.candidate.CandidateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "resume")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResumeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String pathDropbox;
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private CandidateEntity candidateEntity;
}
