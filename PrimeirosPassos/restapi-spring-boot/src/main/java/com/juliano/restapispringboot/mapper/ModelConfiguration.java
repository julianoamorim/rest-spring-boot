package com.juliano.restapispringboot.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//Converte Entidades -> DTO
@Configuration
public class ModelConfiguration {
    @Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
