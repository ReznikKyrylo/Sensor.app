package com.sensor.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SensorDTO {
        @NotEmpty(message = "У сенсора повинна бути назва!")
        @Size(min = 3,max = 30,message = "Розмір назви сенсора повинна бути від 3 до 30 символів!")
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
