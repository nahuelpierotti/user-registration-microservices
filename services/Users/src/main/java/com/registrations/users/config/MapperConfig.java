package com.registrations.users.config;

import com.registrations.users.dto.UserLoggedResponseDto;
import com.registrations.users.model.User;
import org.hibernate.validator.internal.constraintvalidators.bv.time.pastorpresent.PastOrPresentValidatorForCalendar;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class MapperConfig {

    private final ModelMapper modelMapper=new ModelMapper();

    @Bean(name="defaultMapper")
    public ModelMapper modelMapper() {
        return modelMapper;
    }

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .toList();
    }
}
