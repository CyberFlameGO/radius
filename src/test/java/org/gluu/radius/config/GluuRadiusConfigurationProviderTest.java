package org.gluu.radius.config;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class GluuRadiusConfigurationProviderTest {
	
	private static final String MISSING_CONFIG_FILENAME = "missing-config.properties";
	private static final String INVALID_CONFIG_FILENAME = "invalid-config.properties";
	private static final String VALID_CONFIG_FILENAME = "valid-config.properties";

	@Test
	public void nullConfigurationFile() {

		try {
			ConfigurationProvider provider = new GluuRadiusConfigurationProvider(null);
			fail("expected GluuRadiusConfigException not thrown");
		}catch(GluuRadiusConfigException e) {
			assertThat(e.getMessage(),is("Missing configuration filename"));
		}
	}

	@Test
	public void missingConfigurationFile() {

		try {
			ConfigurationProvider provider = new GluuRadiusConfigurationProvider(MISSING_CONFIG_FILENAME);
			fail("expected GluuRadiusConfigException not thrown");
		}catch(GluuRadiusConfigException e) {
			assertTrue(e.getCause() instanceof FileNotFoundException);
		}
	}


	@Test
	public void invalidConfigurationFile() {

		try {
			ConfigurationProvider provider = new GluuRadiusConfigurationProvider(INVALID_CONFIG_FILENAME);
		}catch(GluuRadiusConfigException e) {
			assertEquals("Missing salt file name",e.getMessage());
		}
	}


	@Test
	public void validConfigurationFile() {

		ConfigurationProvider provider = new GluuRadiusConfigurationProvider(VALID_CONFIG_FILENAME);
		LdapConfiguration ldapconfig  = provider.getLdapConfiguration();

		Integer port = 1636;
		Integer boundcpsize = 1;
		Integer unboundcpsize = 10;
		assertEquals("localhost",ldapconfig.getHostname());
		assertEquals(port,ldapconfig.getPort());
		assertEquals("cn=directory manager",ldapconfig.getBindDn());
		assertEquals("gluu",ldapconfig.getPassword());
		assertEquals(true,ldapconfig.getSslEnabled());

		assertEquals("opendj.pkcs12",ldapconfig.getTrustStoreFile());
		assertEquals("Agtyc0lLNwuC",ldapconfig.getTrustStorePin());
		assertEquals("pkcs12",ldapconfig.getTrustStoreFormat());
		assertEquals(true,ldapconfig.getSslVerifyEnabled());
		assertEquals(boundcpsize,ldapconfig.getConnPoolConfig().getBoundCpSize());
		assertEquals(unboundcpsize,ldapconfig.getConnPoolConfig().getUnboundCpSize());

	}
}