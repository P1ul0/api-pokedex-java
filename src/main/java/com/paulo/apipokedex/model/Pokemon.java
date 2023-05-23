package com.paulo.apipokedex.model;

import com.paulo.apipokedex.model.subModel.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "pokemons")
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column()
    private String name;

    @Column()
    private String img;

    @ManyToMany
    @JoinTable(name = "pokemon_type",
            joinColumns = @JoinColumn (name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    private List<Type> types;

    @Embedded
    private Status status;

    @ManyToMany
    @JoinTable(name = "pokemon_move",
            joinColumns = @JoinColumn (name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "move_id")
    )
    private List<Move> move;
}
