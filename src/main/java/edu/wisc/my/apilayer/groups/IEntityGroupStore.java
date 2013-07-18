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
 * Interface for finding and maintaining <code>IEntityGroups</code>.
 * 
 * @author Eric Dalquist <a href="mailto:edalquist@unicon.net">edalquist@unicon.net</a>
 * @version $Revision: 1.1.2.1 $
 * @since 1.2
 */
public interface IEntityGroupStore {

    /**
     * Answers if <code>group</code> contains <code>member</code>.
     * @return boolean
     * @param group org.jasig.portal.groups.IEntityGroup
     * @param member org.jasig.portal.groups.IGroupMember
     */
    public boolean contains(IEntityGroup group, IGroupMember member) throws GroupsException;

    /**
     * Delete this <code>IEntityGroup</code> from the data store.
     * @param group org.jasig.portal.groups.IEntityGroup
     */
    public void delete(IEntityGroup group) throws GroupsException;

    /**
     * Returns an instance of the <code>IEntityGroup</code> from the data store.
     * @return org.jasig.portal.groups.IEntityGroup
     * @param key java.lang.String
     */
    public IEntityGroup find(String key) throws GroupsException;

    /**
     * Returns an <code>Iterator</code> over the <code>Collection</code> of
     * <code>IEntityGroups</code> that the <code>IGroupMember</code> belongs to.
     * @return java.util.Iterator
     * @param gm org.jasig.portal.groups.IEntityGroup
     */
    public Iterator<IEntityGroup> findContainingGroups(IGroupMember gm) throws GroupsException;

    /**
     * Returns an <code>Iterator</code> over the <code>Collection</code> of
     * <code>IEntities</code> that are members of this <code>IEntityGroup</code>.
     * @return java.util.Iterator
     * @param group org.jasig.portal.groups.IEntityGroup
     */
    public Iterator<IEntity> findEntitiesForGroup(IEntityGroup group) throws GroupsException;

    /**
     * Returns an instance of the <code>ILockableEntityGroup</code> from the data store.
     * @return org.jasig.portal.groups.IEntityGroup
     * @param key java.lang.String
     */
    public ILockableEntityGroup findLockable(String key) throws GroupsException;

    /**
     * Returns a <code>String[]</code> containing the keys of  <code>IEntityGroups</code>
     * that are members of this <code>IEntityGroup</code>.  In a composite group
     * system, a group may contain a member group from a different service.  This is
     * called a foreign membership, and is only possible in an internally-managed
     * service.  A group store in such a service can return the key of a foreign member
     * group, but not the group itself, which can only be returned by its local store.
     *
     * @return String[]
     * @param group org.jasig.portal.groups.IEntityGroup
     */
    public String[] findMemberGroupKeys(IEntityGroup group) throws GroupsException;

    /**
     * Returns an <code>Iterator</code> over the <code>Collection</code> of
     * <code>IEntityGroups</code> that are members of this <code>IEntityGroup</code>.
     * @return java.util.Iterator
     * @param group org.jasig.portal.groups.IEntityGroup
     */
    public Iterator<IEntityGroup> findMemberGroups(IEntityGroup group) throws GroupsException;

    /**
     * @return org.jasig.portal.groups.IEntityGroup
     */
    public IEntityGroup newInstance(Class<? extends IBasicEntity> entityType) throws GroupsException;

    /**
     * Find EntityIdentifiers for groups whose name matches the query string
     * according to the specified method and matches the provided leaf type
     */
    public IEntityIdentifier[] searchForGroups(String query, SearchMethod method, Class<? extends IBasicEntity> leaftype) throws GroupsException;

    /**
     * Adds or updates the <code>IEntityGroup</code> AND ITS MEMBERSHIPS to the
     * data store, as appropriate.
     * @param group org.jasig.portal.groups.IEntityGroup
     */
    public void update(IEntityGroup group) throws GroupsException;

    /**
     * Commits the group memberships of the <code>IEntityGroup</code> to
     * the data store.
     * @param group org.jasig.portal.groups.IEntityGroup
     */
    public void updateMembers(IEntityGroup group) throws GroupsException;
}
