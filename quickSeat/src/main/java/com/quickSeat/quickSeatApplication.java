package com.quickSeat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.quickSeat")
public class quickSeatApplication {

	public static void main(String[] args) {
		SpringApplication.run(quickSeatApplication.class, args);
	}

}
