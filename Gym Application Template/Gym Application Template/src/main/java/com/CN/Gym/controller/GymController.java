package com.CN.Gym.controller;

import com.CN.Gym.dto.GymDto;
import com.CN.Gym.model.Gym;
import com.CN.Gym.service.GymService;
import com.CN.Gym.service.UserService;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gym")
public class GymController {

	@Autowired
	public GymService gymService;
	
	@Autowired
	public UserService userService;
	
//	@Autowired
//	public GymController(GymService gymService) {
//		this.gymService=gymService;
//	}

   
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
    public List<Gym> getAllGyms() {
        return gymService.getAllGyms();
    }


   
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	public Gym getGymById(@PathVariable Long id){
        return gymService.getGymById(id);
    }


   
	@PostMapping("/create")
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public void createGym(@RequestBody GymDto gymDto) {
		gymService.createGym(gymDto);
    }


                                                                
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	public void updateGym(@RequestBody GymDto gymDto, @PathVariable Long id){
    	gymService.updateGym(gymDto,id);
    }


  
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
  	@ResponseStatus(HttpStatus.OK)
    public void deleteGym(@PathVariable Long id){
    		gymService.deleteGymById(id);
    }


  
    @PostMapping("/addMember")
    @PreAuthorize("hasRole('ADMIN')")
  	@ResponseStatus(HttpStatus.CREATED)
    public void addMember(@RequestParam Long userId, @RequestParam Long gymId) {
    	gymService.addMember(userId,gymId);
    }


   
    @DeleteMapping("/deleteMember")
    @PreAuthorize("hasRole('ADMIN')")
  	@ResponseStatus(HttpStatus.OK)
    public void deleteMember(@PathParam("userId") Long userId, @PathParam("gymId") Long gymId) {
    	gymService.deleteMember(userId,gymId);
    }

}
