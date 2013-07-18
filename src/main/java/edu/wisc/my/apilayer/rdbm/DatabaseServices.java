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
* the University of Wisconsin System.”
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
package edu.wisc.my.apilayer.rdbm;

import edu.wisc.my.apilayer.internal.IDatabaseServices;
import edu.wisc.my.apilayer.internal.IPortalServices;
import edu.wisc.my.apilayer.internal.PortalServicesLocator;


/**
 * Provides access to the database servers that have been configured in the portal.
 * 
 * @author Eric Dalquist <a href="mailto:edalquist@unicon.net">edalquist@unicon.net</a>
 * @version $Revision: 1.1.2.1 $
 * @since 1.0
 */
public final class DatabaseServices {
    /** Hide the constructor so this class cannot be instanciated */
    private DatabaseServices() { }
    
    /**
     * Gets the {@link IDatabaseServer} instance that represents the portal's
     * default database server. May be <code>null</code> if the portal doesn't
     * have a default database server configured.
     * 
     * @return The {@link IDatabaseServer} instance representing the default database server, <code>null</code> if one doesn't exist.
     */
    public static IDatabaseServer getDefaultServer() {
        final IPortalServices ps = PortalServicesLocator.getPortalServices();
        final IDatabaseServices ds = ps.getDatabaseServices();
        
        return ds.getDefaultServer();
    }
    
    /**
     * Gets the {@link IDatabaseServer} instance that represent's the database
     * server with the specified name configured in the portal. May be
     * <code>null</code> if there is no server with the specified name
     * configured.
     * 
     * @param name The name of the database server to get a reference to.
     * @return The {@link IDatabaseServer} instance representing the named database server, <code>null</code> if it doesn't exist.
     */
    public static IDatabaseServer getServer(final String name) {
        final IPortalServices ps = PortalServicesLocator.getPortalServices();
        final IDatabaseServices ds = ps.getDatabaseServices();
        
        return ds.getServer(name);
    }
    
    /**
     * Gets a list of named database servers that are configured for the portal.
     * If the default server has a name it will be listed here but won't be if
     * it doesn't have a name.
     * 
     * @return A list of named database servers, if no servers exist a 0 length array will be returned.
     */
    public static String[] getServerNames() {
        final IPortalServices ps = PortalServicesLocator.getPortalServices();
        final IDatabaseServices ds = ps.getDatabaseServices();
        
        return ds.getServerNames();
    }

}
