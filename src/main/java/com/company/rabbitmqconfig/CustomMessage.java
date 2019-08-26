package com.company.rabbitmqconfig;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public final class CustomMessage implements Serializable {
	private static final long serialVersionUID = 3173878798114199524L;
	private String text;
    private int priority;
    private boolean secret;
}
