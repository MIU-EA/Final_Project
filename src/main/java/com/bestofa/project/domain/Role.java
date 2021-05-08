package com.bestofa.project.domain;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Role {
    private String name;
}
