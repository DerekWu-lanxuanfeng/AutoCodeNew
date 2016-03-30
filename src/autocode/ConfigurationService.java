/*    */ package autocode;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.IOException;
/*    */ import java.util.Properties;
/*    */ 
/*    */ public class ConfigurationService
/*    */ {
/* 10 */   private static Properties prop = new Properties();
/*    */ 
/*    */   static
/*    */   {
/*    */     try
/*    */     {
/* 25 */       prop.load(new FileInputStream(new File("config.properties")));
/*    */     } catch (IOException e) {
/* 27 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public static String getProperty(String key)
/*    */   {
/* 14 */     return prop.getProperty(key);
/*    */   }
/*    */ 
/*    */   public static void setProperty(String key, String value) {
/* 18 */     prop.setProperty(key, value);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\AutoCode_V1.4\AutoCode_V1.4.jar
 * Qualified Name:     autocode.ConfigurationService
 * JD-Core Version:    0.6.1
 */