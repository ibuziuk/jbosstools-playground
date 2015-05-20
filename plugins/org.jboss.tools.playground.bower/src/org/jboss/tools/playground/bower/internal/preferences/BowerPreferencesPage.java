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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.jboss.tools.playground.bower.internal.Activator;
import org.jboss.tools.playground.bower.internal.BowerConstants;

/**
 * @author "Ilya Buziuk (ibuziuk)"
 */
public class BowerPreferencesPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	public static final String PAGE_ID = "org.jboss.tools.playground.bower.internal.preferences.BowerPreferencesPage"; //$NON-NLS-1$
	private String nodeLocation;
	
	public BowerPreferencesPage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Node settings for bower support");
	}
	

	@Override
	public void init(IWorkbench workbench) {
	}
	
	@Override
	public boolean performOk() {
		BowerPreferencesHolder.setNodeLocation(nodeLocation);
		return true;
	}

	@Override
	protected void createFieldEditors() {
		BowerHomeFieldEditor editor = new BowerHomeFieldEditor("Node Home", "Node", getFieldEditorParent());
		addField(editor);
	}
	
	private static class BowerHomeFieldEditor extends DirectoryFieldEditor {
		public String nodeLocation;
		
		public BowerHomeFieldEditor(String key, String label, Composite composite) {
			super(key, label, composite);
			setEmptyStringAllowed(true);
		}
		
		@Override
		protected boolean doCheckState() {
			String filename = getTextControl().getText();
			filename = filename.trim();
			if (filename.isEmpty()) {
				this.getPage().setMessage("A location for the Node must be specified", IStatus.WARNING);
				return true;
			} else {
				// clear the warning message
				this.getPage().setMessage(null);
			}

			if (!filename.endsWith(File.separator)) {
				filename = filename + File.separator;
			}
			
			File file = new File(filename);
			if (!file.isDirectory()){
				setErrorMessage("A directory must be specified"); //$NON-NLS-1$
				return false;
			}
			
			File bower = new File(file, BowerConstants.BOWER_CMD);
			if (bower == null || !bower.exists()) {
				setErrorMessage("bower must be installed");
				return false;
			}
			this.nodeLocation = file.getAbsolutePath();
			return true;
		}
		
		@Override
		public void setValidateStrategy(int value) {
			super.setValidateStrategy(VALIDATE_ON_KEY_STROKE);
		}
		
	}

}
