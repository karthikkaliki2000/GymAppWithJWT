package com.CN.Gym.service;


import com.CN.Gym.dto.GymDto;
import com.CN.Gym.exception.GymNotFoundException;
import com.CN.Gym.exception.UserNotFoundException;
import com.CN.Gym.model.Gym;
import com.CN.Gym.model.User;
import com.CN.Gym.repository.GymRepository;
import com.CN.Gym.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GymService {


	@Autowired
	public GymRepository gymRepository;

	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
    public List<Gym> getAllGyms() {
    	return gymRepository.findAll();
    }


  

	public Gym getGymById(Long id) {
		
		return gymRepository.findById(id).orElseThrow(()->new GymNotFoundException("Not Found"));
	}


	public void createGym(GymDto gymDto) {
		Gym gym=new Gym();
		gym.setAddress(gymDto.getAddress());
		gym.setContactNo(gymDto.getContactNo());
		gym.setFacilities(gymDto.getFacilities());
		gym.setMembershipPlans(gymDto.getMembershipPlans());
		gym.setName(gymDto.getName());
		gym.setMembers(gymDto.getMembers());
		gymRepository.save(gym);
	}
	



	public void updateGym(GymDto gymDto, Long id) {
		
		Gym gym=this.getGymById(id);
		gym.setAddress(gymDto.getAddress());
		gym.setContactNo(gymDto.getContactNo());
		gym.setFacilities(gymDto.getFacilities());
		gym.setMembershipPlans(gymDto.getMembershipPlans());
		gym.setName(gymDto.getName());
		gym.setMembers(gymDto.getMembers());
		gymRepository.save(gym);
	}



	public void deleteGymById(Long id) {
		gymRepository.deleteById(id);
	}







	public void addMember(Long userId, Long gymId) {
		Gym gym=this.getGymById(gymId);
		User user=userService.getUserById(userId);
		user.setGym(gym);
		List<User> members= gym.getMembers();
        members.add(user);
        gym.setMembers(members);
		gymRepository.save(gym);
		userRepository.save(user);
		
	}


	public void deleteMember(Long userId, Long gymId) {
		// TODO Auto-generated method stub
		Gym gym=this.getGymById(gymId);
		User user=userService.getUserById(userId);
		if(gym.getMembers().contains(user)) {
			user.setGym(null);
			userRepository.deleteById(userId);
			gym.getMembers().remove(user);
			gymRepository.save(gym);
		}
		
		
		
	}
}
