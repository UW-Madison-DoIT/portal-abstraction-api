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
package edu.wisc.my.apilayer.rdbm;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;


/**
 * {@link javax.sql.DataSource} backed by {@link edu.wisc.my.apilayer.rdbm.DatabaseServices}.
 * This class allows clients to consume {@link edu.wisc.my.apilayer.rdbm.DatabaseServices}
 * as a {@link javax.sql.DataSource}.
 * 
 * Depending on the constructor used either the default database or a named
 * database will be used.
 * 
 * @author Eric Dalquist <a href="mailto:edalquist@unicon.net">edalquist@unicon.net</a>
 * @version $Revision: 1.1.2.1 $
 * @since 1.0
 */
public class DatabaseServicesDataSource implements DataSource {
    private final String name;
    
    /**
     * Creates a new {@link DataSource} wrapper which will be backed by the
     * {@link DataSource} representing uPortal's default database.
     * 
     * @see DatabaseServices#getDefaultServer()
     */
    public DatabaseServicesDataSource() {
        this.name = null;
    }

    /**
     * Creates a new {@link DataSource} wrapper which will be backed by the
     * {@link DataSource} representing the database with the specified name.
     * 
     * @param serverName The name of the database server to use to back this wrapper.
     * @see DatabaseServices#getServer(String)
     */
    public DatabaseServicesDataSource(final String serverName) {
        this.name = serverName;
    }
    
    /**
     * Returns the server name being used. A value of <code>null</code> means the
     * default portal database server is being used.
     * 
     * @return The server name being used.
     */
    public String getServerName() {
        return this.name;
    }

    /**
     * @see javax.sql.DataSource#getLoginTimeout()
     */
    public int getLoginTimeout() throws SQLException {
        final DataSource ds = this.getDataSource();
        return ds.getLoginTimeout();
    }

    /**
     * @see javax.sql.DataSource#setLoginTimeout(int)
     */
    public void setLoginTimeout(final int seconds) throws SQLException {
        final DataSource ds = this.getDataSource();
        ds.setLoginTimeout(seconds);
    }

    /**
     * @see javax.sql.DataSource#getLogWriter()
     */
    public PrintWriter getLogWriter() throws SQLException {
        final DataSource ds = this.getDataSource();
        return ds.getLogWriter();
    }

    /**
     * @see javax.sql.DataSource#setLogWriter(java.io.PrintWriter)
     */
    public void setLogWriter(final PrintWriter out) throws SQLException {
        final DataSource ds = this.getDataSource();
        ds.setLogWriter(out);
    }

    /**
     * @see javax.sql.DataSource#getConnection()
     */
    public Connection getConnection() throws SQLException {
        final DataSource ds = this.getDataSource();
        return ds.getConnection();
    }

    /**
     * @see javax.sql.DataSource#getConnection(java.lang.String, java.lang.String)
     */
    public Connection getConnection(final String username, final String password) throws SQLException {
        final DataSource ds = this.getDataSource();
        return ds.getConnection(username, password);
    }
    
    /* (non-Javadoc)
     * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
     */
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        final DataSource ds = this.getDataSource();
        return ds.isWrapperFor(iface);
    }

    /* (non-Javadoc)
     * @see java.sql.Wrapper#unwrap(java.lang.Class)
     */
    public <T> T unwrap(Class<T> iface) throws SQLException {
        final DataSource ds = this.getDataSource();
        return ds.unwrap(iface);
    }

    /**
     * Gets the appropriate {@link IDatabaseServer} for the configured
     * server name then gets a {@link DataSource} from it.
     * 
     * Note that the {@link IDatabaseServer} and {@link DataSource} are not
     * cached on purpose. We are relying on {@link DatabaseServices} to perform
     * any caching of these objects that is needed.
     * 
     * @return The appropriate {@link DataSource} for the server name.
     */
    protected DataSource getDataSource() {
        if (this.name != null) {
            final IDatabaseServer server = DatabaseServices.getServer(this.name);
            if (server == null) {
                throw new IllegalStateException("No IDatabaseServer was found for " + this.name);
            }
            
            return server.getDataSource();
        }
        
        final IDatabaseServer server = DatabaseServices.getDefaultServer();
        if (server == null) {
            throw new IllegalStateException("No default IDatabaseServer was found.");
        }
        
        return server.getDataSource();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return this.getDataSource().getParentLogger();
    }
}
