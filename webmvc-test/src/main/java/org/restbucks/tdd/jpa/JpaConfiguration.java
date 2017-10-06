package org.restbucks.tdd.jpa;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@ComponentScan("org.restbucks.tdd.jpa")
@Configuration
public class JpaConfiguration {

}
