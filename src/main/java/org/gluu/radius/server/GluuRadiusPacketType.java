package org.gluu.radius.server;


public enum GluuRadiusPacketType {
	ACCESS_ACCEPT,
	ACCESS_CHALLENGE,
	ACCESS_REJECT,
	ACCESS_REQUEST,
	ACCOUNTING_MESSAGE,
	ACCOUNTING_RESPONSE,
	ACCOUNTING_STATUS,
	COA_ACK,
	COA_NAK,
	COA_REQUEST,
	DISCONNECT_ACK,
	DISCONNECT_NAK,
	DISCONNECT_REQUEST,
	MAX_PACKET_LENGTH,
	PASSWORD_ACCEPT,
	PASSWORD_REJECT,
	PASSWORD_REQUEST,
	RADIUS_HEADER_LENGTH,
	RESERVED,
	STATUS_ACCEPT,
	STATUS_CLIENT,
	STATUS_REJECT,
	STATUS_REQUEST,
	STATUS_SERVER
}