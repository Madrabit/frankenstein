package ru.madrabit.frankenstein.database.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NamedEntityGraph(name = "User.company",
attributeNodes = @NamedAttributeNode("company")
)
@Data
@ToString(exclude = "userChats")
@EqualsAndHashCode(of = "username")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String firstname;

    @Column(unique = true, nullable = false)
    private String lastname;

    @Column(unique = true, nullable = false)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Roles role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UserChat> userChats = new ArrayList<>();
}