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
package edu.wisc.my.apilayer.person;

import java.util.List;
import java.util.Map;

import edu.wisc.my.apilayer.internal.IPersonServices;
import edu.wisc.my.apilayer.internal.IPortalServices;
import edu.wisc.my.apilayer.internal.PortalServicesLocator;


/**
 * Provides search methods to retrieve {@link edu.wisc.my.apilayer.person.IPerson}
 * instance from the implementing portal.
 * 
 * @author Eric Dalquist <a href="mailto:edalquist@unicon.net">edalquist@unicon.net</a>
 * @version $Revision: 1.1.2.2 $
 * @since 1.1
 */
public class PersonServices {
    /** Hide the constructor so this class cannot be instanciated */
    private PersonServices() { }

    /**
     * Convience method to look up a portal user by their portal user
     * name. Will return a full {@link IPerson} if the appropriate user
     * is found, null otherwise.
     * 
     * @param userName The name of the user to find.
     * @return An {@link IPerson} object representing the user or null if they are not found.
     */
    public static IPerson getPersonByUserName(final String userName) {
        final IPortalServices prtlSrvcs = PortalServicesLocator.getPortalServices();
        final IPersonServices ps = prtlSrvcs.getPersonServices();
        
        return ps.getPersonByUserName(userName);
    }
    
    /**
     * Convience method to look up a portal user by their portal key.
     * Will return a full {@link IPerson} if the appropriate user
     * is found, null otherwise.
     * 
     * @param key The key of the user to find.
     * @return An {@link IPerson} object representing the user or null if they are not found.
     */
    public static IPerson getPersonByKey(final String key) {
        final IPortalServices prtlSrvcs = PortalServicesLocator.getPortalServices();
        final IPersonServices ps = prtlSrvcs.getPersonServices();
        
        return ps.getPersonByKey(key);
    }
    
    /**
     * Uses the {@link Map} of attributes and values to find a user. If
     * no matching user is found null is returned.
     * 
     * @param queryMap The {@link Map} of attributes to use for the query.
     * @return An {@link IPerson} object representing the user or null if they are not found.
     */
    public static IPerson getPerson(final Map<String, List<Object>> queryMap) {
        final IPortalServices prtlSrvcs = PortalServicesLocator.getPortalServices();
        final IPersonServices ps = prtlSrvcs.getPersonServices();
        
        return ps.getPerson(queryMap);
    }
    
    /**
     * A convience method version of {@link #getPerson(Map)} that will create
     * a {@link Map} with one entry and call {@link #getPerson(Map)}.
     * 
     * @param queryAttry The attribute to use for the query.
     * @param queryVal The value of the attribute to search for.
     * @return An {@link IPerson} object representing the user or null if they are not found.
     */
    public static IPerson getPerson(final String queryAttry, final String queryVal) {
        final IPortalServices prtlSrvcs = PortalServicesLocator.getPortalServices();
        final IPersonServices ps = prtlSrvcs.getPersonServices();
        
        return ps.getPerson(queryAttry, queryVal);
    }
}
