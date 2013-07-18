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

import javax.naming.Name;


/**
 * Defines an api for discovering entry points into a composite groups system
 * consisting of component group services.  These entry points are represented
 * by <code>IGroupMembers</code>.  The role of the <code>IGroupMember</code>is 
 * somewhat analogous to that of an <code>InitialContext</code> in JNDI.  Once
 * a client gets an <code>IGroupMember</code>, subsequent requests for navigating
 * the system or maintaining groups go thru the <code>IGroupMember</code> api 
 * and are serviced by the individual component services.
 *
 * @author Eric Dalquist <a href="mailto:edalquist@unicon.net">edalquist@unicon.net</a>
 * @version $Revision: 1.1.2.1 $
 * @since 1.2
 */
public interface ICompositeGroupService extends IComponentGroupService {

    /**
     * Returns the groups that contain the <code>IGroupMember</code>.
     * @param gm IGroupMember
     */
    public Iterator<IEntityGroup> findContainingGroups(IGroupMember gm) throws GroupsException;

    /**
     * Returns a pre-existing <code>IEntityGroup</code> or null if it does not
     * exist.
     */
    public IEntityGroup findGroup(String key) throws GroupsException;

    /**
     * Returns a pre-existing <code>IEntityGroup</code> or null if it does not
     * exist.
     */
    public ILockableEntityGroup findGroupWithLock(String key, String owner)  throws GroupsException;

    /**
     * Returns an <code>IEntity</code> representing a portal entity.  This does
     * not guarantee that the entity actually exists.
     */
    public IEntity getEntity(String key, Class<? extends IBasicEntity> type) throws GroupsException;

    /**
     * Returns an <code>IEntity</code> representing a portal entity.  This does
     * not guarantee that the entity actually exists.
     */
    public IEntity getEntity(String key, Class<? extends IBasicEntity> type, String service) throws GroupsException;

    /**
     * Returns an <code>IGroupMember</code> representing either a group or a
     * portal entity.  If the parm <code>type</code> is the group type,
     * the <code>IGroupMember</code> is an <code>IEntityGroup</code>.  Otherwise
     * it is an <code>IEntity</code>.
     */
    public IGroupMember getGroupMember(String key, Class<? extends IBasicEntity> type) throws GroupsException;

    /**
     * Returns an <code>IGroupMember</code> representing either a group or a
     * portal entity, based on the <code>IEntityIdentifier</code>, which refers
     * to the UNDERLYING entity for the <code>IGroupMember</code>.
     */
    public IGroupMember getGroupMember(IEntityIdentifier underlyingIEntityIdentifier) throws GroupsException;

    /**
     * Returns a new <code>IEntityGroup</code> for the given Class<? extends IBasicEntity> with an unused
     * key from the named service.
     */
    public IEntityGroup newGroup(Class<? extends IBasicEntity> type, Name serviceName) throws GroupsException;

    /**
     * Find IEntityIdentifiers for entities whose name matches the query string 
     * according to the specified method and is of the specified type 
     */
    public IEntityIdentifier[] searchForEntities(String query, SearchMethod method, Class<? extends IBasicEntity> type) throws GroupsException;

    /**
     * Find IEntityIdentifiers for entities whose name matches the query string 
     * according to the specified method, is of the specified type  and
     * descends from the specified group
     */
    public IEntityIdentifier[] searchForEntities(String query, SearchMethod method, Class<? extends IBasicEntity> type, IEntityGroup ancestor) throws GroupsException;

    /**
     * Find IEntityIdentifiers for groups whose name matches the query string 
     * according to the specified method and matches the provided leaf type 
     */
    public IEntityIdentifier[] searchForGroups(String query, SearchMethod method, Class<? extends IBasicEntity> leaftype) throws GroupsException;

    /**
     * Find IEntityIdentifiers for groups whose name matches the query string 
     * according to the specified method, has the provided leaf type  and
     * descends from the specified group
     */
    public IEntityIdentifier[] searchForGroups(String query, SearchMethod method, Class<? extends IBasicEntity> leaftype, IEntityGroup ancestor) throws GroupsException;
}
