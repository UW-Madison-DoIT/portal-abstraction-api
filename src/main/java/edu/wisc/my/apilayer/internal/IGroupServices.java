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

import javax.naming.InvalidNameException;

import edu.wisc.my.apilayer.groups.GroupsException;
import edu.wisc.my.apilayer.groups.IBasicEntity;
import edu.wisc.my.apilayer.groups.ICompositeGroupService;
import edu.wisc.my.apilayer.groups.IEntity;
import edu.wisc.my.apilayer.groups.IEntityGroup;
import edu.wisc.my.apilayer.groups.IEntityIdentifier;
import edu.wisc.my.apilayer.groups.IGroupMember;
import edu.wisc.my.apilayer.groups.IGroupService;
import edu.wisc.my.apilayer.groups.ILockableEntityGroup;
import edu.wisc.my.apilayer.groups.SearchMethod;


/**
 * Provides access to the portal's implementation of the methods provided
 * by {@link edu.wisc.my.apilayer.groups.GroupService}
 * 
 * @author Eric Dalquist <a href="mailto:edalquist@unicon.net">edalquist@unicon.net</a>
 * @version $Revision: 1.1.2.1 $
 * @since 1.2
 */
public interface IGroupServices {
    /**
     * @see edu.wisc.my.apilayer.groups.GroupService#findGroup(String)
     */
    public IEntityGroup findGroup(final String key) throws GroupsException;
    
    /**
     * @see edu.wisc.my.apilayer.groups.GroupService#getEntity(String, Class)
     */
    public IEntity getEntity(final String key, final Class<? extends IBasicEntity> type) throws GroupsException;
    
    /**
     * @see edu.wisc.my.apilayer.groups.GroupService#getGroupMember(String, Class)
     */
    public IGroupMember getGroupMember(final String key, final Class<? extends IBasicEntity> type) throws GroupsException;
    
    /**
     * @see edu.wisc.my.apilayer.groups.GroupService#getGroupMember(IEntityIdentifier)
     */
    public IGroupMember getGroupMember(final IEntityIdentifier underlyingIEntityIdentifier) throws GroupsException;
    
    /**
     * @see edu.wisc.my.apilayer.groups.GroupService#newGroup(Class)
     */
    public IEntityGroup newGroup(final Class<? extends IBasicEntity> type) throws GroupsException;
    
    /**
     * @see edu.wisc.my.apilayer.groups.GroupService#searchForGroups(String, SearchMethod, Class)
     */
    public IEntityIdentifier[] searchForGroups(final String query, final SearchMethod method, final Class<? extends IBasicEntity> leaftype) throws GroupsException;
    
    /**
     * @see edu.wisc.my.apilayer.groups.GroupService#searchForGroups(String, SearchMethod, Class, IEntityGroup)
     */
    public IEntityIdentifier[] searchForGroups(final String query, final SearchMethod method, final Class<? extends IBasicEntity> leaftype, final IEntityGroup ancestor) throws GroupsException;
    
    /**
     * @see edu.wisc.my.apilayer.groups.GroupService#searchForEntities(String, SearchMethod, Class)
     */
    public IEntityIdentifier[] searchForEntities(final String query, final SearchMethod method, final Class<? extends IBasicEntity> type) throws GroupsException;
    
    /**
     * @see edu.wisc.my.apilayer.groups.GroupService#searchForEntities(String, SearchMethod, Class, IEntityGroup)
     */
    public IEntityIdentifier[] searchForEntities(final String query, final SearchMethod method, final Class<? extends IBasicEntity> type, final IEntityGroup ancestor) throws GroupsException;
    
    /**
     * @see edu.wisc.my.apilayer.groups.GroupService#findLockableGroup(String, String)
     */
    public ILockableEntityGroup findLockableGroup(final String key, final String lockOwner) throws GroupsException;

    /**
     * @see edu.wisc.my.apilayer.groups.GroupService#getCompositeGroupService()
     */
    public ICompositeGroupService getCompositeGroupService() throws GroupsException;
    
    /**
     * @see edu.wisc.my.apilayer.groups.GroupService#getDistinguishedGroup(String)
     */
    public IEntityGroup getDistinguishedGroup(String name) throws GroupsException;
    
    /**
     * @see edu.wisc.my.apilayer.groups.GroupService#getEntity(String, Class, String)
     */
    public IEntity getEntity(String key, Class<? extends IBasicEntity> type, String service) throws GroupsException;
    
    /**
     * @see edu.wisc.my.apilayer.groups.GroupService#getGroupService()
     */
    public IGroupService getGroupService() throws GroupsException;
    
    /**
     * @see edu.wisc.my.apilayer.groups.GroupService#getRootGroup(Class)
     */
    public IEntityGroup getRootGroup(Class<? extends IBasicEntity> type) throws GroupsException;
    
    /**
     * @see edu.wisc.my.apilayer.groups.GroupService#isComposite()
     */
    public boolean isComposite();
    
    /**
     * @see edu.wisc.my.apilayer.groups.GroupService#newGroup(Class, String)
     */
    public IEntityGroup newGroup(Class<? extends IBasicEntity> type, String serviceName) throws GroupsException;
    
    /**
     * @see edu.wisc.my.apilayer.groups.GroupService#parseLocalKey(String)
     */
    public String parseLocalKey(String compositeKey) throws InvalidNameException, GroupsException;
    
    /**
     * @see edu.wisc.my.apilayer.groups.GroupService#parseServiceName(String)
     */
    public javax.naming.Name parseServiceName(String serviceName) throws InvalidNameException, GroupsException;
    
    /**
     * @see edu.wisc.my.apilayer.groups.GroupService#getDistinguishedGroupKey(String)
     */
    public String getDistinguishedGroupKey(final String name) throws GroupsException;
}
