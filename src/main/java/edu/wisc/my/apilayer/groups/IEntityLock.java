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

import java.util.Date;


/**
 * Defines a lock associated with an owner and a portal entity that
 * guarantees some degree of exclusive access to the entity, depending on
 * <code>lockType</code> and <code>expirationTime</code>.
 *
 * See IEntityLockService for the rules governing lock assignment
 * and a code example.
 *
 * @author Eric Dalquist <a href="mailto:edalquist@unicon.net">edalquist@unicon.net</a>
 * @version $Revision: 1.1.2.1 $
 * @since 1.2
 */
public interface IEntityLock {
    /**
     * Attempts to change the <code>lockType</code> of this lock to
     * <code>newType</code>.  The <code>expirationTime</code> is renewed.
     *
     * @param newType int
     * @exception org.jasig.portal.concurrency.LockingException - if the conversion fails.
     */
    public void convert(LockType newType) throws LockingException;

    /**
     * Attempts to change the <code>lockType</code> of this lock to
     * <code>newType</code>.  The <code>expirationTime</code> is extended
     * <code>newDuration</code> seconds.
     *
     * @param newType int
     * @param newDuration int
     * @exception org.jasig.portal.concurrency.LockingException - if the conversion fails.
     */
    public void convert(LockType newType, int newDuration) throws LockingException;

    /**
     * @return java.lang.String
     */
    public String getEntityKey();

    /**
     * @return java.lang.Class
     */
    public Class<? extends IBasicEntity> getEntityType();

    /**
     * @return java.util.Date
     */
    public Date getExpirationTime();

    /**
     * Could be the portal user or the framework or ...?
     * @return java.lang.String
     */
    public String getLockOwner();

    /**
     * See IEntityLockingService for a description of lock types.
     * @return int
     */
    public LockType getLockType();

    /**
     * Answers if this lock is still good.
     */
    public boolean isValid() throws LockingException;

    /**
     * Invalidate the lock.
     */
    public void release() throws LockingException;

    /**
     * Extends the expiration time of this lock for a service-defined period.
     */
    public void renew() throws LockingException;

    /**
     * Extends the expiration time of this lock for <code>duration</code> seconds.
     */
    public void renew(int duration) throws LockingException;
}
