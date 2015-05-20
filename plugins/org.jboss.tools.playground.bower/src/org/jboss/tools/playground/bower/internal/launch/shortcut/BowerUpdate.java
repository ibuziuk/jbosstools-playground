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

import org.eclipse.core.externaltools.internal.IExternalToolConstants;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.jboss.tools.playground.bower.internal.Activator;
import org.jboss.tools.playground.bower.internal.launch.BowerCommands;
import org.jboss.tools.playground.bower.internal.preferences.BowerPreferencesHolder;
import org.jboss.tools.playground.bower.internal.util.BowerUtil;

/**
 * @author "Ilya Buziuk (ibuziuk)"
 */
@SuppressWarnings("restriction")
public class BowerUpdate implements ILaunchShortcut {
	private static final String NAME = "Bower Update"; //$NON-NLS-1$

	@Override
	public void launch(ISelection selection, String mode) {
		if (selection instanceof IStructuredSelection) {
			 Object element = ((IStructuredSelection)selection).getFirstElement();
			 if (element != null && element instanceof IResource) {
				 IProject project = ((IResource) element).getProject();
				 try {
					String executionPath = BowerUtil.getExecutionPath(project);
					execute(executionPath);
				} catch (CoreException e) {
					Activator.logError(e);
				}
			 }
		}
	}

	@Override
	public void launch(IEditorPart editor, String mode) {			
	}
	
	private void execute(String commandExecutionPath) throws CoreException {
		ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
		ILaunchConfigurationType programType = manager.getLaunchConfigurationType(IExternalToolConstants.ID_PROGRAM_LAUNCH_CONFIGURATION_TYPE);
		ILaunchConfiguration cfg = programType.newInstance(null, NAME);
		ILaunchConfigurationWorkingCopy wc = cfg.getWorkingCopy();
		wc.setAttribute(IExternalToolConstants.ATTR_LOCATION, BowerPreferencesHolder.getBowerExecutableLocation());
		wc.setAttribute(IExternalToolConstants.ATTR_WORKING_DIRECTORY, "${workspace_loc:" + commandExecutionPath +"}"); //$NON-NLS-1$ //$NON-NLS-2$
		wc.setAttribute(IExternalToolConstants.ATTR_TOOL_ARGUMENTS, BowerCommands.UPDATE.getValue());
		cfg = wc.doSave();
		cfg.launch(ILaunchManager.RUN_MODE, null, false, true);
		cfg.delete();
	}
	 
}
