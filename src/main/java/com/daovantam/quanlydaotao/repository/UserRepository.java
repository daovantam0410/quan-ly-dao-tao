package com.daovantam.quanlydaotao.repository;

import com.daovantam.quanlydaotao.entity.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long>{

    Optional<User> findByEmail(String email);

    Optional<User> findByUserName(String userName);
}
