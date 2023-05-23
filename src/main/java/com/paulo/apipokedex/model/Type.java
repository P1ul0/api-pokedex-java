package com.paulo.apipokedex.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "types")
public class Type {
    @Id
    private Long id;

    @Column()
    private String name;
    @ManyToMany
    private List<Pokemon> pokemon;
}
