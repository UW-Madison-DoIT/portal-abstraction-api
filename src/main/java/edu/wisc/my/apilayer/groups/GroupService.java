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
package edu.wisc.my.apilayer.groups;

import javax.naming.InvalidNameException;
import javax.naming.Name;

import edu.wisc.my.apilayer.internal.IGroupServices;
import edu.wisc.my.apilayer.internal.IPortalServices;
import edu.wisc.my.apilayer.internal.PortalServicesLocator;


/**
 * @author Eric Dalquist <a href="mailto:edalquist@unicon.net">edalquist@unicon.net</a>
 * @version $Revision: 1.1.2.1 $
 * @since 1.2
 */
public final class GroupService {
    /** Hide the constructor so this class cannot be instanciated */
    private GroupService() { }
    
    /**
     * Returns a pre-existing <code>IEntityGroup</code> or null if the
     * <code>IGroupMember</code> does not exist.
     * 
     * @see ICompositeGroupService#findGroup(String)
     * @see IGroupService#findGroup(String)
     */
    public static IEntityGroup findGroup(final String key) throws GroupsException {
        final IPortalServices ps = PortalServicesLocator.getPortalServices();
        final IGroupServices gsb = ps.getGroupServices();

        return gsb.findGroup(key);
    }

    /**
     * Returns an <code>IEntity</code> representing a portal entity.  This does
     * not guarantee that the entity actually exists.
     * 
     * @see ICompositeGroupService#getEntity(String, Class)
     * @see IGroupService#getEntity(String, Class)
     */
    public static IEntity getEntity(final String key, final Class<? extends IBasicEntity> type) throws GroupsException {
        final IPortalServices ps = PortalServicesLocator.getPortalServices();
        final IGroupServices gsb = ps.getGroupServices();

        return gsb.getEntity(key, type);
    }

    /**
     * Returns an <code>IGroupMember</code> representing either a group or a
     * portal entity.  If the parm <code>type</code> is the group type,
     * the <code>IGroupMember</code> is an <code>IEntityGroup</code> else it is
     * an <code>IEntity</code>.
     * 
     * @see ICompositeGroupService#getGroupMember(String, Class)
     * @see IGroupService#getGroupMember(String, Class)
     */
    public static IGroupMember getGroupMember(final String key, final Class<? extends IBasicEntity> type) throws GroupsException {
        final IPortalServices ps = PortalServicesLocator.getPortalServices();
        final IGroupServices gsb = ps.getGroupServices();

        return gsb.getGroupMember(key, type);
    }

    /**
     * Returns an <code>IGroupMember</code> representing either a group or a
     * portal entity, based on the <code>IEntityIdentifier</code>, which
     * refers to the UNDERLYING entity for the <code>IGroupMember</code>.
     * 
     * @see ICompositeGroupService#getGroupMember(IEntityIdentifier)
     * @see IGroupService#getGroupMember(IEntityIdentifier)
     */
    public static IGroupMember getGroupMember(final IEntityIdentifier underlyingEntityIdentifier) throws GroupsException {
        final IPortalServices ps = PortalServicesLocator.getPortalServices();
        final IGroupServices gsb = ps.getGroupServices();

        return gsb.getGroupMember(underlyingEntityIdentifier);
    }

    /**
     * Returns a new <code>IEntityGroup</code> for the given Class<? extends IBasicEntity> with an unused
     * key.
     * 
     * @see IGroupService#newGroup(Class)
     * @see ICompositeGroupService#newGroup(Class, Name)
     */
    public static IEntityGroup newGroup(final Class<? extends IBasicEntity> type) throws GroupsException {
        final IPortalServices ps = PortalServicesLocator.getPortalServices();
        final IGroupServices gsb = ps.getGroupServices();

        return gsb.newGroup(type);
    }

    /**
     * Find IEntityIdentifiers for groups whose name matches the query string
     * according to the specified method and matches the provided leaf type
     * 
     * @see ICompositeGroupService#searchForGroups(String, SearchMethod, Class)
     * @see IGroupService#searchForGroups(String, SearchMethod, Class)
     */
    public static IEntityIdentifier[] searchForGroups(final String query, final SearchMethod method, final Class<? extends IBasicEntity> leaftype) throws GroupsException {
        final IPortalServices ps = PortalServicesLocator.getPortalServices();
        final IGroupServices gsb = ps.getGroupServices();

        return gsb.searchForGroups(query, method, leaftype);
    }

    /**
     * Find IEntityIdentifiers for groups whose name matches the query string
     * according to the specified method, has the provided leaf type  and
     * descends from the specified group
     * 
     * @see ICompositeGroupService#searchForGroups(String, SearchMethod, Class, IEntityGroup)
     * @see IGroupService#searchForGroups(String, SearchMethod, Class, IEntityGroup)
     */
    public static IEntityIdentifier[] searchForGroups(final String query, final SearchMethod method, final Class<? extends IBasicEntity> leaftype, final IEntityGroup ancestor) throws GroupsException {
        final IPortalServices ps = PortalServicesLocator.getPortalServices();
        final IGroupServices gsb = ps.getGroupServices();

        return gsb.searchForGroups(query, method, leaftype, ancestor);
    }

    /**
     * Find IEntityIdentifiers for entities whose name matches the query string
     * according to the specified method and is of the specified type
     * 
     * @see ICompositeGroupService#searchForEntities(String, SearchMethod, Class)
     * @see IGroupService#searchForEntities(String, SearchMethod, Class)
     */
    public static IEntityIdentifier[] searchForEntities(final String query, final SearchMethod method, final Class<? extends IBasicEntity> type) throws GroupsException {
        final IPortalServices ps = PortalServicesLocator.getPortalServices();
        final IGroupServices gsb = ps.getGroupServices();

        return gsb.searchForGroups(query, method, type);
    }

    /**
     * Find IEntityIdentifiers for entities whose name matches the query string
     * according to the specified method, is of the specified type  and
     * descends from the specified group
     * 
     * @see ICompositeGroupService#searchForEntities(String, SearchMethod, Class, IEntityGroup)
     * @see IGroupService#searchForEntities(String, SearchMethod, Class, IEntityGroup)
     */
    public static IEntityIdentifier[] searchForEntities(final String query, final SearchMethod method, final Class<? extends IBasicEntity> type, final IEntityGroup ancestor) throws GroupsException {
        final IPortalServices ps = PortalServicesLocator.getPortalServices();
        final IGroupServices gsb = ps.getGroupServices();

        return gsb.searchForGroups(query, method, type, ancestor);
    }

    /**
     * Returns a pre-existing <code>IEntityGroup</code> or null if it does not
     * exist.
     * 
     * @see ICompositeGroupService#findGroupWithLock(String, String)
     */
    public static ILockableEntityGroup findLockableGroup(final String key, final String lockOwner) throws GroupsException {
        final IPortalServices ps = PortalServicesLocator.getPortalServices();
        final IGroupServices gsb = ps.getGroupServices();

        return gsb.findLockableGroup(key, lockOwner);
    }
              
    /**
     * Gets the {@link IComponentGroupService} that provides access to all
     * the member group services the portal is using. This will be
     * <code>null</code> if {@link #isComposite()} return <code>false</code>.
     *
     * @return The {@link IComponentGroupService} being used.
     */
    public static ICompositeGroupService getCompositeGroupService() throws GroupsException {
        final IPortalServices ps = PortalServicesLocator.getPortalServices();
        final IGroupServices gsb = ps.getGroupServices();

        return gsb.getCompositeGroupService();
    }
    
    /**
     * Retrieves a named group, if one does not exist for the name <code>null</code>
     * is returned.
     * 
     * @param name The name of the group to find.
     * @return The group associated with the name, <code>null</code> if one isn't found.
     */
    public static IEntityGroup getDistinguishedGroup(final String name) throws GroupsException {
        final IPortalServices ps = PortalServicesLocator.getPortalServices();
        final IGroupServices gsb = ps.getGroupServices();

        return gsb.getDistinguishedGroup(name);
    }
    
    /**
     * Returns an <code>IEntity</code> representing a portal entity.  This does
     * not guarantee that the entity actually exists.
     * 
     * @see ICompositeGroupService#getEntity(String, Class, String)
     */
    public static IEntity getEntity(final String key, final Class<? extends IBasicEntity> type, final String service) throws GroupsException {
        final IPortalServices ps = PortalServicesLocator.getPortalServices();
        final IGroupServices gsb = ps.getGroupServices();

        return gsb.getEntity(key, type, service);
    }
               
    /**
     * Gets the {@link IGroupService} that the portal is using. This will be
     * <code>null</code> if {@link #isComposite()} return <code>true</code>.
     * 
     * @return The {@link IGroupService} that is being used.
     */
    public static IGroupService getGroupService() throws GroupsException {
        final IPortalServices ps = PortalServicesLocator.getPortalServices();
        final IGroupServices gsb = ps.getGroupServices();

        return gsb.getGroupService();
    }

    /**
     * Gets the root group associated with the specified type.
     * 
     * @param type The type to lookup the root group for.
     * @return The root group for the type, or <code>null</code> if on doesn't exist.
     */
    public static IEntityGroup getRootGroup(final Class<? extends IBasicEntity> type) throws GroupsException {
        final IPortalServices ps = PortalServicesLocator.getPortalServices();
        final IGroupServices gsb = ps.getGroupServices();

        return gsb.getRootGroup(type);
    }

    /**
     * Returns <code>true</code> if the composite group service is being used,
     * <code>false</code> if the standard group service is being used.
     * 
     * @return <code>true</code> if the composite group service is being used, <code>false</code> otherwise.
     */
    public static boolean isComposite() {
        final IPortalServices ps = PortalServicesLocator.getPortalServices();
        final IGroupServices gsb = ps.getGroupServices();

        return gsb.isComposite();
    }

    /**
     * Returns a new <code>IEntityGroup</code> for the given Class<? extends IBasicEntity> with an unused
     * key from the named service.
     * 
     * @see ICompositeGroupService#newGroup(Class, Name)
     */
    public static IEntityGroup newGroup(final Class<? extends IBasicEntity> type, final String serviceName) throws GroupsException {
        final IPortalServices ps = PortalServicesLocator.getPortalServices();
        final IGroupServices gsb = ps.getGroupServices();

        return gsb.newGroup(type, serviceName);
    }

    /**
     * Extracts the final node from the String form of a composite key.
     * 
     * @param compositeKey The key to parse.
     * @return The final node of the key
     * @throws InvalidNameException If the key is invalid.
     * @throws GroupsException If the key is invalid.
     */
    public static String parseLocalKey(final String compositeKey) throws InvalidNameException, GroupsException {
        final IPortalServices ps = PortalServicesLocator.getPortalServices();
        final IGroupServices gsb = ps.getGroupServices();

        return gsb.parseLocalKey(compositeKey);
    }

    /**
     * Converts the String form of a service name into a {@link javax.naming.Name}.
     * 
     * @param serviceName The service name to parse.
     * @return A parsed service name
     * @throws InvalidNameException If the name is invalid.
     * @throws GroupsException If the name is invalid.
     */
    public static Name parseServiceName(final String serviceName) throws InvalidNameException, GroupsException {
        final IPortalServices ps = PortalServicesLocator.getPortalServices();
        final IGroupServices gsb = ps.getGroupServices();

        return gsb.parseServiceName(serviceName);
    }
    
    /**
     * Gets the distinguished key for the named group.
     * 
     * @param name The name of the group.
     * @return The distinguished key for the group, <code>null</code> if it doesn't exist.
     */
    public static String getDistinguishedGroupKey(final String name) throws GroupsException {
        final IPortalServices ps = PortalServicesLocator.getPortalServices();
        final IGroupServices gsb = ps.getGroupServices();

        return gsb.getDistinguishedGroupKey(name);
    }
}
