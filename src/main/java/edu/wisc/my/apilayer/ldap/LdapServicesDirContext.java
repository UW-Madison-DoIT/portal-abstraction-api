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
package edu.wisc.my.apilayer.ldap;

import java.util.Hashtable;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NameClassPair;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;


/**
 * @author Eric Dalquist <a href="mailto:edalquist@unicon.net">edalquist@unicon.net</a>
 * @version $Revision: 1.1.2.1 $
 * @since 1.0
 */
public class LdapServicesDirContext implements DirContext {
    private final String name;
    private final DirContext context;
    
    /**
     * Creates a new {@link DirContext} wrapper which will be backed by the
     * {@link DirContext} representing uPortal's default ldap server.
     * 
     * @see LdapServices#getDefaultServer()
     */
    public LdapServicesDirContext() throws NamingException {
        this(null);
    }

    /**
     * Creates a new {@link DirContext} wrapper which will be backed by the
     * {@link DirContext} representing the ldap server with the specified name.
     * 
     * @param serverName The name of the ldap server to use to back this wrapper.
     * @see LdapServices#getServer(String)
     */
    public LdapServicesDirContext(final String serverName) throws NamingException {
        this.name = serverName;
        this.context = this.getDirContext(this.name);
    }
    
    /**
     * Returns the server name being used. A value of <code>null</code> means the
     * default portal ldap server is being used.
     * 
     * @return The server name being used.
     */
    public String getServerName() {
        return this.name;
    }

    /**
     * Gets the appropriate {@link ILdapServer} for the configured
     * server name then gets a {@link DirContext} from it.
     * 
     * Note that this method is only called once when the class is created and
     * the {@link DirContext} is cached from that point on.
     * 
     * @param name The name of the {@link ILdapServer} to get a {@link DirContext} for.
     * @return The appropriate {@link DirContext} for the server name.
     * @throws NamingException If there was a problem connecting to the ldap server. 
     */
    protected DirContext getDirContext(final String name) throws NamingException {
        if (name != null) {
            final ILdapServer server = LdapServices.getServer(name);
            if (server == null) {
                throw new IllegalStateException("No ILdapServer was found for " + name);
            }
            
            return server.getConnection();
        }
        
        final ILdapServer server = LdapServices.getDefaultServer();
        if (server == null) {
            throw new IllegalStateException("No default ILdapServer was found.");
        }
        
        return server.getConnection();
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#addToEnvironment(java.lang.String, java.lang.Object)
     */
    public Object addToEnvironment(String propName, Object propVal) throws NamingException {
        return this.context.addToEnvironment(propName, propVal);
    }

    /* (non-Javadoc)
     * @see javax.naming.directory.DirContext#bind(javax.naming.Name, java.lang.Object, javax.naming.directory.Attributes)
     */
    public void bind(Name name, Object obj, Attributes attrs) throws NamingException {
        this.context.bind(name, obj, attrs);
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#bind(javax.naming.Name, java.lang.Object)
     */
    public void bind(Name name, Object obj) throws NamingException {
        this.context.bind(name, obj);
    }

    /* (non-Javadoc)
     * @see javax.naming.directory.DirContext#bind(java.lang.String, java.lang.Object, javax.naming.directory.Attributes)
     */
    public void bind(String name, Object obj, Attributes attrs) throws NamingException {
        this.context.bind(name, obj, attrs);
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#bind(java.lang.String, java.lang.Object)
     */
    public void bind(String name, Object obj) throws NamingException {
        this.context.bind(name, obj);
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#close()
     */
    public void close() throws NamingException {
        this.context.close();
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#composeName(javax.naming.Name, javax.naming.Name)
     */
    public Name composeName(Name name, Name prefix) throws NamingException {
        return this.context.composeName(name, prefix);
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#composeName(java.lang.String, java.lang.String)
     */
    public String composeName(String name, String prefix) throws NamingException {
        return this.context.composeName(name, prefix);
    }

    /* (non-Javadoc)
     * @see javax.naming.directory.DirContext#createSubcontext(javax.naming.Name, javax.naming.directory.Attributes)
     */
    public DirContext createSubcontext(Name name, Attributes attrs) throws NamingException {
        return this.context.createSubcontext(name, attrs);
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#createSubcontext(javax.naming.Name)
     */
    public Context createSubcontext(Name name) throws NamingException {
        return this.context.createSubcontext(name);
    }

    /* (non-Javadoc)
     * @see javax.naming.directory.DirContext#createSubcontext(java.lang.String, javax.naming.directory.Attributes)
     */
    public DirContext createSubcontext(String name, Attributes attrs) throws NamingException {
        return this.context.createSubcontext(name, attrs);
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#createSubcontext(java.lang.String)
     */
    public Context createSubcontext(String name) throws NamingException {
        return this.context.createSubcontext(name);
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#destroySubcontext(javax.naming.Name)
     */
    public void destroySubcontext(Name name) throws NamingException {
        this.context.destroySubcontext(name);
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#destroySubcontext(java.lang.String)
     */
    public void destroySubcontext(String name) throws NamingException {
        this.context.destroySubcontext(name);
    }

    /* (non-Javadoc)
     * @see javax.naming.directory.DirContext#getAttributes(javax.naming.Name, java.lang.String[])
     */
    public Attributes getAttributes(Name name, String[] attrIds) throws NamingException {
        return this.context.getAttributes(name, attrIds);
    }

    /* (non-Javadoc)
     * @see javax.naming.directory.DirContext#getAttributes(javax.naming.Name)
     */
    public Attributes getAttributes(Name name) throws NamingException {
        return this.context.getAttributes(name);
    }

    /* (non-Javadoc)
     * @see javax.naming.directory.DirContext#getAttributes(java.lang.String, java.lang.String[])
     */
    public Attributes getAttributes(String name, String[] attrIds) throws NamingException {
        return this.context.getAttributes(name, attrIds);
    }

    /* (non-Javadoc)
     * @see javax.naming.directory.DirContext#getAttributes(java.lang.String)
     */
    public Attributes getAttributes(String name) throws NamingException {
        return this.context.getAttributes(name);
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#getEnvironment()
     */
    public Hashtable<?, ?> getEnvironment() throws NamingException {
        return this.context.getEnvironment();
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#getNameInNamespace()
     */
    public String getNameInNamespace() throws NamingException {
        return this.context.getNameInNamespace();
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#getNameParser(javax.naming.Name)
     */
    public NameParser getNameParser(Name name) throws NamingException {
        return this.context.getNameParser(name);
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#getNameParser(java.lang.String)
     */
    public NameParser getNameParser(String name) throws NamingException {
        return this.context.getNameParser(name);
    }

    /* (non-Javadoc)
     * @see javax.naming.directory.DirContext#getSchema(javax.naming.Name)
     */
    public DirContext getSchema(Name name) throws NamingException {
        return this.context.getSchema(name);
    }

    /* (non-Javadoc)
     * @see javax.naming.directory.DirContext#getSchema(java.lang.String)
     */
    public DirContext getSchema(String name) throws NamingException {
        return this.context.getSchema(name);
    }

    /* (non-Javadoc)
     * @see javax.naming.directory.DirContext#getSchemaClassDefinition(javax.naming.Name)
     */
    public DirContext getSchemaClassDefinition(Name name) throws NamingException {
        return this.context.getSchemaClassDefinition(name);
    }

    /* (non-Javadoc)
     * @see javax.naming.directory.DirContext#getSchemaClassDefinition(java.lang.String)
     */
    public DirContext getSchemaClassDefinition(String name) throws NamingException {
        return this.context.getSchemaClassDefinition(name);
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#list(javax.naming.Name)
     */
    public NamingEnumeration<NameClassPair> list(Name name) throws NamingException {
        return this.context.list(name);
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#list(java.lang.String)
     */
    public NamingEnumeration<NameClassPair> list(String name) throws NamingException {
        return this.context.list(name);
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#listBindings(javax.naming.Name)
     */
    public NamingEnumeration<Binding> listBindings(Name name) throws NamingException {
        return this.context.listBindings(name);
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#listBindings(java.lang.String)
     */
    public NamingEnumeration<Binding> listBindings(String name) throws NamingException {
        return this.context.listBindings(name);
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#lookup(javax.naming.Name)
     */
    public Object lookup(Name name) throws NamingException {
        return this.context.lookup(name);
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#lookup(java.lang.String)
     */
    public Object lookup(String name) throws NamingException {
        return this.context.lookup(name);
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#lookupLink(javax.naming.Name)
     */
    public Object lookupLink(Name name) throws NamingException {
        return this.context.lookupLink(name);
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#lookupLink(java.lang.String)
     */
    public Object lookupLink(String name) throws NamingException {
        return this.context.lookupLink(name);
    }

    /* (non-Javadoc)
     * @see javax.naming.directory.DirContext#modifyAttributes(javax.naming.Name, int, javax.naming.directory.Attributes)
     */
    public void modifyAttributes(Name name, int mod_op, Attributes attrs) throws NamingException {
        this.context.modifyAttributes(name, mod_op, attrs);
    }

    /* (non-Javadoc)
     * @see javax.naming.directory.DirContext#modifyAttributes(javax.naming.Name, javax.naming.directory.ModificationItem[])
     */
    public void modifyAttributes(Name name, ModificationItem[] mods) throws NamingException {
        this.context.modifyAttributes(name, mods);
    }

    /* (non-Javadoc)
     * @see javax.naming.directory.DirContext#modifyAttributes(java.lang.String, int, javax.naming.directory.Attributes)
     */
    public void modifyAttributes(String name, int mod_op, Attributes attrs) throws NamingException {
        this.context.modifyAttributes(name, mod_op, attrs);
    }

    /* (non-Javadoc)
     * @see javax.naming.directory.DirContext#modifyAttributes(java.lang.String, javax.naming.directory.ModificationItem[])
     */
    public void modifyAttributes(String name, ModificationItem[] mods) throws NamingException {
        this.context.modifyAttributes(name, mods);
    }

    /* (non-Javadoc)
     * @see javax.naming.directory.DirContext#rebind(javax.naming.Name, java.lang.Object, javax.naming.directory.Attributes)
     */
    public void rebind(Name name, Object obj, Attributes attrs) throws NamingException {
        this.context.rebind(name, obj, attrs);
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#rebind(javax.naming.Name, java.lang.Object)
     */
    public void rebind(Name name, Object obj) throws NamingException {
        this.context.rebind(name, obj);
    }

    /* (non-Javadoc)
     * @see javax.naming.directory.DirContext#rebind(java.lang.String, java.lang.Object, javax.naming.directory.Attributes)
     */
    public void rebind(String name, Object obj, Attributes attrs) throws NamingException {
        this.context.rebind(name, obj, attrs);
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#rebind(java.lang.String, java.lang.Object)
     */
    public void rebind(String name, Object obj) throws NamingException {
        this.context.rebind(name, obj);
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#removeFromEnvironment(java.lang.String)
     */
    public Object removeFromEnvironment(String propName) throws NamingException {
        return this.context.removeFromEnvironment(propName);
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#rename(javax.naming.Name, javax.naming.Name)
     */
    public void rename(Name oldName, Name newName) throws NamingException {
        this.context.rename(oldName, newName);
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#rename(java.lang.String, java.lang.String)
     */
    public void rename(String oldName, String newName) throws NamingException {
        this.context.rename(oldName, newName);
    }

    /* (non-Javadoc)
     * @see javax.naming.directory.DirContext#search(javax.naming.Name, javax.naming.directory.Attributes, java.lang.String[])
     */
    public NamingEnumeration<SearchResult> search(Name name, Attributes matchingAttributes, String[] attributesToReturn) throws NamingException {
        return this.context.search(name, matchingAttributes, attributesToReturn);
    }

    /* (non-Javadoc)
     * @see javax.naming.directory.DirContext#search(javax.naming.Name, javax.naming.directory.Attributes)
     */
    public NamingEnumeration<SearchResult> search(Name name, Attributes matchingAttributes) throws NamingException {
        return this.context.search(name, matchingAttributes);
    }

    /* (non-Javadoc)
     * @see javax.naming.directory.DirContext#search(javax.naming.Name, java.lang.String, java.lang.Object[], javax.naming.directory.SearchControls)
     */
    public NamingEnumeration<SearchResult> search(Name name, String filterExpr, Object[] filterArgs, SearchControls cons) throws NamingException {
        return this.context.search(name, filterExpr, filterArgs, cons);
    }

    /* (non-Javadoc)
     * @see javax.naming.directory.DirContext#search(javax.naming.Name, java.lang.String, javax.naming.directory.SearchControls)
     */
    public NamingEnumeration<SearchResult> search(Name name, String filter, SearchControls cons) throws NamingException {
        return this.context.search(name, filter, cons);
    }

    /* (non-Javadoc)
     * @see javax.naming.directory.DirContext#search(java.lang.String, javax.naming.directory.Attributes, java.lang.String[])
     */
    public NamingEnumeration<SearchResult> search(String name, Attributes matchingAttributes, String[] attributesToReturn) throws NamingException {
        return this.context.search(name, matchingAttributes, attributesToReturn);
    }

    /* (non-Javadoc)
     * @see javax.naming.directory.DirContext#search(java.lang.String, javax.naming.directory.Attributes)
     */
    public NamingEnumeration<SearchResult> search(String name, Attributes matchingAttributes) throws NamingException {
        return this.context.search(name, matchingAttributes);
    }

    /* (non-Javadoc)
     * @see javax.naming.directory.DirContext#search(java.lang.String, java.lang.String, java.lang.Object[], javax.naming.directory.SearchControls)
     */
    public NamingEnumeration<SearchResult> search(String name, String filterExpr, Object[] filterArgs, SearchControls cons) throws NamingException {
        return this.context.search(name, filterExpr, filterArgs, cons);
    }

    /* (non-Javadoc)
     * @see javax.naming.directory.DirContext#search(java.lang.String, java.lang.String, javax.naming.directory.SearchControls)
     */
    public NamingEnumeration<SearchResult> search(String name, String filter, SearchControls cons) throws NamingException {
        return this.context.search(name, filter, cons);
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#unbind(javax.naming.Name)
     */
    public void unbind(Name name) throws NamingException {
        this.context.unbind(name);
    }

    /* (non-Javadoc)
     * @see javax.naming.Context#unbind(java.lang.String)
     */
    public void unbind(String name) throws NamingException {
        this.context.unbind(name);
    }
}
