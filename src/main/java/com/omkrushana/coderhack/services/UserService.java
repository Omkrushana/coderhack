package com.omkrushana.coderhack.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.omkrushana.coderhack.exception.InvalidScoreException;
import com.omkrushana.coderhack.exception.UserNotFoundException;
import com.omkrushana.coderhack.model.Badges;
import com.omkrushana.coderhack.model.User;
import com.omkrushana.coderhack.paylods.UserDto;
import com.omkrushana.coderhack.repository.IUserRepository;

@Service
public class UserService {
	@Autowired
	private IUserRepository userRepository;

	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepository.save(user);
		return this.userToDto(savedUser);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll(Sort.by(Sort.Direction.ASC, "score"));
	}

	public Optional<User> getUserById(String id) {
		return Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException()));
	}

	public UserDto updateUser(String userId, int score) {
		User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
		if (score < 0 || score > 100) {
			throw new InvalidScoreException("score should be between 0 - 100");
		} else if (score == 0) {
			user.setScore(score);
		} else if (score > 0 && score <= 30) {
			user.setScore(score);
			user.setBadges(Badges.Code_Ninja);
		} else if (score > 30 && score < 60) {
			user.setScore(score);
			user.setBadges(Badges.Code_Champ);
		} else {
			user.setScore(score);
			user.setBadges(Badges.Code_Master);
		}
		User updatedUser = this.userRepository.save(user);
		UserDto userDto1 = this.userToDto(updatedUser);
		return userDto1;
	}

	public void deleteUser(String userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
		userRepository.delete(user);
	}

	private User dtoToUser(UserDto userDto) {
		User user = new User();
		user.setUserId(userDto.getUserId());
		user.setUsername(userDto.getUsername());
		user.setScore(userDto.getScore());
		user.setBadges(userDto.getBadges());
		return user;
	}

	public UserDto userToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setUsername(user.getUsername());
		userDto.setScore(user.getScore());
		userDto.setBadges(user.getBadges());
		return userDto;
	}

}
