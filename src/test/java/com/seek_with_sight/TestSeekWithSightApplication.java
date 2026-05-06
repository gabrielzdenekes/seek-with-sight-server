package com.seek_with_sight;

import org.springframework.boot.SpringApplication;

public class TestSeekWithSightApplication {

	static void main(String[] args) {
		SpringApplication.from(SeekWithSightApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
