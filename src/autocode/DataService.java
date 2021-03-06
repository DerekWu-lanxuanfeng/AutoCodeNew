/*     */ package autocode;
/*     */ 
/*     */ import freemarker.template.Configuration;
/*     */ import freemarker.template.DefaultObjectWrapper;
/*     */ import freemarker.template.Template;
/*     */ import freemarker.template.TemplateException;

/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.StringWriter;
/*     */ import java.io.Writer;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
import java.util.Calendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Vector;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;

import javax.swing.JTable;
/*     */ 
/*     */ public class DataService
/*     */ {
/*  29 */   private static Connection conn = null;
/*  30 */   private Statement stmt = null;
/*  31 */   private PreparedStatement prepstmt = null;
/*  32 */   private ResultSetMetaData rsmd = null;
/*  33 */   private List<ColumnInfo> columnInfoList = null;
/*     */   private static String databaseName;
/*     */   private String tableName;
/*     */   private String className;
/*     */   private String varClassName;
/*     */   private static DataService dataService;
/*     */ 
/*     */   private DataService(String db_url, String db_username, String db_password)
/*     */   {
/*     */     try
/*     */     {
/*  44 */       Class.forName("com.mysql.jdbc.Driver");
/*  45 */       conn = DriverManager.getConnection(db_url, db_username, db_password);
/*     */     } catch (SQLException ex) {
/*  47 */       Logger.getLogger(AutoCodeView.class.getName()).log(Level.SEVERE, null, ex);
/*     */     } catch (ClassNotFoundException ex) {
/*  49 */       Logger.getLogger(AutoCodeView.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static DataService getInstance(String db_url, String db_username, String db_password)
/*     */   {
/*  55 */     if (dataService == null) {
/*  56 */       dataService = new DataService(db_url, db_username, db_password);
/*  57 */       databaseName = db_url.substring(db_url.indexOf("3306") + 5, db_url.indexOf("?"));
/*  58 */       System.out.println(databaseName);
/*     */     }
/*     */ 
/*  61 */     return dataService;
/*     */   }
/*     */ 
/*     */   public static DataService getInstance() {
/*  65 */     return dataService;
/*     */   }
/*     */ 
/*     */   public Vector showTables()
/*     */   {
/*  70 */     Vector vector = new Vector();
/*     */     try
/*     */     {
/*  73 */       this.prepstmt = conn.prepareStatement("SHOW TABLES");
/*  74 */       ResultSet rs = this.prepstmt.executeQuery();
/*     */ 
/*  76 */       while (rs.next())
/*  77 */         vector.add(rs.getString(1));
/*     */     }
/*     */     catch (SQLException ex)
/*     */     {
/*  81 */       Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */ 
/*  84 */     return vector;
/*     */   }
/*     */ 
/*     */   public String[][] getTableColumnsInfo(String tableName)
/*     */   {
/*  89 */     this.tableName = tableName.replace("_0_", "_#{tableNum}_"); 
/*     */ 
/*  91 */     this.className = tableName.substring(4, tableName.length());
/*  92 */     this.className = converseColumn(this.className);
/*  93 */     this.className = (this.className.substring(0, 1).toUpperCase() + this.className.substring(1, this.className.length()));
/*     */ 
/*  95 */     this.varClassName = (this.className.substring(0, 1).toLowerCase() + this.className.substring(1, this.className.length()));
/*     */ 
/*  97 */     this.columnInfoList = new ArrayList();
/*     */ 
/*  99 */     String[][] tableItems = (String[][])null;
/*     */     try
/*     */     {
/* 102 */       this.prepstmt = conn.prepareStatement("SELECT COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT, COLUMN_KEY, EXTRA FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='" + tableName + "' AND TABLE_SCHEMA='" + databaseName + "' ORDER BY ORDINAL_POSITION");
/* 103 */       ResultSet rs = this.prepstmt.executeQuery();
/*     */ 
/* 105 */       rs.last();
/* 106 */       int row = rs.getRow();
/* 107 */       tableItems = new String[row][4];
/*     */ 
/* 109 */       rs.first();
/* 110 */       int i = 0;
/* 111 */       while (rs.next()) {
/* 112 */         if (i == 0) {
/* 113 */           rs.first();
/*     */         }
/* 115 */         tableItems[i][0] = rs.getString(1);
/* 116 */         tableItems[i][1] = rs.getString(2);
/* 117 */         tableItems[i][2] = rs.getString(3);
/* 118 */         tableItems[i][3] = converseColumn(tableItems[i][0]);
/*     */ 
/* 120 */         ColumnInfo columnInfo = new ColumnInfo();
/*     */ 
/* 122 */         String dataType = tableItems[i][1];
/* 123 */         String asDataType = tableItems[i][1];
				  String protoDataType = tableItems[i][1];
/*     */ 
/* 125 */         if (dataType.equals("int")) { 
/* 126 */           dataType = "int";
/* 127 */           asDataType = "number";
					protoDataType = "int32";
/* 128 */         } else if (dataType.equals("smallint")) {
	/* 129 */           dataType = "short";
	/* 130 */           asDataType = "number";
						protoDataType = "int32";
/* 131 */         } else if (dataType.equals("tinyint")) {
	/* 129 */           dataType = "byte";
	/* 130 */           asDataType = "number";
						protoDataType = "int32";
/* 131 */         } else if (dataType.equals("float")) {
/* 129 */           dataType = "float";
/* 130 */           asDataType = "number";
					protoDataType = "float";
/* 131 */         } else if (dataType.equals("bigint")) {
/* 132 */           dataType = "long";
/* 133 */           asDataType = "number";
					protoDataType = "int64";
/* 134 */         } else if ((dataType.equals("varchar")) || (dataType.equals("text"))|| (dataType.equals("char"))) {
/* 135 */           dataType = "String";
/* 136 */           asDataType = "string";
					protoDataType = "string";
/* 137 */         } else if ((dataType.equals("timestamp")) || (dataType.equals("date")) || (dataType.equals("datetime"))) {
/* 138 */           dataType = "Date";
/* 139 */           asDataType = "Date";
					protoDataType = "int64";
/*     */         }
/*     */ 
/* 142 */         columnInfo.setColumnName(tableItems[i][0]);
/* 143 */         columnInfo.setDataType(dataType);
/* 144 */         columnInfo.setAsDataType(asDataType);
/* 144 */         columnInfo.setProtoDataType(protoDataType);
/* 145 */         columnInfo.setComment(tableItems[i][2]);
/* 146 */         columnInfo.setColumnKey(rs.getString(4));
/* 147 */         columnInfo.setExtra(rs.getString(5));
/*     */ 
/* 149 */         this.columnInfoList.add(columnInfo);
/*     */ 
/* 151 */         i++;
/*     */       }
/*     */     }
/*     */     catch (SQLException ex) {
/* 155 */       Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */ 
/* 158 */     return tableItems;
/*     */   }
/*     */ 
/*     */   public String converseColumn(String targetStr)
/*     */   {
/* 163 */     String[] strSplited = targetStr.split("_");
/*     */ 
/* 165 */     if (strSplited.length == 1) {
/* 166 */       return strSplited[0].toLowerCase();
/*     */     }
/*     */ 
/* 169 */     StringBuffer strBuffer = new StringBuffer();
/*     */ 
/* 171 */     for (int i = 0; i < strSplited.length; i++) {
/* 172 */       if (i == 0) {
/* 173 */         strBuffer.append(strSplited[0].toLowerCase());
/*     */       } else {
/* 175 */         strBuffer.append(strSplited[i].substring(0, 1).toUpperCase());
/* 176 */         strBuffer.append(strSplited[i].substring(1, strSplited[i].length()).toLowerCase());
/*     */       }
/*     */     }
/*     */ 
/* 180 */     return strBuffer.toString();
/*     */   }
/*     */ 
/*     */   public void reSetColumnInfo(JTable jTable)
/*     */   {
/* 185 */     for (int i = 0; i < this.columnInfoList.size(); i++) {
/* 186 */       String value = jTable.getValueAt(i, 3).toString();
/* 187 */       ((ColumnInfo)this.columnInfoList.get(i)).setName(value);
/* 188 */       ((ColumnInfo)this.columnInfoList.get(i)).setGetterSetterName(value.substring(0, 1).toUpperCase() + value.substring(1, value.length()));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String generateDomain(JTable jTable)
/*     */   {
/* 194 */     reSetColumnInfo(jTable);
/*     */ 
/* 196 */     Writer out = new StringWriter();
/*     */ 
/* 198 */     Configuration cfg = new Configuration();
/* 199 */     cfg.setDefaultEncoding("UTF-8");
/*     */     try
/*     */     {
/* 202 */       cfg.setObjectWrapper(new DefaultObjectWrapper());
/* 203 */       Template template = cfg.getTemplate("domain.ftl");
/*     */ 
/* 205 */       Map dataMap = new HashMap();
/* 206 */       this.initMap(dataMap);
/*     */ 
/* 211 */       template.process(dataMap, out);
/*     */     }
/*     */     catch (TemplateException ex) {
/* 214 */       Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
/*     */     } catch (IOException ex) {
/* 216 */       Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */ 
/* 219 */     return out.toString();
/*     */   }
/*     */ 
/*     */   public String generateDomainAS(JTable jTable)
/*     */   {
/* 224 */     reSetColumnInfo(jTable);
/*     */ 
/* 226 */     Writer out = new StringWriter();
/*     */ 
/* 228 */     Configuration cfg = new Configuration();
/* 229 */     cfg.setDefaultEncoding("UTF-8");
/*     */     try
/*     */     {
/* 232 */       cfg.setObjectWrapper(new DefaultObjectWrapper());
/* 233 */       Template template = cfg.getTemplate("domain2.ftl");
/*     */ 
/* 235 */       Map dataMap = new HashMap();
/* 236 */       this.initMap(dataMap);
/*     */ 
/* 241 */       template.process(dataMap, out);
/*     */     }
/*     */     catch (TemplateException ex) {
/* 244 */       Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
/*     */     } catch (IOException ex) {
/* 246 */       Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */ 
/* 249 */     return out.toString();
/*     */   }
/*     */ 
/*     */   public String generateDomainAS2(JTable jTable)
/*     */   {
/* 254 */     reSetColumnInfo(jTable);
/*     */ 
/* 256 */     Writer out = new StringWriter();
/*     */ 
/* 258 */     Configuration cfg = new Configuration();
/* 259 */     cfg.setDefaultEncoding("UTF-8");
/*     */     try
/*     */     {
/* 262 */       cfg.setObjectWrapper(new DefaultObjectWrapper());
/* 263 */       Template template = cfg.getTemplate("IService.ftl");
/*     */ 
/* 265 */       Map dataMap = new HashMap();
/* 266 */       this.initMap(dataMap);
/*     */ 
/* 271 */       template.process(dataMap, out);
/*     */     }
/*     */     catch (TemplateException ex) {
/* 274 */       Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
/*     */     } catch (IOException ex) {
/* 276 */       Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */ 
/* 279 */     return out.toString();
/*     */   }
/*     */ 
/*     */   public String generateSQLMAP(JTable jTable)
/*     */   {
/* 286 */     reSetColumnInfo(jTable);
/*     */ 
/* 288 */     Writer out = new StringWriter();
/*     */ 
/* 290 */     Configuration cfg = new Configuration();
/* 291 */     cfg.setDefaultEncoding("UTF-8");
/*     */     try
/*     */     {
/* 294 */       cfg.setObjectWrapper(new DefaultObjectWrapper());
/* 295 */       Template template = cfg.getTemplate("sqlmap.ftl");
/*     */ 
/* 297 */       Map dataMap = new HashMap();
					this.initMap(dataMap);
/*     */ 
/* 309 */       template.process(dataMap, out);
/*     */     }
/*     */     catch (TemplateException ex) {
/* 312 */       Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
/*     */     } catch (IOException ex) {
/* 314 */       Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */ 
/* 317 */     return out.toString();
/*     */   }
/*     */ 
/*     */   public String generateIDAO(JTable jTable)
/*     */   {
/* 322 */     reSetColumnInfo(jTable);
/*     */ 
/* 324 */     Writer out = new StringWriter();
/*     */ 
/* 326 */     Configuration cfg = new Configuration();
/* 327 */     cfg.setDefaultEncoding("UTF-8");
/*     */     try
/*     */     {
/* 330 */       cfg.setObjectWrapper(new DefaultObjectWrapper());
/* 331 */       Template template = cfg.getTemplate("IDAO.ftl");
/*     */ 
/* 333 */       Map dataMap = new HashMap();
/* 334 */       this.initMap(dataMap);
/*     */ 
/* 342 */       template.process(dataMap, out);
/*     */     }
/*     */     catch (TemplateException ex) {
/* 345 */       Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
/*     */     } catch (IOException ex) {
/* 347 */       Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */ 
/* 350 */     return out.toString();
/*     */   }
/*     */ 
/*     */   public String generateDAO(JTable jTable)
/*     */   {
/* 355 */     reSetColumnInfo(jTable);
/*     */ 
/* 357 */     Writer out = new StringWriter();
/*     */ 
/* 359 */     Configuration cfg = new Configuration();
/* 360 */     cfg.setDefaultEncoding("UTF-8");
/*     */     try
/*     */     {
/* 363 */       cfg.setObjectWrapper(new DefaultObjectWrapper());
/* 364 */       Template template = cfg.getTemplate("DAO.ftl");
/*     */ 
/* 366 */       Map dataMap = new HashMap();
/* 367 */       this.initMap(dataMap);
/*     */ 
/* 375 */       template.process(dataMap, out);
/*     */     }
/*     */     catch (TemplateException ex) {
/* 378 */       Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
/*     */     } catch (IOException ex) {
/* 380 */       Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */ 
/* 383 */     return out.toString();
/*     */   }
/*     */ 
/*     */   public String generateTestCase(JTable jTable)
/*     */   {
/* 388 */     reSetColumnInfo(jTable);
/*     */ 
/* 390 */     Writer out = new StringWriter();
/*     */ 
/* 392 */     Configuration cfg = new Configuration();
/* 393 */     cfg.setDefaultEncoding("UTF-8");
/*     */     try
/*     */     {
/* 396 */       cfg.setObjectWrapper(new DefaultObjectWrapper());
/* 397 */       Template template = cfg.getTemplate("Service.ftl");
/*     */ 
/* 399 */       Map dataMap = new HashMap();
/* 400 */       this.initMap(dataMap);
/*     */ 
/* 405 */       template.process(dataMap, out);
/*     */     }
/*     */     catch (TemplateException ex) {
/* 408 */       Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
/*     */     } catch (IOException ex) {
/* 410 */       Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */ 
/* 413 */     return out.toString();
/*     */   }

				private void initMap(Map dataMap) {
					dataMap.put("package", ConfigurationService.getProperty("obj_package"));
					dataMap.put("packageFile", ConfigurationService.getProperty("obj_package").replace(".","/"));
					dataMap.put("tableName", this.tableName);
					
	/* 401 */       dataMap.put("className", this.className);
						dataMap.put("classNameVO", this.className+"VO");
					dataMap.put("daoName", this.className.substring(0,1).toLowerCase()+this.className.substring(1));
				   dataMap.put("moduleName", this.className.toLowerCase());
	/* 402 */       dataMap.put("varClassName", this.varClassName+"VO");
					dataMap.put("conlumnInfoCount", Integer.valueOf(this.columnInfoList.size()));
	/* 403 */       dataMap.put("columnInfoList", this.columnInfoList);
					if (this.columnInfoList.size() > 0) {
						dataMap.put("pkColumnName", ((ColumnInfo)this.columnInfoList.get(0)).getColumnName());
	/* 372 */         dataMap.put("pkName", ((ColumnInfo)this.columnInfoList.get(0)).getName());
					dataMap.put("pkType", ((ColumnInfo)this.columnInfoList.get(0)).getDataType());
	
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					dataMap.put("date", formatter.format(Calendar.getInstance().getTime()));
/*     */       }
					
				}
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\AutoCode_V1.4\AutoCode_V1.4.jar
 * Qualified Name:     autocode.DataService
 * JD-Core Version:    0.6.1
 */