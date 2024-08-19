package com.CN.Gym.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutDto {

   
	private String workoutName;
    private String description;
    private String difficultyLevel;
    private int duration;

}
