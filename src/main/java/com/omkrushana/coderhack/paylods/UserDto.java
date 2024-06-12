package com.omkrushana.coderhack.paylods;

import java.util.Set;

import com.omkrushana.coderhack.model.Badges;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private String userId;
    private String username;
    private int score;
    private Set<Badges> badges;
}
