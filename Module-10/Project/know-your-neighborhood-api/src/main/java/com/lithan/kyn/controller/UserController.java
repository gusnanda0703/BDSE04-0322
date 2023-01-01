package com.lithan.kyn.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lithan.kyn.entity.UserAccount;
import com.lithan.kyn.exception.ResourceNotFoundException;
import com.lithan.kyn.model.EditProfileDto;
import com.lithan.kyn.model.UserDto;
import com.lithan.kyn.model.UserFullData;
import com.lithan.kyn.model.UserFullData.Profile;
import com.lithan.kyn.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("")
  public List<UserDto> listUser() {
    List<UserDto> listUser = userService.listUser();

    return listUser;
  }

  @GetMapping("/me")
  public UserFullData getLoginUser(Authentication authentication) {
    if (userService.findByEmail(authentication.getName()) == null) {
      throw new ResourceNotFoundException("User", "Email", authentication.getName());
    }

    UserAccount user = userService.findByEmail(authentication.getName());

    Profile profile = new Profile(
        user.getUserId(),
        user.getName(),
        user.getEmail(),
        user.getImageUrl(),
        user.getAddress(),
        user.getPhoneNumber());

    List<String> roles = user.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toList());

    return new UserFullData(profile, user.getStores(), roles);
  }

  @GetMapping("/{userId}")
  public UserFullData getUser(@PathVariable("userId") int userId) {
    if (userService.getById(userId) == null) {
      throw new ResourceNotFoundException("User", "userId", userId);
    }

    UserAccount user = userService.getById(userId);

    Profile profile = new Profile(
        user.getUserId(),
        user.getName(),
        user.getEmail(),
        user.getImageUrl(),
        user.getAddress(),
        user.getPhoneNumber());

    List<String> roles = user.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toList());

    return new UserFullData(profile, user.getStores(), roles);
  }

  @PutMapping("/edit")
  public UserDto editProfile(@RequestBody EditProfileDto editProfileDto) {
    UserAccount user = userService.editProfile(editProfileDto);

    return new UserDto(
        user.getUserId(),
        user.getName(),
        user.getEmail(),
        user.getImageUrl(),
        user.getAddress(),
        user.getPhoneNumber());
  }
}
