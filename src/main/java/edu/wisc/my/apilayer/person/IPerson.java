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

import java.util.Enumeration;

import edu.wisc.my.apilayer.groups.IBasicEntity;
import edu.wisc.my.apilayer.groups.IEntityIdentifier;


/**
 * An IPerson represents a portal user. The user's attributes are avalable
 * with two special getters for the user's unique key from the portal
 * and their portal user name.
 *
 * @author Eric Dalquist <a href="mailto:edalquist@unicon.net">edalquist@unicon.net</a>
 * @version $Revision: 1.1.2.1 $
 * @since 1.1
 */
public interface IPerson extends IBasicEntity {
    /**
     * Represents a unique identifier that client code can use to key
     * stored data for this particular user off of. There are no restrictions
     * on what the String may actually be. This key may not exist for all
     * users. Some implementations of this service allow non-portal user
     * look-up. In that case this key may be null. This key may also be
     * duplicated in the enumeration of attributes.
     *
     * @return A unique identifier for this person.
     */
    public String getKey();

    /**
     * The name the user uses to log into the portal. This name may not
     * exist for all users. Some implementations of this service allow
     * non-portal user look-up. In that case this name may be null. This
     * name may also be duplicated in the enumeration of attributes.
     *
     * @return The name the user uses to log into the portal.
     */
    public String getUserName();

    /**
     * Gets the values for the specified attribute. Returns null if the
     * attribute doesn't exist.
     * <br>
     * Implementers should be carefull about the mutibility of the values
     * they are returning.
     *
     * @param attributeName The name of the attribute to lookup.
     * @return The values for the attribute, null if the attribute doesn't exist.
     */
    public Object[] getAttributeValues(final String attributeName);

    /**
     * Gets the first value for the specified attribute. Returns null if the
     * attribute doesn't exist.
     * <br>
     * Implementers should be carefull about the mutibility of the value
     * they are returning.
     *
     * @param attributeName The name of the attribute to lookup.
     * @return The first value for the attribute, null if the attribute doesn't exist.
     */
    public Object getAttributeValue(final String attributeName);

    /**
     * Returns an Enumeration of attribute names.
     *
     * @return An Enumeration of attribute names.
     */
    public Enumeration<String> getAttributeNames();
    
    /**
     * Gets the unique entity identifier for the {@link IPerson}.
     * 
     * @return The unique identifier for this person.
     * @since 1.2
     */
    public IEntityIdentifier getIdentifier();
}
