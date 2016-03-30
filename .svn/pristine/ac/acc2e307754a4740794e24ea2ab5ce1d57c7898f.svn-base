package autocode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.TaskMonitor;

public class AutoCodeView extends FrameView {
	private String db_url;
	private String db_username;
	private String db_password;
	private JButton jButtonConnect;
	private JLabel jLabelPassword;
	private JLabel jLabelURL;
	private JLabel jLabelUsername;
	private JList jListTables;
	private JPanel jPanel1;
	private JPanel jPanelDAO;
	private JPanel jPanelDomain;
	private JPanel jPanelDomain2;
	private JPanel jPanelIService;
	private JPanel jPanelIDAO;
	private JPanel jPanelSQLMAP;
	private JPanel jPanelService;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane3;
	private JScrollPane jScrollPaneAS3_2;
	private JScrollPane jScrollPaneAS3;
	private JScrollPane jScrollPane4;
	private JScrollPane jScrollPane5;
	private JScrollPane jScrollPane6;
	private JScrollPane jScrollPane7;
	private JSplitPane jSplitPane1;
	private JTabbedPane jTabbedPane;
	private JTable jTableColumnsInfo;
	private JTextArea jTextAreaDAO;
	private JTextArea jTextAreaDomain;
	private JTextArea jTextAreaDomainAS;
	private JTextArea jTextAreaDomainAS2;
	private JTextArea jTextAreaIDAO;
	private JTextArea jTextAreaSQLMAP;
	private JTextArea jTextAreaTestCase;
	private JTextField jTextFieldPassword;
	private JTextField jTextFieldURL;
	private JTextField jTextFieldUsername;
	private JPanel mainPanel;
	private JMenuBar menuBar;
	private JProgressBar progressBar;
	private JLabel statusAnimationLabel;
	private JLabel statusMessageLabel;
	private JPanel statusPanel;
	private final Timer messageTimer;
	private final Timer busyIconTimer;
	private final Icon idleIcon;
	private final Icon[] busyIcons = new Icon[15];
	private int busyIconIndex = 0;
	private JDialog aboutBox;

	public AutoCodeView(SingleFrameApplication app) {
		super(app);
		initComponents();

		this.jTextFieldURL.setText(ConfigurationService.getProperty("db_url"));
		/* 108 */this.jTextFieldUsername.setText(ConfigurationService.getProperty("db_username"));
		/* 109 */this.jTextFieldPassword.setText(ConfigurationService.getProperty("db_password"));

		/* 111 */String[] headers = { "列名", "数据类型", "注释", "别名" };
		/* 112 */DefaultTableModel dm = new DefaultTableModel();
		/* 113 */dm.setDataVector(new Object[2][4], headers);
		/* 114 */this.jTableColumnsInfo.setModel(dm);

		/* 116 */ResourceMap resourceMap = getResourceMap();
		/* 117 */int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout").intValue();
		/* 118 */this.messageTimer = new Timer(messageTimeout, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AutoCodeView.this.statusMessageLabel.setText("");
			}

		});
		/* 124 */this.messageTimer.setRepeats(false);
		/* 125 */int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate").intValue();
		/* 126 */for (int i = 0; i < this.busyIcons.length; i++) {
			/* 127 */this.busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
		}
		/* 129 */this.busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* 133 */AutoCodeView.this.statusAnimationLabel.setIcon(AutoCodeView.this.busyIcons[AutoCodeView.this.busyIconIndex]);
			}

		});
		/* 136 */this.idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
		/* 137 */this.statusAnimationLabel.setIcon(this.idleIcon);
		/* 138 */this.progressBar.setVisible(false);

		/* 140 */TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
		/* 141 */taskMonitor.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				/* 144 */String propertyName = evt.getPropertyName();
				/* 145 */if ("started".equals(propertyName)) {
					/* 146 */if (!AutoCodeView.this.busyIconTimer.isRunning()) {
						/* 147 */AutoCodeView.this.statusAnimationLabel.setIcon(AutoCodeView.this.busyIcons[0]);

						/* 149 */AutoCodeView.this.busyIconTimer.start();
					}
					/* 151 */AutoCodeView.this.progressBar.setVisible(true);
					/* 152 */AutoCodeView.this.progressBar.setIndeterminate(true);
					/* 153 */} else if ("done".equals(propertyName)) {
					/* 154 */AutoCodeView.this.busyIconTimer.stop();
					/* 155 */AutoCodeView.this.statusAnimationLabel.setIcon(AutoCodeView.this.idleIcon);
					/* 156 */AutoCodeView.this.progressBar.setVisible(false);
					/* 157 */AutoCodeView.this.progressBar.setValue(0);
					/* 158 */} else if ("message".equals(propertyName)) {
					/* 159 */String text = (String) evt.getNewValue();
					/* 160 */AutoCodeView.this.statusMessageLabel.setText(text == null ? "" : text);
					/* 161 */AutoCodeView.this.messageTimer.restart();
					/* 162 */} else if ("progress".equals(propertyName)) {
					/* 163 */int value = ((Integer) evt.getNewValue()).intValue();
					/* 164 */AutoCodeView.this.progressBar.setVisible(true);
					/* 165 */AutoCodeView.this.progressBar.setIndeterminate(false);
					/* 166 */AutoCodeView.this.progressBar.setValue(value);
				}
			}
		});
	}

	@Action
	public void showAboutBox() {
		/* 173 */if (this.aboutBox == null) {
			/* 174 */JFrame mainFrame = AutoCodeApp.getApplication().getMainFrame();
			/* 175 */this.aboutBox = new AutoCodeAboutBox(mainFrame);
			/* 176 */this.aboutBox.setLocationRelativeTo(mainFrame);
		}
		/* 178 */AutoCodeApp.getApplication().show(this.aboutBox);
	}

	private void initComponents() {
		/* 183 */this.mainPanel = new JPanel();
		/* 184 */this.jPanel1 = new JPanel();
		/* 185 */this.jLabelURL = new JLabel();
		/* 186 */this.jTextFieldURL = new JTextField();
		/* 187 */this.jLabelUsername = new JLabel();
		/* 188 */this.jTextFieldUsername = new JTextField();
		/* 189 */this.jLabelPassword = new JLabel();
		/* 190 */this.jTextFieldPassword = new JTextField();
		/* 191 */this.jSplitPane1 = new JSplitPane();
		/* 192 */this.jScrollPane1 = new JScrollPane();
		/* 193 */this.jListTables = new JList();
		/* 194 */this.jTabbedPane = new JTabbedPane();
		/* 195 */this.jScrollPane2 = new JScrollPane();
		/* 196 */this.jTableColumnsInfo = new JTable();
		/* 197 */this.jPanelDomain = new JPanel();
		/* 198 */this.jPanelDomain2 = new JPanel();
		/* 199 */this.jPanelIService = new JPanel();
		/* 200 */this.jScrollPane3 = new JScrollPane();
		/* 201 */this.jScrollPaneAS3 = new JScrollPane();
		/* 202 */this.jScrollPaneAS3_2 = new JScrollPane();
		/* 203 */this.jTextAreaDomain = new JTextArea();
		/* 204 */this.jTextAreaDomainAS = new JTextArea();
		/* 205 */this.jTextAreaDomainAS2 = new JTextArea();
		/* 206 */this.jPanelSQLMAP = new JPanel();
		/* 207 */this.jScrollPane4 = new JScrollPane();
		/* 208 */this.jTextAreaSQLMAP = new JTextArea();
		/* 209 */this.jPanelIDAO = new JPanel();
		/* 210 */this.jScrollPane5 = new JScrollPane();
		/* 211 */this.jTextAreaIDAO = new JTextArea();
		/* 212 */this.jPanelDAO = new JPanel();
		/* 213 */this.jScrollPane6 = new JScrollPane();
		/* 214 */this.jTextAreaDAO = new JTextArea();
		/* 215 */this.jPanelService = new JPanel();
		/* 216 */this.jScrollPane7 = new JScrollPane();
		/* 217 */this.jTextAreaTestCase = new JTextArea();
		/* 218 */this.jButtonConnect = new JButton();
		/* 219 */this.menuBar = new JMenuBar();
		/* 220 */JMenu fileMenu = new JMenu();
		/* 221 */JMenuItem exitMenuItem = new JMenuItem();
		/* 222 */JMenu helpMenu = new JMenu();
		/* 223 */JMenuItem aboutMenuItem = new JMenuItem();
		/* 224 */this.statusPanel = new JPanel();
		/* 225 */JSeparator statusPanelSeparator = new JSeparator();
		/* 226 */this.statusMessageLabel = new JLabel();
		/* 227 */this.statusAnimationLabel = new JLabel();
		/* 228 */this.progressBar = new JProgressBar();

		/* 230 */this.mainPanel.setName("mainPanel");

		/* 232 */ResourceMap resourceMap = ((AutoCodeApp) Application.getInstance(AutoCodeApp.class)).getContext().getResourceMap(AutoCodeView.class);
		/* 233 */this.jPanel1.setBorder(BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title", new Object[0])));
		/* 234 */this.jPanel1.setToolTipText(resourceMap.getString("jPanel1.toolTipText", new Object[0]));
		/* 235 */this.jPanel1.setName("jPanel1");

		/* 237 */this.jLabelURL.setText(resourceMap.getString("jLabelURL.text", new Object[0]));
		/* 238 */this.jLabelURL.setName("jLabelURL");

		/* 240 */this.jTextFieldURL.setText(resourceMap.getString("jTextFieldURL.text", new Object[0]));
		/* 241 */this.jTextFieldURL.setName("jTextFieldURL");

		/* 243 */this.jLabelUsername.setText(resourceMap.getString("jLabelUsername.text", new Object[0]));
		/* 244 */this.jLabelUsername.setName("jLabelUsername");

		/* 246 */this.jTextFieldUsername.setText(resourceMap.getString("jTextFieldUsername.text", new Object[0]));
		/* 247 */this.jTextFieldUsername.setName("jTextFieldUsername");

		/* 249 */this.jLabelPassword.setText(resourceMap.getString("jLabelPassword.text", new Object[0]));
		/* 250 */this.jLabelPassword.setName("jLabelPassword");

		/* 252 */this.jTextFieldPassword.setText(resourceMap.getString("jTextFieldPassword.text", new Object[0]));
		/* 253 */this.jTextFieldPassword.setName("jTextFieldPassword");

		/* 255 */GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
		/* 256 */this.jPanel1.setLayout(jPanel1Layout);
		/* 257 */jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jLabelPassword, -1, -1, 32767)
										.addComponent(this.jLabelUsername, -1, -1, 32767).addComponent(this.jLabelURL, -1, -1, 32767))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jTextFieldURL, -1, 482, 32767)
										.addComponent(this.jTextFieldUsername, -1, 482, 32767).addComponent(this.jTextFieldPassword, -1, 482, 32767))
						.addContainerGap()));

		/* 259 */jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout
						.createSequentialGroup()
						.addGroup(
								jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabelURL)
										.addComponent(this.jTextFieldURL, -2, -1, -2))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabelUsername)
										.addComponent(this.jTextFieldUsername, -2, -1, -2))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabelPassword)
										.addComponent(this.jTextFieldPassword, -2, -1, -2))));

		/* 261 */this.jLabelURL.getAccessibleContext().setAccessibleName(resourceMap.getString("jLabelURL.AccessibleContext.accessibleName", new Object[0]));

		/* 263 */this.jSplitPane1.setName("jSplitPane1");

		/* 265 */this.jScrollPane1.setName("jScrollPane1");

		/* 267 */this.jListTables.setSelectionMode(0);
		/* 268 */this.jListTables.setName("jListTables");
		/* 269 */this.jListTables.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				/* 271 */AutoCodeView.this.loadTableColumnsInfo(evt);
			}

		});
		/* 274 */this.jScrollPane1.setViewportView(this.jListTables);

		/* 276 */this.jSplitPane1.setLeftComponent(this.jScrollPane1);

		/* 278 */this.jTabbedPane.setName("jTabbedPane");
		/* 279 */this.jTabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				/* 281 */AutoCodeView.this.changePane(evt);
			}

		});
		/* 284 */this.jScrollPane2.setName("jScrollPane2");

		/* 286 */this.jTableColumnsInfo.setModel(new DefaultTableModel(new Object[][] { new Object[4], new Object[4], new Object[4], new Object[4] },
				new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));

		/* 288 */this.jTableColumnsInfo.setName("jTableColumnsInfo");
		/* 289 */this.jScrollPane2.setViewportView(this.jTableColumnsInfo);

		/* 291 */this.jTabbedPane.addTab(resourceMap.getString("jScrollPane2.TabConstraints.tabTitle", new Object[0]), this.jScrollPane2);

		/* 293 */this.jPanelDomain.setName("jPanelDomain");
		/* 294 */this.jPanelDomain2.setName("jPanelDomainAS");
		/* 295 */this.jPanelIService.setName("jPanelDomainAS2");

		/* 297 */this.jScrollPane3.setName("jScrollPane3");
		/* 298 */this.jScrollPaneAS3.setName("jScrollPaneAS3");
		/* 299 */this.jScrollPaneAS3_2.setName("jScrollPaneAS3_2");

		/* 301 */this.jTextAreaDomain.setColumns(20);
		/* 302 */this.jTextAreaDomain.setRows(5);
		/* 303 */this.jTextAreaDomain.setName("jTextAreaDomain");
		/* 304 */this.jScrollPane3.setViewportView(this.jTextAreaDomain);

		/* 306 */this.jTextAreaDomainAS.setColumns(20);
		/* 307 */this.jTextAreaDomainAS.setRows(5);
		/* 308 */this.jTextAreaDomainAS.setName("jTextAreaDomainAS");
		/* 309 */this.jScrollPaneAS3.setViewportView(this.jTextAreaDomainAS);

		/* 311 */this.jTextAreaDomainAS2.setColumns(20);
		/* 312 */this.jTextAreaDomainAS2.setRows(5);
		/* 313 */this.jTextAreaDomainAS2.setName("jTextAreaDomainAS2");
		/* 314 */this.jScrollPaneAS3_2.setViewportView(this.jTextAreaDomainAS2);

		/* 316 */GroupLayout jPanelDomainLayout = new GroupLayout(this.jPanelDomain);
		/* 317 */this.jPanelDomain.setLayout(jPanelDomainLayout);
		/* 318 */jPanelDomainLayout.setHorizontalGroup(jPanelDomainLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane3,
				-1, 547, 32767));

		/* 320 */jPanelDomainLayout.setVerticalGroup(jPanelDomainLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane3, -1,
				230, 32767));

		/* 322 */GroupLayout jPanelDomainASLayout = new GroupLayout(this.jPanelDomain2);
		/* 323 */this.jPanelDomain2.setLayout(jPanelDomainASLayout);
		/* 324 */jPanelDomainASLayout.setHorizontalGroup(jPanelDomainASLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
				this.jScrollPaneAS3, -1, 547, 32767));

		/* 326 */jPanelDomainASLayout.setVerticalGroup(jPanelDomainASLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
				this.jScrollPaneAS3, -1, 230, 32767));

		/* 328 */GroupLayout jPanelDomainAS2Layout = new GroupLayout(this.jPanelIService);
		/* 329 */this.jPanelIService.setLayout(jPanelDomainAS2Layout);
		/* 330 */jPanelDomainAS2Layout.setHorizontalGroup(jPanelDomainAS2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
				this.jScrollPaneAS3_2, -1, 547, 32767));

		/* 332 */jPanelDomainAS2Layout.setVerticalGroup(jPanelDomainAS2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
				this.jScrollPaneAS3_2, -1, 230, 32767));

		/* 334 */this.jTabbedPane.addTab(resourceMap.getString("jPanelDomain.TabConstraints.tabTitle", new Object[0]), this.jPanelDomain);
		/* 335 */this.jTabbedPane.addTab(resourceMap.getString("jPanelDomain2.TabConstraints.tabTitle", new Object[0]), this.jPanelDomain2);
		this.jTabbedPane.addTab(resourceMap.getString("jPanelSQLMAP.TabConstraints.tabTitle", new Object[0]), this.jPanelSQLMAP);
		this.jTabbedPane.addTab(resourceMap.getString("jPanelIDAO.TabConstraints.tabTitle", new Object[0]), this.jPanelIDAO);
		this.jTabbedPane.addTab(resourceMap.getString("jPanelDAO.TabConstraints.tabTitle", new Object[0]), this.jPanelDAO);

		/* 336 */this.jTabbedPane.addTab(resourceMap.getString("jPanelIService.TabConstraints.tabTitle", new Object[0]), this.jPanelIService);
		this.jTabbedPane.addTab(resourceMap.getString("jPanelService.TabConstraints.tabTitle", new Object[0]), this.jPanelService);
		/* 338 */this.jPanelSQLMAP.setName("jPanelSQLMAP");

		/* 340 */this.jScrollPane4.setName("jScrollPane4");

		/* 342 */this.jTextAreaSQLMAP.setColumns(20);
		/* 343 */this.jTextAreaSQLMAP.setRows(5);
		/* 344 */this.jTextAreaSQLMAP.setName("jTextAreaSQLMAP");
		/* 345 */this.jScrollPane4.setViewportView(this.jTextAreaSQLMAP);

		/* 347 */GroupLayout jPanelSQLMAPLayout = new GroupLayout(this.jPanelSQLMAP);
		/* 348 */this.jPanelSQLMAP.setLayout(jPanelSQLMAPLayout);
		/* 349 */jPanelSQLMAPLayout.setHorizontalGroup(jPanelSQLMAPLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane4,
				-1, 547, 32767));

		/* 351 */jPanelSQLMAPLayout.setVerticalGroup(jPanelSQLMAPLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane4, -1,
				230, 32767));

		/* 353 */

		/* 355 */this.jPanelIDAO.setName("jPanelIDAO");

		/* 357 */this.jScrollPane5.setName("jScrollPane5");

		/* 359 */this.jTextAreaIDAO.setColumns(20);
		/* 360 */this.jTextAreaIDAO.setRows(5);
		/* 361 */this.jTextAreaIDAO.setName("jTextAreaIDAO");
		/* 362 */this.jScrollPane5.setViewportView(this.jTextAreaIDAO);

		/* 364 */GroupLayout jPanelIDAOLayout = new GroupLayout(this.jPanelIDAO);
		/* 365 */this.jPanelIDAO.setLayout(jPanelIDAOLayout);
		/* 366 */jPanelIDAOLayout.setHorizontalGroup(jPanelIDAOLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane5, -1,
				547, 32767));

		/* 368 */jPanelIDAOLayout.setVerticalGroup(jPanelIDAOLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane5, -1, 230,
				32767));

		/* 370 */

		/* 372 */this.jPanelDAO.setName("jPanelDAO");

		/* 374 */this.jScrollPane6.setName("jScrollPane6");

		/* 376 */this.jTextAreaDAO.setColumns(20);
		/* 377 */this.jTextAreaDAO.setRows(5);
		/* 378 */this.jTextAreaDAO.setName("jTextAreaDAO");
		/* 379 */this.jScrollPane6.setViewportView(this.jTextAreaDAO);

		/* 381 */GroupLayout jPanelDAOLayout = new GroupLayout(this.jPanelDAO);
		/* 382 */this.jPanelDAO.setLayout(jPanelDAOLayout);
		/* 383 */jPanelDAOLayout.setHorizontalGroup(jPanelDAOLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane6, -1, 547,
				32767));

		/* 385 */jPanelDAOLayout.setVerticalGroup(jPanelDAOLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane6, -1, 230,
				32767));

		/* 387 */

		/* 389 */this.jPanelService.setName("jPanelTestCase");

		/* 391 */this.jScrollPane7.setName("jScrollPane7");

		/* 393 */this.jTextAreaTestCase.setColumns(20);
		/* 394 */this.jTextAreaTestCase.setRows(5);
		/* 395 */this.jTextAreaTestCase.setName("jTextAreaTestCase");
		/* 396 */this.jScrollPane7.setViewportView(this.jTextAreaTestCase);

		/* 398 */GroupLayout jPanelTestCaseLayout = new GroupLayout(this.jPanelService);
		/* 399 */this.jPanelService.setLayout(jPanelTestCaseLayout);
		/* 400 */jPanelTestCaseLayout.setHorizontalGroup(jPanelTestCaseLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
				this.jScrollPane7, -1, 547, 32767));

		/* 402 */jPanelTestCaseLayout.setVerticalGroup(jPanelTestCaseLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane7,
				-1, 230, 32767));

		/* 404 */

		/* 406 */this.jSplitPane1.setRightComponent(this.jTabbedPane);

		/* 408 */ActionMap actionMap = ((AutoCodeApp) Application.getInstance(AutoCodeApp.class)).getContext().getActionMap(AutoCodeView.class, this);
		/* 409 */this.jButtonConnect.setAction(actionMap.get("connectDB"));
		/* 410 */this.jButtonConnect.setText(resourceMap.getString("jButtonConnect.text", new Object[0]));
		/* 411 */this.jButtonConnect.setName("jButtonConnect");

		/* 413 */GroupLayout mainPanelLayout = new GroupLayout(this.mainPanel);
		/* 414 */this.mainPanel.setLayout(mainPanelLayout);
		/* 415 */mainPanelLayout.setHorizontalGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				mainPanelLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel1, -1, -1, 32767)
										.addComponent(this.jButtonConnect).addComponent(this.jSplitPane1, -1, 582, 32767)).addContainerGap()));

		/* 417 */mainPanelLayout.setVerticalGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				mainPanelLayout.createSequentialGroup().addComponent(this.jPanel1, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(this.jButtonConnect).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(this.jSplitPane1, -1, 261, 32767).addContainerGap()));

		/* 419 */this.menuBar.setName("menuBar");

		/* 421 */fileMenu.setText(resourceMap.getString("fileMenu.text", new Object[0]));
		/* 422 */fileMenu.setName("fileMenu");

		/* 424 */exitMenuItem.setAction(actionMap.get("quit"));
		/* 425 */exitMenuItem.setText(resourceMap.getString("exitMenuItem.text", new Object[0]));
		/* 426 */exitMenuItem.setName("exitMenuItem");
		/* 427 */fileMenu.add(exitMenuItem);

		/* 429 */this.menuBar.add(fileMenu);

		/* 431 */helpMenu.setText(resourceMap.getString("helpMenu.text", new Object[0]));
		/* 432 */helpMenu.setName("helpMenu");

		/* 434 */aboutMenuItem.setAction(actionMap.get("showAboutBox"));
		/* 435 */aboutMenuItem.setName("aboutMenuItem");
		/* 436 */helpMenu.add(aboutMenuItem);

		/* 438 */this.menuBar.add(helpMenu);

		/* 440 */this.statusPanel.setName("statusPanel");

		/* 442 */statusPanelSeparator.setName("statusPanelSeparator");

		/* 444 */this.statusMessageLabel.setName("statusMessageLabel");

		/* 446 */this.statusAnimationLabel.setHorizontalAlignment(2);
		/* 447 */this.statusAnimationLabel.setName("statusAnimationLabel");

		/* 449 */this.progressBar.setName("progressBar");

		/* 451 */GroupLayout statusPanelLayout = new GroupLayout(this.statusPanel);
		/* 452 */this.statusPanel.setLayout(statusPanelLayout);
		/* 453 */statusPanelLayout.setHorizontalGroup(statusPanelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(statusPanelSeparator, -1, 602, 32767)
				.addGroup(
						statusPanelLayout.createSequentialGroup().addContainerGap().addComponent(this.statusMessageLabel)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 432, 32767).addComponent(this.progressBar, -2, -1, -2)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.statusAnimationLabel).addContainerGap()));

		/* 455 */statusPanelLayout.setVerticalGroup(statusPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				statusPanelLayout
						.createSequentialGroup()
						.addComponent(statusPanelSeparator, -2, 2, -2)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
						.addGroup(
								statusPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.statusMessageLabel)
										.addComponent(this.statusAnimationLabel).addComponent(this.progressBar, -2, -1, -2)).addGap(3, 3, 3)));

		/* 457 */setComponent(this.mainPanel);
		/* 458 */setMenuBar(this.menuBar);
		/* 459 */setStatusBar(this.statusPanel);
	}

	private void loadTableColumnsInfo(MouseEvent evt) {
		/* 465 */DataService dataService = DataService.getInstance();

		/* 467 */if (dataService != null) {
			/* 469 */String[][] tableItems = dataService.getTableColumnsInfo(this.jListTables.getSelectedValue().toString());

			/* 471 */String[] headers = { "列名", "数据类型", "注释", "别名" };

			/* 473 */DefaultTableModel dm = new DefaultTableModel();

			/* 475 */dm.setDataVector(tableItems, headers);

			/* 477 */this.jTableColumnsInfo.setModel(dm);

			/* 479 */changeTabbed(dataService);
		}
	}

	private void changePane(ChangeEvent evt) {
		/* 486 */if (this.jTabbedPane.getSelectedIndex() != 0) {
			/* 487 */DataService dataService = DataService.getInstance();
			/* 488 */changeTabbed(dataService);
		}
	}

	private void changeTabbed(DataService dataService) {
		/* 493 */if (dataService != null) {
			/* 494 */if (this.jTabbedPane.getSelectedIndex() == 1) {
				/* 496 */this.jTextAreaDomain.setText(dataService.generateDomain(this.jTableColumnsInfo));
				/* 497 */this.jTextAreaDomain.setCaretPosition(0);
			}
			/* 499 */if (this.jTabbedPane.getSelectedIndex() == 2) {
				/* 501 */this.jTextAreaDomainAS.setText(dataService.generateDomainAS(this.jTableColumnsInfo));
				/* 502 */this.jTextAreaDomainAS.setCaretPosition(0);
			}
			/* 504 */if (this.jTabbedPane.getSelectedIndex() == 6) {
				/* 506 */this.jTextAreaDomainAS2.setText(dataService.generateDomainAS2(this.jTableColumnsInfo));
				/* 507 */this.jTextAreaDomainAS2.setCaretPosition(0);
			}
			/* 509 */if (this.jTabbedPane.getSelectedIndex() == 3) {
				/* 511 */this.jTextAreaSQLMAP.setText(dataService.generateSQLMAP(this.jTableColumnsInfo));
				/* 512 */this.jTextAreaSQLMAP.setCaretPosition(0);
			}
			/* 514 */if (this.jTabbedPane.getSelectedIndex() == 4) {
				/* 516 */this.jTextAreaIDAO.setText(dataService.generateIDAO(this.jTableColumnsInfo));
				/* 517 */this.jTextAreaIDAO.setCaretPosition(0);
			}
			/* 519 */if (this.jTabbedPane.getSelectedIndex() == 5) {
				/* 521 */this.jTextAreaDAO.setText(dataService.generateDAO(this.jTableColumnsInfo));
				/* 522 */this.jTextAreaDAO.setCaretPosition(0);
			}
			/* 524 */if (this.jTabbedPane.getSelectedIndex() == 7) {
				/* 526 */if (this.jTextAreaTestCase != null) {
					/* 527 */this.jTextAreaTestCase.setText(dataService.generateTestCase(this.jTableColumnsInfo));
					/* 528 */this.jTextAreaTestCase.setCaretPosition(0);
				}
			}
		}
	}

	@Action
	public void connectDB() {
		/* 536 */this.db_url = this.jTextFieldURL.getText();
		/* 537 */this.db_username = this.jTextFieldUsername.getText();
		/* 538 */this.db_password = this.jTextFieldPassword.getText();

		/* 540 */if (this.db_url.length() == 0) {
			/* 541 */JOptionPane.showMessageDialog(null, "请输入数据库链接！", "提示", 1);
			/* 542 */return;
		}

		/* 545 */DataService dataService = DataService.getInstance(this.db_url, this.db_username, this.db_password);

		/* 547 */this.jListTables.setListData(dataService.showTables());
	}
}
