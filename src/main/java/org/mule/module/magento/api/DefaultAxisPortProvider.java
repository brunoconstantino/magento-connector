/**
 * Mule Magento Cloud Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.magento.api;

import org.mule.module.magento.api.internal.Mage_Api_Model_Server_V2_HandlerPortType;
import org.mule.module.magento.api.internal.MagentoServiceLocator;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.Validate;

public class DefaultAxisPortProvider implements AxisPortProvider
{
    private final String username;
    private final String password;
    private final String address;

    /**
     * Creates the port provider
     * 
     * @param username
     * @param password
     * @param address
     */
    public DefaultAxisPortProvider(@NotNull String username, @NotNull String password, @NotNull String address)
    {
        Validate.notNull(username);
        Validate.notNull(password);
        Validate.notNull(address);
        this.username = username;
        this.password = password;
        this.address = address;
    }

    public Mage_Api_Model_Server_V2_HandlerPortType getPort() throws Exception
    {
        MagentoServiceLocator serviceLocator = new MagentoServiceLocator();
        serviceLocator.setMage_Api_Model_Server_V2_HandlerPortEndpointAddress(address);
        return serviceLocator.getMage_Api_Model_Server_V2_HandlerPort();
    }

    /**
     * Need to authenticate before every call
     * 
     * @return Magento session ID
     * @throws Exception
     */
    public String getSessionId() throws Exception
    {
        return getPort().login(username, password);
    }
}
