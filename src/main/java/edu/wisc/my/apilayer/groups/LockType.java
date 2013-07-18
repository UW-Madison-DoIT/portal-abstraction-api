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


/**
 * Used for specifing types of {@link edu.wisc.my.apilayer.groups.IEntityLock}s
 * in the groups API.
 * <br>
 * A READ lock guarantees repeatable reads; other clients can get READ
 * locks but not WRITE locks. A WRITE lock guarantees exclusive access; no
 * other clients can get either READ or WRITE locks on the entity.
 * 
 * @author Eric Dalquist <a href="mailto:edalquist@unicon.net">edalquist@unicon.net</a>
 * @version $Revision: 1.1.2.1 $
 * @since 1.2
 */
public class LockType implements Comparable<LockType> {
    //Used to keep track of the next ID for a LockType object
    private static int lastLockId = 0;
    
    /** Read lock */
    public static final LockType READ_LOCK = new LockType("READ_LOCK");
    /** Write lock */
    public static final LockType WRITE_LOCK = new LockType("WRITE_LOCK");

    
    /** Unique Identifier for LockType instance */
    private final int methodId;
    private final String methodName;
    
    /**
     * Create a new LockType
     */
    private LockType(final String name) {
        this.methodId = lastLockId++;
        this.methodName = name;
    }
    
    /* 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        return (obj != null && obj instanceof LockType && ((LockType)obj).methodId == this.methodId);
    }
    
    /*
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return (new Integer(this.methodId)).hashCode();
    }
    
    /* 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.methodName + ":" + this.methodId;
    }
    
    /**
     * Compares on the LockType's internal initialization value.
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(final LockType obj) {
        return this.methodId - obj.methodId;
    }
}
