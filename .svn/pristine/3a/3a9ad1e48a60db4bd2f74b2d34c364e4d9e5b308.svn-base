package autocode;

import java.awt.Frame;

import javax.swing.ActionMap;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.ResourceMap;

public class AutoCodeAboutBox extends JDialog
{
  /**
	 * 
	 */
	private static final long serialVersionUID = -3714017955340877246L;
private JButton closeButton;

  public AutoCodeAboutBox(Frame parent)
  {
/* 28 */     super(parent);
/* 29 */     initComponents();
/* 30 */     getRootPane().setDefaultButton(this.closeButton);
  }
/* 33 */   @Action
  public void closeAboutBox() { setVisible(false); }


  private void initComponents()
  {
/* 38 */     this.closeButton = new JButton();
/* 39 */     JLabel appTitleLabel = new JLabel();
/* 40 */     JLabel versionLabel = new JLabel();
/* 41 */     JLabel appVersionLabel = new JLabel();
/* 42 */     JLabel vendorLabel = new JLabel();
/* 43 */     JLabel appVendorLabel = new JLabel();
/* 44 */     JLabel homepageLabel = new JLabel();
/* 45 */     JLabel appHomepageLabel = new JLabel();
/* 46 */     JLabel appDescLabel = new JLabel();
/* 47 */     JLabel imageLabel = new JLabel();

/* 49 */     setDefaultCloseOperation(2);
/* 50 */     ResourceMap resourceMap = ((AutoCodeApp)Application.getInstance(AutoCodeApp.class)).getContext().getResourceMap(AutoCodeAboutBox.class);
/* 51 */     setTitle(resourceMap.getString("title", new Object[0]));
/* 52 */     setModal(true);
/* 53 */     setName("aboutBox");
/* 54 */     setResizable(false);

/* 56 */     ActionMap actionMap = ((AutoCodeApp)Application.getInstance(AutoCodeApp.class)).getContext().getActionMap(AutoCodeAboutBox.class, this);
/* 57 */     this.closeButton.setAction(actionMap.get("closeAboutBox"));
/* 58 */     this.closeButton.setName("closeButton");

/* 60 */     appTitleLabel.setFont(appTitleLabel.getFont().deriveFont(appTitleLabel.getFont().getStyle() | 0x1, appTitleLabel.getFont().getSize() + 4));
/* 61 */     appTitleLabel.setText(resourceMap.getString("Application.title", new Object[0]));
/* 62 */     appTitleLabel.setName("appTitleLabel");

/* 64 */     versionLabel.setFont(versionLabel.getFont().deriveFont(versionLabel.getFont().getStyle() | 0x1));
/* 65 */     versionLabel.setText(resourceMap.getString("versionLabel.text", new Object[0]));
/* 66 */     versionLabel.setName("versionLabel");

/* 68 */     appVersionLabel.setText(resourceMap.getString("Application.version", new Object[0]));
/* 69 */     appVersionLabel.setName("appVersionLabel");

/* 71 */     vendorLabel.setFont(vendorLabel.getFont().deriveFont(vendorLabel.getFont().getStyle() | 0x1));
/* 72 */     vendorLabel.setText(resourceMap.getString("vendorLabel.text", new Object[0]));
/* 73 */     vendorLabel.setName("vendorLabel");

/* 75 */     appVendorLabel.setText(resourceMap.getString("Application.vendor", new Object[0]));
/* 76 */     appVendorLabel.setName("appVendorLabel");

/* 78 */     homepageLabel.setFont(homepageLabel.getFont().deriveFont(homepageLabel.getFont().getStyle() | 0x1));
/* 79 */     homepageLabel.setText(resourceMap.getString("homepageLabel.text", new Object[0]));
/* 80 */     homepageLabel.setName("homepageLabel");

/* 82 */     appHomepageLabel.setText(resourceMap.getString("Application.homepage", new Object[0]));
/* 83 */     appHomepageLabel.setName("appHomepageLabel");

/* 85 */     appDescLabel.setText(resourceMap.getString("appDescLabel.text", new Object[0]));
/* 86 */     appDescLabel.setName("appDescLabel");

/* 88 */     imageLabel.setIcon(resourceMap.getIcon("imageLabel.icon"));
/* 89 */     imageLabel.setName("imageLabel");

/* 91 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 92 */     getContentPane().setLayout(layout);
/* 93 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(imageLabel).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(versionLabel).addComponent(vendorLabel).addComponent(homepageLabel)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(appVersionLabel).addComponent(appVendorLabel).addComponent(appHomepageLabel))).addComponent(appTitleLabel, GroupLayout.Alignment.LEADING).addComponent(appDescLabel, GroupLayout.Alignment.LEADING, -1, 266, 32767).addComponent(this.closeButton)).addContainerGap()));

/* 95 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(imageLabel, -2, -1, 32767).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(appTitleLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(appDescLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(versionLabel).addComponent(appVersionLabel)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(vendorLabel).addComponent(appVendorLabel)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(homepageLabel).addComponent(appHomepageLabel)).addGap(19, 19, 32767).addComponent(this.closeButton).addContainerGap()));

/* 97 */     pack();
  }
}
