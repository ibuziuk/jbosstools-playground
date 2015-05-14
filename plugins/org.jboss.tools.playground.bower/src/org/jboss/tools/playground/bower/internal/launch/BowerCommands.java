/*******************************************************************************
 * Copyright (c) 2015 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 *  Contributors:
 *       Red Hat, Inc. - initial API and implementation
 *******************************************************************************/
package org.jboss.tools.playground.bower.internal.launch;

/**
 * @author "Ilya Buziuk (ibuziuk)"
 */
public enum BowerCommands {
	INIT("init"),  //$NON-NLS-1$
	INSTALL("install"),  //$NON-NLS-1$
	UNINSTALL("uninstall"), //$NON-NLS-1$
	UPDATE("update");  //$NON-NLS-1$

	private final String value;

	private BowerCommands(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
