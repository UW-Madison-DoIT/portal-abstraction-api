/**
 * Copyright 2012, Board of Regents of the University of
 * Wisconsin System. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Board of Regents of the University of Wisconsin
 * System licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
/*******************************************************************************
* Copyright 2004, The Board of Regents of the University of Wisconsin System.
* All rights reserved.
*
* A non-exclusive worldwide royalty-free license is granted for this Software.
* Permission to use, copy, modify, and distribute this Software and its
* documentation, with or without modification, for any purpose is granted
* provided that such redistribution and use in source and binary forms, with or
* without modification meets the following conditions:
*
* 1. Redistributions of source code must retain the above copyright notice,
* this list of conditions and the following disclaimer.
*
* 2. Redistributions in binary form must reproduce the above copyright notice,
* this list of conditions and the following disclaimer in the documentation
* and/or other materials provided with the distribution.
*
* 3. Redistributions of any form whatsoever must retain the following
* acknowledgement:
*
* "This product includes software developed by The Board of Regents of
* the University of Wisconsin System.�
*
*THIS SOFTWARE IS PROVIDED BY THE BOARD OF REGENTS OF THE UNIVERSITY OF
*WISCONSIN SYSTEM "AS IS" AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING,
*BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
*PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE BOARD OF REGENTS OF
*THE UNIVERSITY OF WISCONSIN SYSTEM BE LIABLE FOR ANY DIRECT, INDIRECT,
*INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
*LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
*PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
*LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
*OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
*ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*******************************************************************************/
package edu.wisc.my.apilayer.ldap;

import edu.wisc.my.apilayer.internal.ILdapServices;
import edu.wisc.my.apilayer.internal.IPortalServices;
import edu.wisc.my.apilayer.internal.PortalServicesLocator;


/**
 * Provides access to the LDAP servers that have been configured in the portal.
 * 
 * @author Eric Dalquist <a href="mailto:edalquist@unicon.net">edalquist@unicon.net</a>
 * @version $Revision: 1.1.2.1 $
 * @since 1.0
 */
public final class LdapServices {
    /** Hide the constructor so this class cannot be instanciated */
    private LdapServices() { }
    
    /**
     * Gets the {@link ILdapServer} instance that represents the portal's
     * default ldap server. May be <code>null</code> if the portal doesn't
     * have a default LDAP server configured.
     * 
     * @return The {@link ILdapServer} instance representing the default LDAP server, <code>null</code> if one doesn't exist.
     */
    public static ILdapServer getDefaultServer() {
        final IPortalServices ps = PortalServicesLocator.getPortalServices();
        final ILdapServices ls = ps.getLdapServices();
        
        return ls.getDefaultServer();
    }
    
    /**
     * Gets the {@link ILdapServer} instance that represent's the ldap server
     * with the specified name configured in the portal. May be
     * <code>null</code> if there is no server with the specified name
     * configured.
     * 
     * @param name The name of the LDAP server to get a reference to.
     * @return The {@link ILdapServer} instance representing the named LDAP server, <code>null</code> if it doesn't exist.
     */
    public static ILdapServer getServer(final String name) {
        final IPortalServices ps = PortalServicesLocator.getPortalServices();
        final ILdapServices ls = ps.getLdapServices();
        
        return ls.getServer(name);
    }
    
    /**
     * Gets a list of named LDAP servers that are configured for the portal. If
     * the default server has a name it will be listed here but won't be if it
     * doesn't have a name.
     * 
     * @return A list of named LDAP servers, if no servers exist a 0 length array will be returned.
     */
    public static String[] getServerNames() {
        final IPortalServices ps = PortalServicesLocator.getPortalServices();
        final ILdapServices ls = ps.getLdapServices();
        
        return ls.getServerNames();
    }
}
