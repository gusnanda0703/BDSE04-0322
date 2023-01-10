package com.lithan.kyn.model;

import java.util.List;

import com.lithan.kyn.entity.Store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserFullData {

  private UserDto profile;

  private List<Store> stores;

  private List<String> roles;

}
