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
package org.jboss.tools.playground.bower.internal.launch.shortcut;

import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;

/**
 * @author "Ilya Buziuk (ibuziuk)"
 */
public class BowerInit implements ILaunchShortcut {

	@Override
	public void launch(ISelection selection, String mode) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void launch(IEditorPart editor, String mode) {			
	}
	 
}