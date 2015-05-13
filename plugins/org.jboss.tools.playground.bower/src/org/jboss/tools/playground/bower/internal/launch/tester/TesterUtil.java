/******************************************************************************* 
 * Copyright (c) 2015 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/
package org.jboss.tools.playground.bower.internal.launch.tester;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Ilya Buziuk (ibuziuk)
 */
public final class TesterUtil {
	private static final String BOWER_JSON = "bower.json"; //$NON-NLS-1$

	private TesterUtil() {
	}

	public static boolean isBowerInit(final IProject project) throws CoreException {
		if (project != null) {
			IResource[] members = project.members(); 
			for (IResource member : members) {
				if (BOWER_JSON.equals(member.getName()) && member.exists()) {
					return true;
				}
			}
		}
		return false;
	}
	
}