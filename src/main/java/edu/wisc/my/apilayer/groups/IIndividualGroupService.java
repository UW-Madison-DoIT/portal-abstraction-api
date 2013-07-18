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
package edu.wisc.my.apilayer.groups;

import java.util.Iterator;


/**
 * Defines a component group service that finds and maintains
 * <code>IGroupMembers</code> within a composite group service.
 *
 * @author Eric Dalquist <a href="mailto:edalquist@unicon.net">edalquist@unicon.net</a>
 * @version $Revision: 1.1.2.1 $
 * @since 1.2
 */
public interface IIndividualGroupService extends ICompositeGroupService {

    /**
     * Answers if <code>group</code> contains <code>member</code>.
     * @return boolean
     * @param group org.jasig.portal.groups.IEntityGroup
     * @param member org.jasig.portal.groups.IGroupMember
     */
    public boolean contains(IEntityGroup group, IGroupMember member) throws GroupsException;

    /**
     * Removes the <code>IEntityGroup</code> from the store.
     */
    public void deleteGroup(IEntityGroup group) throws GroupsException;

    /**
     * Returns a preexisting <code>IEntityGroup</code> from the store.
     * @param ent CompositeEntityIdentifier
     */
    public IEntityGroup findGroup(ICompositeEntityIdentifier ent) throws GroupsException;

    /**
     * Returns an <code>Iterator</code> over the members of <code>group</code>.
     * @param group IEntityGroup
     */
    public Iterator<IGroupMember> findMembers(IEntityGroup group) throws GroupsException;

    /**
     * Answers if the group can be updated or deleted in the store.
     * @param group IEntityGroup
     */
    public boolean isEditable(IEntityGroup group) throws GroupsException;

    /**
     * Answers if the service can be updated by the portal.
     */
    public boolean isEditable();

    /**
     * Returns a new <code>IEntityGroup</code> for the given Class<? extends IBasicEntity> with an unused
     * key.
     */
    public IEntityGroup newGroup(Class<? extends IBasicEntity> type) throws GroupsException;

    /**
     * Commits the updated <code>IEntityGroup</code> and its memberships to the
     * store.
     * @param group IEntityGroup
     */
    public void updateGroup(IEntityGroup group) throws GroupsException;

    /**
     * Commits the updated group memberships to the store.
     * @param group IEntityGroup
     */
    public void updateGroupMembers(IEntityGroup group) throws GroupsException;
}
