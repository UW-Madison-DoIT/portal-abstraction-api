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
package edu.wisc.my.apilayer.groups;

import javax.naming.InvalidNameException;
import javax.naming.Name;


/**
 * Extends a basic entity identifier to provide identity information about
 * entities managed in the composite group service.
 * 
 * @author Eric Dalquist <a href="mailto:edalquist@unicon.net">edalquist@unicon.net</a>
 * @version $Revision: 1.1.2.1 $
 * @since 1.2
 */
public interface ICompositeEntityIdentifier extends IEntityIdentifier {
    /**
     * Gets the entity key that is local to it's containing service.
     * 
     * @return java.lang.String
     */
    public String getLocalKey();
    
    /**
     * If the composite key is either empty or has a single node, there is
     * no service name.
     * 
     * @return javax.naming.Name
     */
    public Name getServiceName();
    
    /**
     * Removes a node string from the Name for the entity identifier
     * 
     * @return String - the removed component
     */
    public String popNode() throws InvalidNameException;
    
    /**
     * Adds the node string to the Name for the entity identifier
     * 
     * @return javax.naming.Name
     */
    public Name pushNode(String newNode) throws InvalidNameException;
    
    /**
     * Specifies a new composite key for the composite entity identifier
     * 
     * @param newCompositeKey javax.naming.Name
     */
    public void setCompositeKey(Name newCompositeKey);
    
    /**
     * Specifies a new service name for the composite entity identifier
     * 
     * @param newServiceName javax.naming.Name
     */
    public void setServiceName(Name newServiceName) throws InvalidNameException;
}
