package org.eclipse.egit.ui.internal.clone;

import java.util.List;

import org.eclipse.egit.ui.internal.clone.GitCloneSourceProviderExtension.CloneSourceProvider;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.internal.wizards.datatransfer.Activator;
import org.eclipse.ui.internal.wizards.datatransfer.NestedProjectsWizardPage;
import org.eclipse.ui.internal.wizards.datatransfer.SelectImportRootWizardPage;

public class EasymportGitWizard extends AbstractGitCloneWizard implements IImportWizard {

	private SelectImportRootWizardPage selectRootPage;
	private NestedProjectsWizardPage nestedProjectsPage;
	private GitSelectRepositoryPage selectRepoPage = new GitSelectRepositoryPage();
	private Repository existingRepo;
	
	public EasymportGitWizard() {
		super();
		IDialogSettings dialogSettings = super.getDialogSettings();
		if (dialogSettings == null) {
			dialogSettings = Activator.getDefault().getDialogSettings();
			setDialogSettings(dialogSettings);
		}
	}
	
	@Override
	protected void addPreClonePages() {
		if (!hasSearchResult()) {
			addPage(selectRepoPage);
		}
	}
	
	@Override
	protected void addPostClonePages() {
		this.selectRootPage = new SelectImportRootWizardPage(this, null, null) {
			@Override
			public void setVisible(boolean visible) {
				if (visible) {
					if (existingRepo != null) {
						EasymportGitWizard.this.selectRootPage.setInitialSelectedDirectory(existingRepo.getWorkTree());
					} else { /* if ( (cloneDestination.cloneSettingsChanged())) {
						TODO handle clone
						setCallerRunsCloneOperation(true);
						try {
							final GitRepositoryInfo repositoryInfo = currentSearchResult.getGitRepositoryInfo();
							performClone(repositoryInfo);
							importWithDirectoriesPage.getControl().getDisplay().asyncExec(new Runnable() {

								public void run() {
									runCloneOperation(getContainer(), repositoryInfo);
									cloneDestination.saveSettingsForClonedRepo();
								}});
						} catch (URISyntaxException e) {
							Activator.error(UIText.GitImportWizard_errorParsingURI, e);
						} catch (NoRepositoryInfoException e) {
							Activator.error(UIText.GitImportWizard_noRepositoryInfo, e);
						} catch (Exception e) {
							Activator.error(e.getMessage(), e);
						}*/
						this.setInitialSelectedDirectory(getCloneDestinationPage().getDestinationFile());
					}
				}
				super.setVisible(visible);
			}
		};
		addPage(this.selectRootPage);
		this.nestedProjectsPage = new NestedProjectsWizardPage(this, this.selectRootPage);
		addPage(this.nestedProjectsPage);
	}

	@Override
	public boolean performFinish() {
		this.nestedProjectsPage.performNestedImport();
		return true;
	}
	
	@Override
	public boolean canFinish() {
		return getContainer().getCurrentPage().equals(this.nestedProjectsPage) ||
				(getContainer().getCurrentPage().equals(this.selectRootPage) && this.selectRootPage.canFlipToNextPage());
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected List<CloneSourceProvider> getCloneSourceProviders() {
		List<CloneSourceProvider> cloneSourceProvider = super.getCloneSourceProviders();
		cloneSourceProvider.add(0, CloneSourceProvider.LOCAL);
		return cloneSourceProvider;
	}

	public CloneDestinationPage getCloneDestinationPage() {
		return this.cloneDestination;
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page == selectRepoPage) {
			this.existingRepo = this.selectRepoPage.getRepository();
			return this.selectRootPage;
		}
		return super.getNextPage(page);
	}
}
