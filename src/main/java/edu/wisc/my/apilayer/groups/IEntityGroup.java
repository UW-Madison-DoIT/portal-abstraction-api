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

import javax.naming.Name;


/**
 * An <code>IEntityGroup</code> is a composite, or non-leaf <code>IGroupMember</code>.
 * It contains <code>IEntities</code> and other <code>IEntityGroups</code>.
 * <p>
 * The api defines methods for adding a member to, and removing it from, a group, 
 * though not vice versa.  (Although there is nothing to prevent a given <code>IGroupMember</code>
 * implementation from storing references to its containing groups.)  These methods only
 * change the group structure in memory.
 * <p>
 *   <code>addMember(IGroupMember gm)</code><br>
 *   <code>removeMember(IGroupMember gm)</code><br>
 * <p>
 * The following methods commit changes in the group structure to the
 * persistent store:
 * <p>
 *   <code>delete()</code> - delete the group and its memberships<br>
 *   <code>update()</code>  - insert or update the group, as appropriate<br>
 *   <code>updateMembers()</code> - insert/update/delete group memberships as appropriate<br>
 * <p>
 * The following methods were added to permit an <code>IEntityGroup</code> to function
 * within a composite group service:
 * <p>
 *   <code>getLocalKey()</code> - returns the key within the service of origin.<br>
 *   <code>getServiceName()</code> - returns the Name of the group service of origin.<br>
 *   <code>setLocalGroupService()</code> - sets the group service of origin.<br>
 * <p>
 *
 * @author Eric Dalquist <a href="mailto:edalquist@unicon.net">edalquist@unicon.net</a>
 * @version $Revision: 1.1.2.1 $
 * @since 1.2
 */
public interface IEntityGroup extends IGroupMember {
    /**
     * Adds <code>IGroupMember</code> gm to this group, but does not commit it to the
     * data store.  Use <code>updateMembers()</code> to commit memberships to the data store.
     * @param gm org.jasig.portal.groups.IGroupMember
     * @exception GroupsException is thrown if the member is a group and
     * this group already has a group with the same name or if the addition
     * of the group creates a circular reference.
     */
    public void addMember(IGroupMember gm) throws GroupsException;

    /**
     * Deletes the <code>IEntityGroup</code> from the data store.
     * @exception GroupsException if the delete cannot be performed. 
     */
    public void delete() throws GroupsException;

    /**
     * Returns the name of the group creator.  May be null.
     * @return String The name of the group's creator, may be null.
     */
    public String getCreatorID();

    /**
     * Returns the group description, which may be null.
     * @return String The description of the group, may be null.
     */
    public String getDescription();

    /**
     * Returns the key from the group service of origin.
     * @return String The key within the group service that owns this group.
     */
    public String getLocalKey();

    /**
     * Returns the group name.
     * @return String The name of the group.
     */
    public String getName();

    /**
     * Returns the Name of the group service of origin.
     * @return String The name of the group service that owns this group.
     */
    public Name getServiceName();

    /**
     * Answers if this <code>IEntityGroup</code> can be changed or deleted.
     * @return boolean <code>true</code> if it can be modified, <code>false</code> otherwise.
     * @exception GroupsException
     */
    public boolean isEditable() throws GroupsException;

    /**
     * Removes the <code>IGroupMember</code> from this group, but does not remove the
     * membership from the data store.
     * @param gm org.jasig.portal.groups.IGroupMember
     */
    public void removeMember(IGroupMember gm) throws GroupsException;

    /**
     * @param userID String (required)
     */
    public void setCreatorID(String userID);

    /**
     * @param name String (may be null)
     */
    public void setDescription(String name);

    /**
     * Sets the group name which must be unique within any of its containing 
     * groups.  
     * @param name The name to set for the group.
     * @exception GroupsException
     */
    public void setName(String name) throws GroupsException;

    /**
     * Commit the <code>IEntityGroup</code> AND ITS MEMBERSHIPS to the data store.
     * @exception GroupsException if the update cannot be performed. 
     */
    public void update() throws GroupsException;

    /**
     * Commit this <code>IEntityGroup's</code> memberships to the data store.
     * @exception GroupsException if the update cannot be performed. 
     */
    public void updateMembers() throws GroupsException;

    /**
     * Sets the group service of origin.
     */
    public void setLocalGroupService(IIndividualGroupService groupService) throws GroupsException;
}
