package com.company.rabbitmqconfig;

public enum MessagingConstants {
	EXCHANGE_NAME("appExchange"), FANOUT_EXCHANGE_NAME("appFanoutExchange"), 
	DIRECT_EXCHANGE_NAME("appDirectExchange"), QUEUE_GENERIC_NAME("appGenericQueue"), 
	FANOUT_QUEUE_GENERIC_NAME("appFanoutGenericQueue"), DIRECT_QUEUE_GENERIC_NAME("appDirectGenericQueue"), 
	QUEUE_SPECIFIC_NAME("appSpecificQueue"), ROUTING_KEY("messages.key");

	private String value;
	
	public String getValue() {
		return this.value;
	}
	private MessagingConstants(String value) {
		this.value = value;
	}
}
