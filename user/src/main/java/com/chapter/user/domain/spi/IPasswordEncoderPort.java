package com.chapter.user.domain.spi;

public interface IPasswordEncoderPort {

    String encryptPassword(String password);
}
