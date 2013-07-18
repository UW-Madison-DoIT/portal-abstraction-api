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

package edu.wisc.my.apilayer.internal;



/**
 * The <code>PortalServicesLocator</code> is used as a bridge point between a
 * portal's implemenation of the APIs and a client application's use of the APIs.
 * 
 * A portal that implements the API Abstraction Layer must use the
 * {@link #setPortalServices(IPortalServices)} method to pass a reference to
 * the portal's implemenation of the {@link edu.wisc.my.apilayer.internal.IPortalServices}
 * interface before any client application calls any method on the API.
 * 
 * @author Eric Dalquist <a href="mailto:edalquist@unicon.net">edalquist@unicon.net</a>
 * @version $Revision: 1.1.2.3 $
 * @since 1.0
 */
public final class PortalServicesLocator {
    /** Refrence holder to an {@link IPortalServices} implementation */
    private static IPortalServices servicesRef = null;
    
    /**
     * Setter method to get passed a refrences to an {@link IPortalServices}
     * implementation. 
     * 
     * @param portalServices A refrences to the services interface implementation.
     */
    public static void setPortalServices(IPortalServices portalServices) {
        servicesRef = portalServices;
    }
    
    /**
     * Returns a refrence to the currently set {@link IPortalServices}
     * implementation. If the reference is <code>null</code> an
     * {@link IllegalStateException} will be thrown.
     * 
     * @return A refrence to the current services interface implementation, may be <code>null</code>.
     * @throws IllegalStateException If no refernce to an {@link IPortalServices} implementation has been set via {@link #setPortalServices(IPortalServices)}.
     */
    public static IPortalServices getPortalServices() {
        if (servicesRef == null) {
            throw new IllegalStateException("A IPortalServices refrence must be set by calling setPortalServices(IPortalServices) with a valid reference before getPortalServices() is called.");
        }
        
        return servicesRef;
    }
}
