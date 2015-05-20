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
package org.jboss.tools.playground.bower.internal.preferences;

import java.io.File;

import org.eclipse.jface.preference.IPreferenceStore;
import org.jboss.tools.playground.bower.internal.Activator;
import org.jboss.tools.playground.bower.internal.BowerConstants;

/**
 * @author "Ilya Buziuk (ibuziuk)"
 */
public class BowerPreferencesHolder {
	public static final String PREF_NODE_LOCATION = "Node_Location"; //$NON-NLS-1$

	public static String getNodeLocation() {
		return getBowerPreferences().getString(PREF_NODE_LOCATION);
	}

	public static void setNodeLocation(String location) {
		getBowerPreferences().setValue(PREF_NODE_LOCATION, location);
	}

	public static String getBowerExecutableLocation() {
		File bowerExecutable = new File(getBowerPreferences().getString(PREF_NODE_LOCATION), BowerConstants.BOWER_CMD);
		if (bowerExecutable != null && bowerExecutable.exists()) {
			return bowerExecutable.getAbsolutePath();
		}
		return null;
	}

	private static IPreferenceStore getBowerPreferences() {
		return Activator.getDefault().getPreferenceStore();
	}

}
