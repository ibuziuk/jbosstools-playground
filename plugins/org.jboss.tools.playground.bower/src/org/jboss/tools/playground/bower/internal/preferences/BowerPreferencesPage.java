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

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * @author "Ilya Buziuk (ibuziuk)"
 */
public class BowerPreferencesPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
//	private static final String BOWER_HOME = "org.jboss.tools.playground.bower.home"; //$NON-NLS-1$
	public static final String PAGE_ID = "org.jboss.tools.playground.bower.internal.preferences.BowerPreferencesPage"; //$NON-NLS-1$
	
	public BowerPreferencesPage() {
		super(GRID);
//		setPreferenceStore();
//		setDescription(description);
	}
	

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected void createFieldEditors() {
		BowerHomeFieldEditor editor = new BowerHomeFieldEditor("Bower Home", "Bower", getFieldEditorParent());
		addField(editor);
	}
	
	private static class BowerHomeFieldEditor extends DirectoryFieldEditor {
		
		public BowerHomeFieldEditor(String key, String label, Composite composite) {
			super(key, label, composite);
			setEmptyStringAllowed(true);
		}
		
		@Override
		public void setValidateStrategy(int value) {
			super.setValidateStrategy(VALIDATE_ON_KEY_STROKE);
		}
		
	}

}
